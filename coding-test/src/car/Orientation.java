package car;

public enum Orientation {
	NORTH("NORTH"),
	SOUTH("SOUTH"),
	EAST("EAST"),
	WEST("WEST");
	
	private String str;
	
	private Orientation(String str) {
		this.str = str;
	}
	
	public String getOrientation() {
		return str;
	}
}
