package function.connector;

public class Citizen_db {
	  Integer c_code;
	  String c_name;
	  String c_rrn;
	  Integer district_code;
	  String c_rrcard;

	public Citizen_db() {
	}

	public Citizen_db(Integer c_code, String c_name, String c_rrn, Integer district_code, String c_rrcard) {
		super();
		this.c_code = c_code;
		this.c_name = c_name;
		this.c_rrn = c_rrn;
		this.district_code = district_code;
		this.c_rrcard = c_rrcard;
	}
	
	public Integer getC_code() {
		return c_code;
	}

	public void setC_code(Integer c_code) {
		this.c_code = c_code;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public String getC_rrn() {
		return c_rrn;
	}

	public void setC_rrn(String c_rrn) {
		this.c_rrn = c_rrn;
	}

	public Integer getDistrict_code() {
		return district_code;
	}

	public void setDistrict_code(Integer district_code) {
		this.district_code = district_code;
	}

	public String getC_rrcard() {
		return c_rrcard;
	}

	public void setC_rrcard(String c_rrcard) {
		this.c_rrcard = c_rrcard;
	}

	@Override
	public String toString() {
		return "Citizen_db [c_code=" + c_code + ", c_name=" + c_name + ", c_rrn=" + c_rrn + ", district_code="
				+ district_code + ", c_rrcard=" + c_rrcard + "]";
	}
	
}
