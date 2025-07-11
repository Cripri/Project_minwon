package function.connector;

import java.util.Date;

public class Simple_doc {
	Integer simple_doc_code;
	String complaint_category_code;
	Integer district_code;
	String status;
	Date create_date;
	Date complete_date;
	Integer	member_code;
	String member_name_en;
	String member_name_hanja;
	Integer count;

	//과거 주소 변동 사항
	String include_address_history;
	Integer address_history_years;

	//단순 포함 항목
	String include_household_reason; // 세대 구성 사유 포함
	String include_household_date;  // 세대 구성 일자 포함
	String include_occurrence_date; // 발생일 / 신고일 포함
	String include_head_name; // 세대주 성명 포함
	String include_head_relationship; // 세대주와의 관계 포함
	String include_personal_change_details; // 인적사항 변경 내용 포함
	String include_id_number ; // 국내/외국인 등록번호 포함
	//  -- 주민등록번호 뒷자리
	String include_rrn_last7;
	String include_rrn_last7_self;
	String include_rrn_last7_member;
	//			-- 병역사항
	String include_military_service; // 항목 자체 포함 여부
	String military_service_basic_only; // 입영/전역일자만 포함
	String military_service_full; // 병역 전체정보 포함
	//  -- 과거 주소
	String include_previous_address;
	String include_previous_address_self;
	String include_previous_address_member;

	public Simple_doc() {
	}

	public Simple_doc(Integer simple_doc_code, String complaint_category_code, Integer district_code, String status, Date create_date, Date complete_date, Integer member_code, String member_name_en, String member_name_hanja, Integer count, String include_address_history, Integer address_history_years, String include_household_reason, String include_household_date, String include_occurrence_date, String include_head_name, String include_head_relationship, String include_personal_change_details, String include_id_number, String include_rrn_last7, String include_rrn_last7_self, String include_rrn_last7_member, String include_military_service, String military_service_basic_only, String military_service_full, String include_previous_address, String include_previous_address_self, String include_previous_address_member) {
		this.simple_doc_code = simple_doc_code;
		this.complaint_category_code = complaint_category_code;
		this.district_code = district_code;
		this.status = status;
		this.create_date = create_date;
		this.complete_date = complete_date;
		this.member_code = member_code;
		this.member_name_en = member_name_en;
		this.member_name_hanja = member_name_hanja;
		this.count = count;
		this.include_address_history = include_address_history;
		this.address_history_years = address_history_years;
		this.include_household_reason = include_household_reason;
		this.include_household_date = include_household_date;
		this.include_occurrence_date = include_occurrence_date;
		this.include_head_name = include_head_name;
		this.include_head_relationship = include_head_relationship;
		this.include_personal_change_details = include_personal_change_details;
		this.include_id_number = include_id_number;
		this.include_rrn_last7 = include_rrn_last7;
		this.include_rrn_last7_self = include_rrn_last7_self;
		this.include_rrn_last7_member = include_rrn_last7_member;
		this.include_military_service = include_military_service;
		this.military_service_basic_only = military_service_basic_only;
		this.military_service_full = military_service_full;
		this.include_previous_address = include_previous_address;
		this.include_previous_address_self = include_previous_address_self;
		this.include_previous_address_member = include_previous_address_member;
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

	public Integer getMember_code() {
		return member_code;
	}

	public void setMember_code(Integer member_code) {
		this.member_code = member_code;
	}

	public String getMember_name_en() {
		return member_name_en;
	}

	public void setMember_name_en(String member_name_en) {
		this.member_name_en = member_name_en;
	}

	public String getMember_name_hanja() {
		return member_name_hanja;
	}

	public void setMember_name_hanja(String member_name_hanja) {
		this.member_name_hanja = member_name_hanja;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getInclude_address_history() {
		return include_address_history;
	}

	public void setInclude_address_history(String include_address_history) {
		this.include_address_history = include_address_history;
	}

	public Integer getAddress_history_years() {
		return address_history_years;
	}

	public void setAddress_history_years(Integer address_history_years) {
		this.address_history_years = address_history_years;
	}

	public String getInclude_household_reason() {
		return include_household_reason;
	}

	public void setInclude_household_reason(String include_household_reason) {
		this.include_household_reason = include_household_reason;
	}

	public String getInclude_household_date() {
		return include_household_date;
	}

	public void setInclude_household_date(String include_household_date) {
		this.include_household_date = include_household_date;
	}

	public String getInclude_occurrence_date() {
		return include_occurrence_date;
	}

	public void setInclude_occurrence_date(String include_occurrence_date) {
		this.include_occurrence_date = include_occurrence_date;
	}

	public String getInclude_head_name() {
		return include_head_name;
	}

	public void setInclude_head_name(String include_head_name) {
		this.include_head_name = include_head_name;
	}

	public String getInclude_head_relationship() {
		return include_head_relationship;
	}

	public void setInclude_head_relationship(String include_head_relationship) {
		this.include_head_relationship = include_head_relationship;
	}

	public String getInclude_personal_change_details() {
		return include_personal_change_details;
	}

	public void setInclude_personal_change_details(String include_personal_change_details) {
		this.include_personal_change_details = include_personal_change_details;
	}

	public String getInclude_id_number() {
		return include_id_number;
	}

	public void setInclude_id_number(String include_id_number) {
		this.include_id_number = include_id_number;
	}

	public String getInclude_rrn_last7() {
		return include_rrn_last7;
	}

	public void setInclude_rrn_last7(String include_rrn_last7) {
		this.include_rrn_last7 = include_rrn_last7;
	}

	public String getInclude_rrn_last7_self() {
		return include_rrn_last7_self;
	}

	public void setInclude_rrn_last7_self(String include_rrn_last7_self) {
		this.include_rrn_last7_self = include_rrn_last7_self;
	}

	public String getInclude_rrn_last7_member() {
		return include_rrn_last7_member;
	}

	public void setInclude_rrn_last7_member(String include_rrn_last7_member) {
		this.include_rrn_last7_member = include_rrn_last7_member;
	}

	public String getInclude_military_service() {
		return include_military_service;
	}

	public void setInclude_military_service(String include_military_service) {
		this.include_military_service = include_military_service;
	}

	public String getMilitary_service_basic_only() {
		return military_service_basic_only;
	}

	public void setMilitary_service_basic_only(String military_service_basic_only) {
		this.military_service_basic_only = military_service_basic_only;
	}

	public String getMilitary_service_full() {
		return military_service_full;
	}

	public void setMilitary_service_full(String military_service_full) {
		this.military_service_full = military_service_full;
	}

	public String getInclude_previous_address() {
		return include_previous_address;
	}

	public void setInclude_previous_address(String include_previous_address) {
		this.include_previous_address = include_previous_address;
	}

	public String getInclude_previous_address_self() {
		return include_previous_address_self;
	}

	public void setInclude_previous_address_self(String include_previous_address_self) {
		this.include_previous_address_self = include_previous_address_self;
	}

	public String getInclude_previous_address_member() {
		return include_previous_address_member;
	}

	public void setInclude_previous_address_member(String include_previous_address_member) {
		this.include_previous_address_member = include_previous_address_member;
	}
}

