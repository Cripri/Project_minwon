package function.connector;

public class Employees {
	Integer employee_code;
	Integer district_code;
	String employee_name;
	String employee_id;
	String employee_password;
	Integer department_code;
	Integer position_code;
	Integer authority;

	public Employees() {
	}

	public Employees (Integer employee_code, Integer district_code, String employee_name,
					  String employee_id, String employee_password, Integer department_code,
					  Integer position_code, Integer authority) {
		this.employee_code = employee_code;
		this.district_code = district_code;
		this.employee_name = employee_name;
		this.employee_id = employee_id;
		this.employee_password = employee_password;
		this.department_code = department_code;
		this.position_code = position_code;
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "Employees [employee_code=" + employee_code + ", district_code=" + district_code + ", employee_name="
				+ employee_name + ", employee_id=" + employee_id + ", employee_password=" + employee_password
				+ ", department_code=" + department_code + ", position_code=" + position_code + ", authority="
				+ authority + "]";
	}

	public Integer getEmployee_code() {
		return employee_code;
	}

	public void setEmployee_code(Integer employee_code) {
		this.employee_code = employee_code;
	}

	public Integer getDistrict_code() {
		return district_code;
	}

	public void setDistrict_code(Integer district_code) {
		this.district_code = district_code;
	}

	public String getEmployee_name() {
		return employee_name;
	}

	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}

	public String getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}

	public String getEmployee_password() {
		return employee_password;
	}

	public void setEmployee_password(String employee_password) {
		this.employee_password = employee_password;
	}

	public Integer getDepartment_code() {
		return department_code;
	}

	public void setDepartment_code(Integer department_code) {
		this.department_code = department_code;
	}

	public Integer getPosition_code() {
		return position_code;
	}

	public void setPosition_code(Integer position_code) {
		this.position_code = position_code;
	}

	public Integer getAuthority() {
		return authority;
	}

	public void setAuthority(Integer authority) {
		this.authority = authority;
	}
	
	
}
