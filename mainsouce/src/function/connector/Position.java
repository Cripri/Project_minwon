package function.connector;

public class Position {
	Integer position_code;
	String position_name;
	
	public Position(Integer position_code, String position_name) {
		this.position_code = position_code;
		this.position_name = position_name;
	}

	public Position() {
	}

	@Override
	public String toString() {
		return "Position [position_code=" + position_code + ", position_name=" + position_name + "]";
	}

	public Integer getPosition_code() {
		return position_code;
	}

	public void setPosition_code(Integer position_code) {
		this.position_code = position_code;
	}

	public String getPosition_name() {
		return position_name;
	}

	public void setPosition_name(String position_name) {
		this.position_name = position_name;
	}
	
	
}
