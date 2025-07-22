package function.connector;

public enum TableMeta {
    CITIZEN_DB("citizen_db", "c_code"),
    COMPLAINT_CATEGORY_INFO("complaint_category_info", "complaint_category_code"),
    DEPARTMENT("department", "department_code"),
    DISTRICT("district", "district_code"),
    EMPLOYEES("employees", "employee_code"),
    MEMBERS("members", "member_code"),
    POSITION("position", "position_code"),
    SINMUNGO("sinmungo", "sinmungo_code"),
    SIMPLE_DOC("simple_doc", "simple_doc_code");

    private final String tableName;
    private final String pkColumn;

    TableMeta(String tableName, String pkColumn) {
        this.tableName = tableName;
        this.pkColumn = pkColumn;
    }

    public String getTableName() {
        return tableName;
    }

    public String getPkColumn() {
        return pkColumn;
    }

    public static TableMeta fromClass(Class<?> clazz) {
        String name = clazz.getSimpleName().toUpperCase();
        try {
            return TableMeta.valueOf(name);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("TableMeta enum에 없는 이름: " + name);
        }
    }
}