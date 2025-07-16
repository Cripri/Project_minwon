package function.connector;

import gui.mainframe.MainFrameState;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Civil_Connector extends Thread {
    static String path = "oracle.jdbc.driver.OracleDriver";
    static String url = "jdbc:oracle:thin:@//192.168.0.26:1521/XE";
    static String user = "centeradmin";
    static String password = "1234";

    static {
        try {
            Class.forName(path);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private final BlockingQueue<QueryRequest<?>> queryQueue = new LinkedBlockingQueue<>();

    public void putQuery(QueryRequest<?> request) throws InterruptedException {
        queryQueue.put(request);
    }

    @Override
    public void run() {
        MainFrameState.civil = this;
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            while (true) {
                QueryRequest<?> request = queryQueue.take();
                //System.out.println("쿼리 받음: " + request.getQuery());
                if (request.getQuery().toLowerCase().startsWith("select")) {
                    executeQuery(request, conn);
                } else {
                    executeUpdateQuery(request, conn);
                }
            }
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("스레드 종료됨!");
        }
    }

    private <T> void executeQuery(QueryRequest<T> request, Connection conn) {
        try (PreparedStatement ps = conn.prepareStatement(request.getQuery())) {
            List<Object> params = request.getParams();
            if (params != null) {
                for (int i = 0; i < params.size(); i++) {
                    ps.setObject(i + 1, params.get(i));
                }
            }
            ResultSet rs = ps.executeQuery();
            List<T> list = mapResultSetToList(rs, request.getResultClass());
            request.setResultList(list);

            if (list != null && !list.isEmpty()) {
                request.setSingleResult(list.get(0));
            } else {
                request.setSingleResult(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.done();
        }
    }

    private void executeUpdateQuery(QueryRequest<?> request, Connection conn) {
        try (PreparedStatement ps = conn.prepareStatement(request.getQuery())) {
            List<Object> params = request.getParams();
            if (params != null) {
                for (int i = 0; i < params.size(); i++) {
                    Object param = params.get(i);
                    if (param instanceof java.util.Date) {
                        ps.setDate(i + 1, new java.sql.Date(((java.util.Date) param).getTime()));
                    } else if (param instanceof Boolean) {
                        ps.setString(i + 1, (Boolean) param ? "T" : "F");
                    } else {
                        ps.setObject(i + 1, param);
                    }
                }
            }
            int affectedRows = ps.executeUpdate();
            //System.out.println("영향 받은 행 수: " + affectedRows);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            request.done();
        }
    }

    private <T> List<T> mapResultSetToList(ResultSet rs, Class<T> clazz) throws Exception {
        List<T> list = new ArrayList<>();
        ResultSetMetaData meta = rs.getMetaData();
        int columnCount = meta.getColumnCount();

        Map<String, Field> fieldMap = new HashMap<>();
        for (Field f : clazz.getDeclaredFields()) {
            f.setAccessible(true);
            fieldMap.put(f.getName().toLowerCase(), f);
        }

        while (rs.next()) {
            T instance = clazz.getDeclaredConstructor().newInstance();

            for (int i = 1; i <= columnCount; i++) {
                String columnName = meta.getColumnLabel(i).toLowerCase();
                Field field = fieldMap.get(columnName);
                if (field != null) {
                    Object value = rs.getObject(i);

                    if (value instanceof java.math.BigDecimal && field.getType() == Integer.class) {
                        field.set(instance, ((java.math.BigDecimal) value).intValue());
                    } else if (value instanceof Timestamp && field.getType() == java.util.Date.class) {
                        field.set(instance, new java.util.Date(((Timestamp) value).getTime()));
                    } else {
                        field.set(instance, value);
                    }
                }
            }
            list.add(instance);
        }
        return list;
    }

    public <T> void insert(T obj) {
        try {
            Class<?> clazz = obj.getClass();
            Field[] fields = clazz.getDeclaredFields();

            String tableName = clazz.getSimpleName().toLowerCase();
            String seqName = tableName + "_seq";

            List<String> columnNames = new ArrayList<>();
            List<String> valueExprs = new ArrayList<>();  // 값 표현식: ? 또는 시퀀스 nextval
            List<Object> values = new ArrayList<>();

            for (Field f : fields) {
                f.setAccessible(true);
                Object value = f.get(obj);

                String colName = f.getName().toLowerCase();

                // PK 추정: 이름이 'code' 로 끝나는 컬럼
                if (colName.endsWith("code")) {
                    if (value == null) {
                        // 값이 null 이면 시퀀스 nextval 사용
                        columnNames.add(colName);
                        valueExprs.add(seqName + ".nextval");
                    } else {
                        // 값이 있으면 그대로 insert
                        columnNames.add(colName);
                        valueExprs.add("?");
                        values.add(value);
                    }
                } else {
                    if (value != null) {
                        columnNames.add(colName);
                        valueExprs.add("?");
                        values.add(value);
                    }
                }
            }

            String cols = String.join(", ", columnNames);
            String vals = String.join(", ", valueExprs);

            String sql = "INSERT INTO " + tableName + " (" + cols + ") VALUES (" + vals + ")";

            QueryRequest<Object> req = new QueryRequest<>(sql, values, Object.class, this);
            req.getLatch().await();

            System.out.println("Insert complete: " + sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <T> void update(T obj) {
        try {
            Class<?> clazz = obj.getClass();
            Field[] fields = clazz.getDeclaredFields();

            String tableName = clazz.getSimpleName().toLowerCase();
            List<String> setClauses = new ArrayList<>();
            List<Object> values = new ArrayList<>();

            String pkName = null;
            Object pkValue = null;

            for (Field f : fields) {
                f.setAccessible(true);
                Object value = f.get(obj);
                if (f.getName().toLowerCase().endsWith("code")) {
                    pkName = f.getName().toLowerCase();
                    pkValue = value;
                } else if (value != null) {
                    setClauses.add(f.getName().toLowerCase() + " = ?");
                    values.add(value);
                }
            }

            if (pkName == null || pkValue == null) {
                throw new IllegalArgumentException("PK 필드(xxx_code)가 필요합니다!");
            }

            String setPart = String.join(", ", setClauses);
            String sql = "UPDATE " + tableName + " SET " + setPart + " WHERE " + pkName + " = ?";
            values.add(pkValue);

            QueryRequest<Object> req = new QueryRequest<>(sql, values, Object.class, this);
            req.getLatch().await();
            System.out.println("Update complete: " + sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <T> List<T> selectAll(Class<T> clazz) {
        try {
            String tableName = clazz.getSimpleName().toLowerCase();
            String sql = "SELECT * FROM " + tableName;
            QueryRequest<T> req = new QueryRequest<>(sql, Collections.emptyList(), clazz, this);
            req.getLatch().await();
            return req.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public <T> T find(Class<T> clazz,Object pk){
        try{
            Field[] fields = clazz.getDeclaredFields();

            String pkName = null;
            for (Field f : fields) {
                f.setAccessible(true);
                if (f.getName().toLowerCase().endsWith("code")) {
                    pkName = f.getName().toLowerCase();
                    break;
                }
            }

            String tableName = clazz.getSimpleName().toLowerCase();
            String sql = "select * from " + tableName + " where " + pkName + " = ?";

            QueryRequest<T> req = new QueryRequest<>(sql,pk,clazz,this);
            return req.getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
