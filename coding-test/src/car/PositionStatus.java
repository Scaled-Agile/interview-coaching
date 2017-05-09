package car;

public class PositionStatus {
	// Coordination in the grid
	private int x, y;
	private Orientation orientation;
	
	public PositionStatus(int x, int y, Orientation orientation) {
		this.x = x;
		this.y = y;
		this.orientation = orientation;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Orientation getOrientation() {
		return orientation;
	}

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}
	
	public String toString() {
		String str = x + "," + y + "," + orientation.toString();
		return str;
	}
}
