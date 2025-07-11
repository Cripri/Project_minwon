package function.connector;

import java.util.List;

public class QueryRequest<T> {
    private String query;
    private String operationName;
    private String tableName;
    private List<Object> params;
    private List<T> resultList;
    private QueryType queryType;
    private Class<T> resultClass;
    private T singleResult;

    public QueryRequest(String query, String operationName, String tableName,
                        List<Object> params, QueryType queryType, Class<T> resultClass) {
        this.query = query;
        this.operationName = operationName;
        this.tableName = tableName;
        this.params = params;
        this.queryType = queryType;
        this.resultClass = resultClass;
    }

    public QueryRequest(String query, String operationName, String tableName,
                        Object singleParam, QueryType queryType, Class<T> resultClass) {
        this.query = query;
        this.operationName = operationName;
        this.tableName = tableName;
        this.params = List.of(singleParam);  // 바로 리스트 생성
        this.queryType = queryType;
        this.resultClass = resultClass;
    }

    // 게터 & 세터
    public String getQuery() { return query; }
    public void setQuery(String query) { this.query = query; }

    public String getOperationName() { return operationName; }
    public void setOperationName(String operationName) { this.operationName = operationName; }

    public String getTableName() { return tableName; }
    public void setTableName(String tableName) { this.tableName = tableName; }

    public List<Object> getParams() { return params; }
    public void setParams(List<Object> params) { this.params = params; }

    public List<T> getResultList() { return resultList; }
    public void setResultList(List<T> resultList) { this.resultList = resultList; }

    public QueryType getQueryType() { return queryType; }
    public void setQueryType(QueryType queryType) { this.queryType = queryType; }

    public Class<T> getResultClass() { return resultClass; }
    public void setResultClass(Class<T> resultClass) { this.resultClass = resultClass; }

    public T getSingleResult() { return singleResult; }
    public void setSingleResult(T singleResult) { this.singleResult = singleResult; }
}
