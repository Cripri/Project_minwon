package function.connector;


import java.util.Date;

public class Members {
	Integer member_code;
	String member_id;
	String member_password;
	String member_name;
	Date member_birthday;
	String member_gender;
	Integer district_code;
	String member_ad_detail;
	String member_phonenum;
	String member_email;

	public Members() {
	}

	public Members(Integer member_code, String member_id, String member_password,
				   String member_name, Date member_birthday, String member_gender, Integer district_code,
				   String member_ad_detail, String member_phonenum, String member_email) {
		this.member_code = member_code;
		this.member_id = member_id;
		this.member_password = member_password;
		this.member_name = member_name;
		this.member_birthday = member_birthday;
		this.member_gender = member_gender;
		this.district_code = district_code;
		this.member_ad_detail = member_ad_detail;
		this.member_phonenum = member_phonenum;
		this.member_email = member_email;
	}

	@Override
	public String toString() {
		return "Members [member_code=" + member_code + ", member_id=" + member_id + ", member_password="
				+ member_password + ", member_name=" + member_name + ", member_birthday=" + member_birthday
				+ ", member_gender=" + member_gender + ", district_code=" + district_code + ", member_ad_detail="
				+ member_ad_detail + ", member_phonenum=" + member_phonenum + ", member_email=" + member_email + "]";
	}

	public Integer getMember_code() {
		return member_code;
	}

	public void setMember_code(Integer member_code) {
		this.member_code = member_code;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getMember_password() {
		return member_password;
	}

	public void setMember_password(String member_password) {
		this.member_password = member_password;
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

	public String getMember_gender() {
		return member_gender;
	}

	public void setMember_gender(String member_gender) {
		this.member_gender = member_gender;
	}

	public Integer getDistrict_code() {
		return district_code;
	}

	public void setDistrict_code(Integer district_code) {
		this.district_code = district_code;
	}

	public String getMember_ad_detail() {
		return member_ad_detail;
	}

	public void setMember_ad_detail(String member_ad_detail) {
		this.member_ad_detail = member_ad_detail;
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
	
	
}
