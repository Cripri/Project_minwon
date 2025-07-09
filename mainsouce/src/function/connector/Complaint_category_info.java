package function.connector;

public class Complaint_category_info {
	
	String complaint_category_code;
	String complaint_category_name;
	String complaint_category_info;

	public Complaint_category_info() {
	}

	public Complaint_category_info(String complaint_category_code, String complaint_category_name,
								   String complaint_category_info) {
		super();
		this.complaint_category_code = complaint_category_code;
		this.complaint_category_name = complaint_category_name;
		this.complaint_category_info = complaint_category_info;
	}


	@Override
	public String toString() {
		return "Complaint_category_info [complaint_category_code=" + complaint_category_code
				+ ", complaint_category_name=" + complaint_category_name + ", complaint_category_info="
				+ complaint_category_info + "]";
	}


	public String getComplaint_category_code() {
		return complaint_category_code;
	}


	public void setComplaint_category_code(String complaint_category_code) {
		this.complaint_category_code = complaint_category_code;
	}


	public String getComplaint_category_name() {
		return complaint_category_name;
	}


	public void setComplaint_category_name(String complaint_category_name) {
		this.complaint_category_name = complaint_category_name;
	}


	public String getComplaint_category_info() {
		return complaint_category_info;
	}


	public void setComplaint_category_info(String complaint_category_info) {
		this.complaint_category_info = complaint_category_info;
	}
	
	
	
}
