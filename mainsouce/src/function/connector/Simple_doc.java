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
	Integer doc_count;

	String all_Included; // 전부 포함

	//과거 주소 변동 사항
	String address_history;
	Integer address_history_years;

	//단순 포함 항목
	String household_reason; // 세대 구성 사유 포함
	String household_date;  // 세대 구성 일자 포함
	String occurrence_date; // 발생일 / 신고일 포함
	String head_name; // 세대주 성명 포함
	String roommate;
	String head_relationship; // 세대주와의 관계 포함
	String personal_change_details; // 인적사항 변경 내용 포함
	String id_number ; // 국내/외국인 등록번호 포함
	//  -- 주민등록번호 뒷자리
	String rrn_last7;
	String rrn_last7_self;
	String rrn_last7_member;
	//			-- 병역사항
	String military_service; // 항목 자체 포함 여부
	String military_service_basic_only; // 입영/전역일자만 포함
	String military_service_full; // 병역 전체정보 포함
	//  -- 과거 주소
	String previous_address;
	String previous_address_self;
	String previous_address_member;

	public Simple_doc() {
	}

	public Simple_doc(Integer simple_doc_code, String complaint_category_code, Integer district_code, String status, Date create_date, Date complete_date, Integer member_code, String member_name_en, String member_name_hanja, Integer count, String all_Included, String address_history, Integer address_history_years, String household_reason, String household_date, String occurrence_date, String head_name, String head_relationship, String personal_change_details, String id_number, String rrn_last7, String rrn_last7_self, String rrn_last7_member, String military_service, String military_service_basic_only, String military_service_full, String previous_address, String previous_address_self, String previous_address_member) {
		this.simple_doc_code = simple_doc_code;
		this.complaint_category_code = complaint_category_code;
		this.district_code = district_code;
		this.status = status;
		this.create_date = create_date;
		this.complete_date = complete_date;
		this.member_code = member_code;
		this.member_name_en = member_name_en;
		this.member_name_hanja = member_name_hanja;
		this.doc_count = count;
		this.all_Included = all_Included;
		this.address_history = address_history;
		this.address_history_years = address_history_years;
		this.household_reason = household_reason;
		this.household_date = household_date;
		this.occurrence_date = occurrence_date;
		this.head_name = head_name;
		this.head_relationship = head_relationship;
		this.personal_change_details = personal_change_details;
		this.id_number = id_number;
		this.rrn_last7 = rrn_last7;
		this.rrn_last7_self = rrn_last7_self;
		this.rrn_last7_member = rrn_last7_member;
		this.military_service = military_service;
		this.military_service_basic_only = military_service_basic_only;
		this.military_service_full = military_service_full;
		this.previous_address = previous_address;
		this.previous_address_self = previous_address_self;
		this.previous_address_member = previous_address_member;
	}

	@Override
	public String toString() {
		return "Simple_doc{" +
				"simple_doc_code=" + simple_doc_code +
				", complaint_category_code='" + complaint_category_code + '\'' +
				", district_code=" + district_code +
				", status='" + status + '\'' +
				", create_date=" + create_date +
				", complete_date=" + complete_date +
				", member_code=" + member_code +
				", member_name_en='" + member_name_en + '\'' +
				", member_name_hanja='" + member_name_hanja + '\'' +
				", doc_count=" + doc_count +
				", all_Included='" + all_Included + '\'' +
				", address_history='" + address_history + '\'' +
				", address_history_years=" + address_history_years +
				", household_reason='" + household_reason + '\'' +
				", household_date='" + household_date + '\'' +
				", occurrence_date='" + occurrence_date + '\'' +
				", head_name='" + head_name + '\'' +
				", roommate='" + roommate + '\'' +
				", head_relationship='" + head_relationship + '\'' +
				", personal_change_details='" + personal_change_details + '\'' +
				", id_number='" + id_number + '\'' +
				", rrn_last7='" + rrn_last7 + '\'' +
				", rrn_last7_self='" + rrn_last7_self + '\'' +
				", rrn_last7_member='" + rrn_last7_member + '\'' +
				", military_service='" + military_service + '\'' +
				", military_service_basic_only='" + military_service_basic_only + '\'' +
				", military_service_full='" + military_service_full + '\'' +
				", previous_address='" + previous_address + '\'' +
				", previous_address_self='" + previous_address_self + '\'' +
				", previous_address_member='" + previous_address_member + '\'' +
				'}';
	}

	public String getRoommate() {
		return roommate;
	}

	public void setRoommate(String roommate) {
		this.roommate = roommate;
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

	public Integer getDoc_count() {
		return doc_count;
	}

	public void setDoc_count(Integer doc_count) {
		this.doc_count = doc_count;
	}

	public String getAll_Included() {
		return all_Included;
	}

	public void setAll_Included(String all_Included) {
		this.all_Included = all_Included;
	}

	public String getaddress_history() {
		return address_history;
	}

	public void setaddress_history(String address_history) {
		this.address_history = address_history;
	}

	public Integer getAddress_history_years() {
		return address_history_years;
	}

	public void setAddress_history_years(Integer address_history_years) {
		this.address_history_years = address_history_years;
	}

	public String gethousehold_reason() {
		return household_reason;
	}

	public void sethousehold_reason(String household_reason) {
		this.household_reason = household_reason;
	}

	public String gethousehold_date() {
		return household_date;
	}

	public void sethousehold_date(String household_date) {
		this.household_date = household_date;
	}

	public String getoccurrence_date() {
		return occurrence_date;
	}

	public void setoccurrence_date(String occurrence_date) {
		this.occurrence_date = occurrence_date;
	}

	public String gethead_name() {
		return head_name;
	}

	public void sethead_name(String head_name) {
		this.head_name = head_name;
	}

	public String gethead_relationship() {
		return head_relationship;
	}

	public void sethead_relationship(String head_relationship) {
		this.head_relationship = head_relationship;
	}

	public String getpersonal_change_details() {
		return personal_change_details;
	}

	public void setpersonal_change_details(String personal_change_details) {
		this.personal_change_details = personal_change_details;
	}

	public String getid_number() {
		return id_number;
	}

	public void setid_number(String id_number) {
		this.id_number = id_number;
	}

	public String getrrn_last7() {
		return rrn_last7;
	}

	public void setrrn_last7(String rrn_last7) {
		this.rrn_last7 = rrn_last7;
	}

	public String getrrn_last7_self() {
		return rrn_last7_self;
	}

	public void setrrn_last7_self(String rrn_last7_self) {
		this.rrn_last7_self = rrn_last7_self;
	}

	public String getrrn_last7_member() {
		return rrn_last7_member;
	}

	public void setrrn_last7_member(String rrn_last7_member) {
		this.rrn_last7_member = rrn_last7_member;
	}

	public String getmilitary_service() {
		return military_service;
	}

	public void setmilitary_service(String military_service) {
		this.military_service = military_service;
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

	public String getprevious_address() {
		return previous_address;
	}

	public void setprevious_address(String previous_address) {
		this.previous_address = previous_address;
	}

	public String getprevious_address_self() {
		return previous_address_self;
	}

	public void setprevious_address_self(String previous_address_self) {
		this.previous_address_self = previous_address_self;
	}

	public String getprevious_address_member() {
		return previous_address_member;
	}

	public void setprevious_address_member(String previous_address_member) {
		this.previous_address_member = previous_address_member;
	}
}

