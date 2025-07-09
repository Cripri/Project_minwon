package function.connector;


import java.util.Date;

public class Simungo {
	Integer sinmungo_code;
	Integer member_code;
	String member_phonenum;
	String member_email;
	String member_name;
	Date member_birthday;
	String sinmungo_title;
	String sinmungo_content;
	Integer complaint_area;
	Date create_date;
	Date answer_date;
	Integer employee_code;
	String employees_answer;
	String security_set;
	String security_password;
	String status;
	
	
	public Simungo(Integer sinmungo_code, Integer member_code, String member_phonenum, String member_email,
			String member_name, Date member_birthday, String sinmungo_title, String sinmungo_content,
			Integer complaint_area, Date create_date, Date answer_date, Integer employee_code, String employees_answer,
			String security_set, String security_password, String status) {
		super();
		this.sinmungo_code = sinmungo_code;
		this.member_code = member_code;
		this.member_phonenum = member_phonenum;
		this.member_email = member_email;
		this.member_name = member_name;
		this.member_birthday = member_birthday;
		this.sinmungo_title = sinmungo_title;
		this.sinmungo_content = sinmungo_content;
		this.complaint_area = complaint_area;
		this.create_date = create_date;
		this.answer_date = answer_date;
		this.employee_code = employee_code;
		this.employees_answer = employees_answer;
		this.security_set = security_set;
		this.security_password = security_password;
		this.status = status;
	}


	@Override
	public String toString() {
		return "Simungo [sinmungo_code=" + sinmungo_code + ", member_code=" + member_code + ", member_phonenum="
				+ member_phonenum + ", member_email=" + member_email + ", member_name=" + member_name
				+ ", member_birthday=" + member_birthday + ", sinmungo_title=" + sinmungo_title + ", sinmungo_content="
				+ sinmungo_content + ", complaint_area=" + complaint_area + ", create_date=" + create_date
				+ ", answer_date=" + answer_date + ", employee_code=" + employee_code + ", employees_answer="
				+ employees_answer + ", security_set=" + security_set + ", security_password=" + security_password
				+ ", status=" + status + "]";
	}


	public Integer getSinmungo_code() {
		return sinmungo_code;
	}


	public void setSinmungo_code(Integer sinmungo_code) {
		this.sinmungo_code = sinmungo_code;
	}


	public Integer getMember_code() {
		return member_code;
	}


	public void setMember_code(Integer member_code) {
		this.member_code = member_code;
	}


	public String getMember_phonenum() {
		return member_phonenum;
	}


	public void setMember_phonenum(String member_phonenum) {
		this.member_phonenum = member_phonenum;
	}


	public String getMember_email() {
		return member_email;
	}


	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}


	public String getMember_name() {
		return member_name;
	}


	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}


	public Date getMember_birthday() {
		return member_birthday;
	}


	public void setMember_birthday(Date member_birthday) {
		this.member_birthday = member_birthday;
	}


	public String getSinmungo_title() {
		return sinmungo_title;
	}


	public void setSinmungo_title(String sinmungo_title) {
		this.sinmungo_title = sinmungo_title;
	}


	public String getSinmungo_content() {
		return sinmungo_content;
	}


	public void setSinmungo_content(String sinmungo_content) {
		this.sinmungo_content = sinmungo_content;
	}


	public Integer getComplaint_area() {
		return complaint_area;
	}


	public void setComplaint_area(Integer complaint_area) {
		this.complaint_area = complaint_area;
	}


	public Date getCreate_date() {
		return create_date;
	}


	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}


	public Date getAnswer_date() {
		return answer_date;
	}


	public void setAnswer_date(Date answer_date) {
		this.answer_date = answer_date;
	}


	public Integer getEmployee_code() {
		return employee_code;
	}


	public void setEmployee_code(Integer employee_code) {
		this.employee_code = employee_code;
	}


	public String getEmployees_answer() {
		return employees_answer;
	}


	public void setEmployees_answer(String employees_answer) {
		this.employees_answer = employees_answer;
	}


	public String getSecurity_set() {
		return security_set;
	}


	public void setSecurity_set(String security_set) {
		this.security_set = security_set;
	}


	public String getSecurity_password() {
		return security_password;
	}


	public void setSecurity_password(String security_password) {
		this.security_password = security_password;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
