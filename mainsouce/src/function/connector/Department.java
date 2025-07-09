package function.connector;

import java.sql.ResultSet;
import java.sql.SQLException;



public class Department {
	Integer department_code;
	String department_name;
	
	public Department(Integer department_code, String department_name) {
		this.department_code = department_code;
		this.department_name = department_name;
	}

	public Department() {
	}

	@Override
	public String toString() {
		return "Department [department_code=" + department_code + ", department_name=" + department_name + "]";
	}
	

	public Integer getDepartment_code() {
		return department_code;
	}

	public void setDepartment_code(Integer department_code) {
		this.department_code = department_code;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	
	
}
