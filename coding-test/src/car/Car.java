package car;

public class Car {
	private PositionStatus pos;
	private Movements move;
	
	public Car(PositionStatus pos) {
		this.pos = pos;
		move = new Movements();
	}
	
	public void setPos (PositionStatus pos) {
		this.pos = pos;
	}
	public PositionStatus getPos() {
		return pos;
	}
	
	public void moveForward() {
		move.moveForward(pos);
	}
	
	public void turnLeft() {
		move.turnLeft(pos);
	}
	
	public void turnRight() {
		move.turnRight(pos);
	}
}
