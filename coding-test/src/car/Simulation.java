package car;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Simulation {
	private Car car;
	
	public Simulation() {
		car = null;
	}
	
	public void initSimulation(String path) {
		// Read command from either standard input or a file
		Scanner sc = null;
		if(path == null) {	// Read from standard input
			sc = new Scanner(System.in);
		} else {	// Read from file
			try {
				File file = new File(path);
				sc = new Scanner(file);
			} catch (FileNotFoundException ex) {
				System.out.println(ex.toString());
				return;
			}
		}
		try {
			// Keep reading command until reaching the end of file or user terminate the programme
			while(sc.hasNextLine()) {
				// Read line by line and execute each command
				String str = sc.nextLine();
				if(str.startsWith(Command.INIT.toString())) {
					initPosition(str);
				} else if(str.startsWith(Command.FORWARD.toString())) {
					car.moveForward();
				} else if(str.startsWith(Command.TURN_LEFT.toString())) {
					car.turnLeft();
				} else if(str.startsWith(Command.TURN_RIGHT.toString())) {
					car.turnRight();
				} else if(str.startsWith(Command.GPS_REPORT.toString())) {
					gpsReport();
				} else {
					break;
				}
			}
		} finally {
			if(sc != null) {
				sc.close();
			}
		}
	}
	
	private void initPosition(String str) {
		String args[] = str.split("\\s");
		String command = args[0];
		if(!command.equals(Command.INIT.toString())) {
			return;
		}
		
		String para[] = args[1].split(",");
		int x, y;
		try {
			x = Integer.parseInt(para[0]);
			y = Integer.parseInt(para[1]);
		} catch (NumberFormatException ex) {
			return;
		}
		Orientation orientation = parseOrientation(para[2]);
		
		// Check if it is a valid position
		if(!validateCoordination(x, y)) {
			return;
		}
		
		if(car == null) {
			car = new Car(new PositionStatus(x, y, orientation));
		} else {
			car.setPos(new PositionStatus(x, y, orientation));
		}
	}
	
	private Orientation parseOrientation(String orientation) {
		Orientation orien = null;
		if(orientation.equals("NORTH")) {
			orien = Orientation.NORTH;
		} else if(orientation.equals("SOUTH")) {
			orien = Orientation.SOUTH;
		} else if(orientation.equals("WEST")) {
			orien = Orientation.WEST;
		} else if(orientation.equals("EAST")) {
			orien = Orientation.EAST;
		}
		
		return orien;
	}
	
	private void gpsReport() {
		if(car != null) {
			System.out.println(car.getPos());
		}
	}
	
	private boolean validateCoordination(int x, int y) {
		if((x >= 0) && (x < 5)) {
			if((y >= 0) && (y < 5)) {
				return true;
			}
		}
		return false;
	}
	
	public Car getCar() {
		return car;
	}
}
