package function.connector;

import java.util.Date;

public class Simple_doc {
	Integer simple_doc_code;
	String complaint_category_code;
	Integer district_code;
	String household_type;
	String household_member;
	String rrn_last7;
	String status;
	Date create_date;
	Date complete_date;
	Integer number;

	public Simple_doc() {
	}

	public Simple_doc(Integer simple_doc_code, String complaint_category_code, Integer district_code,
					  String household_type, String household_member, String rrn_last7, String status, Date create_date,
					  Date complete_date, Integer number) {
		super();
		this.simple_doc_code = simple_doc_code;
		this.complaint_category_code = complaint_category_code;
		this.district_code = district_code;
		this.household_type = household_type;
		this.household_member = household_member;
		this.rrn_last7 = rrn_last7;
		this.status = status;
		this.create_date = create_date;
		this.complete_date = complete_date;
		this.number = number;
	}
	
	
	@Override
	public String toString() {
		return "Simple_doc [simple_doc_code=" + simple_doc_code + ", complaint_category_code=" + complaint_category_code
				+ ", district_code=" + district_code + ", household_type=" + household_type + ", household_member="
				+ household_member + ", rrn_last7=" + rrn_last7 + ", status=" + status + ", create_date=" + create_date
				+ ", complete_date=" + complete_date + ", number=" + number + "]";
	}


	public Integer getSimple_doc_code() {
		return simple_doc_code;
	}


	public void setSimple_doc_code(Integer simple_doc_code) {
		this.simple_doc_code = simple_doc_code;
	}


	public String getComplaint_category_code() {
		return complaint_category_code;
	}


	public void setComplaint_category_code(String complaint_category_code) {
		this.complaint_category_code = complaint_category_code;
	}


	public Integer getDistrict_code() {
		return district_code;
	}


	public void setDistrict_code(Integer district_code) {
		this.district_code = district_code;
	}


	public String getHousehold_type() {
		return household_type;
	}


	public void setHousehold_type(String household_type) {
		this.household_type = household_type;
	}


	public String getHousehold_member() {
		return household_member;
	}


	public void setHousehold_member(String household_member) {
		this.household_member = household_member;
	}


	public String getRrn_last7() {
		return rrn_last7;
	}


	public void setRrn_last7(String rrn_last7) {
		this.rrn_last7 = rrn_last7;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Date getCreate_date() {
		return create_date;
	}


	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}


	public Date getComplete_date() {
		return complete_date;
	}


	public void setComplete_date(Date complete_date) {
		this.complete_date = complete_date;
	}


	public Integer getNumber() {
		return number;
	}


	public void setNumber(Integer number) {
		this.number = number;
	}

	
	
}

