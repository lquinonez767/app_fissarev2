package ec.edu.ups.app.util;

public class Testing {
	
	private int code;
	private String description;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "Testing [code=" + code + ", description=" + description + "]";
	}
	
	public String Save() {
		return null;
	}
	
	
	
}
