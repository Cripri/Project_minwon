package function.connector;

import java.sql.Connection;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class QueryRequest<T> {
    private CountDownLatch latch = new CountDownLatch(1);
    private String query;
    private List<Object> params;
    private List<T> resultList;
    private Class<T> resultClass;
    private T singleResult;

    public QueryRequest(String query,
                        List<Object> params, Class<T> resultClass, Civil_Connector con) {
        this.query = query;
        this.params = params;
        this.resultClass = resultClass;
        put(con);
    }

    public QueryRequest(String query,
                        Object singleParam, Class<T> resultClass, Civil_Connector con) {
        this.query = query;
        this.params = List.of(singleParam);  // 바로 리스트 생성
        this.resultClass = resultClass;
        put(con);
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public void done() {
        latch.countDown();
    }

    // 게터 & 세터
    public String getQuery() { return query; }
    public void setQuery(String query) { this.query = query; }

    public List<Object> getParams() { return params; }
    public void setParams(List<Object> params) { this.params = params; }

    public List<T> getResultList() { return resultList; }
    public void setResultList(List<T> resultList) { this.resultList = resultList; }

    public Class<T> getResultClass() { return resultClass; }
    public void setResultClass(Class<T> resultClass) { this.resultClass = resultClass; }

    public T getSingleResult() { return singleResult; }
    public void setSingleResult(T singleResult) { this.singleResult = singleResult; }



    private void put(Civil_Connector con){
        try {
            con.putQuery(this);
            this.getLatch().await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
