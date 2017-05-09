package car;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Simulation simulation = new Simulation();
		// Start simulation
		String filePath = null;
		if(args.length > 0) {	// Exist file path parameter
			filePath = args[0];
		}
		simulation.initSimulation(filePath);
	}
}
