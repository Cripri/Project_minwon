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

    private final BlockingQueue<QueryRequest<?>> queryQueue = new LinkedBlockingQueue<>(10);

    public void putQuery(QueryRequest<?> request) throws InterruptedException {
        queryQueue.put(request);
    }

    @Override
    public void run() {
        MainFrameState.civil = this;
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            while (true) {
                QueryRequest<?> request = queryQueue.take();
                if (request.getQuery().toLowerCase().startsWith("select")) {
                    executeQuery(request, conn);
                } else {
                    executeUpdateQuery(request, conn);
                }
            }
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
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
            request.done();  // 꼭 호출!
        }
    }

    private void executeUpdateQuery(QueryRequest<?> request, Connection conn) {
        try (PreparedStatement ps = conn.prepareStatement(request.getQuery())) {
            List<Object> params = request.getParams();
            if (params != null) {
                for (int i = 0; i < params.size(); i++) {
                    ps.setObject(i + 1, params.get(i));
                }
            }
            int affectedRows = ps.executeUpdate();
            System.out.println("영향 받은 행 수: " + affectedRows);
        } catch (SQLException e) {
            e.printStackTrace();
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
                String columnName = meta.getColumnName(i).toLowerCase();  // ← columnLabel → columnName 로 바꿔봄
                Field field = fieldMap.get(columnName);
                if (field != null) {
                    Object value = rs.getObject(i);

                    // BigDecimal → Integer
                    if (value instanceof java.math.BigDecimal && field.getType() == Integer.class) {
                        field.set(instance, ((java.math.BigDecimal) value).intValue());
                    }
                    // Timestamp → Date
                    else if (value instanceof Timestamp && field.getType() == java.util.Date.class) {
                        field.set(instance, new java.util.Date(((Timestamp) value).getTime()));
                    }
                    else {
                        field.set(instance, value);
                    }
                }
            }
            list.add(instance);
        }
        return list;
    }
}
