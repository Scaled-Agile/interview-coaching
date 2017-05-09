package car;

public enum Command {
	INIT("INIT"),
	FORWARD("FORWARD"),
	TURN_LEFT("TURN_LEFT"),
	TURN_RIGHT("TURN_RIGHT"),
	GPS_REPORT("GPS_REPORT");
	
	private String command;
	
	Command(String str) {
		this.command = str;
	}
	
	public String getCommand() {
		return this.command;
	}
}
