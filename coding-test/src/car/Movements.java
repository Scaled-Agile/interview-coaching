package car;

public class Movements {
	public void moveForward(PositionStatus pos) {
		if(pos == null) {
			return;
		}
		switch(pos.getOrientation()) {
			case NORTH:
				if(pos.getY() < 4) {
					pos.setY(pos.getY() + 1);
				}
				break;
			case SOUTH:
				if(pos.getY() > 0) {
					pos.setY(pos.getY() - 1);
				}
				break;
			case WEST:
				if(pos.getX() > 0) {
					pos.setX(pos.getX() - 1);
				}
				break;
			case EAST:
				if(pos.getX() < 4) {
					pos.setX(pos.getX() + 1);
				}
				break;
			default:
				break;
		}
	}
	
	public void turnRight(PositionStatus pos) {
		if(pos == null) {
			return;
		}
		
		switch(pos.getOrientation()) {
			case NORTH:
				pos.setOrientation(Orientation.EAST);
				break;
			case SOUTH:
				pos.setOrientation(Orientation.WEST);
				break;
			case WEST:
				pos.setOrientation(Orientation.NORTH);
				break;
			case EAST:
				pos.setOrientation(Orientation.SOUTH);
				break;
			default:
				break;
		}
	}
	
	public void turnLeft(PositionStatus pos) {
		if(pos == null) {
			return;
		}
		
		switch(pos.getOrientation()) {
			case NORTH:
				pos.setOrientation(Orientation.WEST);
				break;
			case SOUTH:
				pos.setOrientation(Orientation.EAST);
				break;
			case WEST:
				pos.setOrientation(Orientation.SOUTH);
				break;
			case EAST:
				pos.setOrientation(Orientation.NORTH);
				break;
			default:
				break;
		}
	}
}
