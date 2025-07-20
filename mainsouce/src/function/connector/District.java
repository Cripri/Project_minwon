package function.connector;

import java.util.ArrayList;
import java.util.List;

import gui.mainframe.MainFrameState;

public class District {
	Integer district_code;
	Integer sd_code;
	String sd_name;
	Integer sgg_code;
	String sgg_name;
	
	public District(Integer district_code, Integer sd_code, String sd_name, Integer sgg_code, String sgg_name) {
		this.district_code = district_code;
		this.sd_code = sd_code;
		this.sd_name = sd_name;
		this.sgg_code = sgg_code;
		this.sgg_name = sgg_name;
	}

	public District() {
	}

	@Override
	public String toString() {
		return "District [district_code=" + district_code + ", sd_code=" + sd_code + ", sd_name=" + sd_name
				+ ", sgg_code=" + sgg_code + ", sgg_name=" + sgg_name + "]";
	}

	public Integer getDistrict_code() {
		return district_code;
	}

	public void setDistrict_code(Integer district_code) {
		this.district_code = district_code;
	}

	public Integer getSd_code() {
		return sd_code;
	}

	public void setSd_code(Integer sd_code) {
		this.sd_code = sd_code;
	}

	public String getSd_name() {
		return sd_name;
	}

	public void setSd_name(String sd_name) {
		this.sd_name = sd_name;
	}

	public Integer getSgg_code() {
		return sgg_code;
	}

	public void setSgg_code(Integer sgg_code) {
		this.sgg_code = sgg_code;
	}

	public String getSgg_name() {
		return sgg_name;
	}

	public void setSgg_name(String sgg_name) {
		this.sgg_name = sgg_name;
	}

}
