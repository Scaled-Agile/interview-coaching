package test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import car.Simulation;

public class InitialisationTest {

	private Simulation sm;
	
	@Test
	public void testSingleValidInitialisation() {
		ByteArrayInputStream in = new ByteArrayInputStream("INIT 0,0,NORTH\n".getBytes());
		System.setIn(in);
		sm.initSimulation(null);
		assertEquals(0, sm.getCar().getPos().getX());
		assertEquals(0, sm.getCar().getPos().getY());
		assertEquals("NORTH", sm.getCar().getPos().getOrientation().toString());
	}
	
	@Test
	public void testSingleInvalidInitialisation() {
		ByteArrayInputStream in = new ByteArrayInputStream("INIT -1,0,NORTH\n".getBytes());
		System.setIn(in);
		sm.initSimulation(null);
		assertTrue(sm.getCar() == null);
	}
	
	@Test
	public void testMultipleValidInitialisation() {
		ByteArrayInputStream in = new ByteArrayInputStream(("INIT 0,0,NORTH\nGPS_REPORT\n"
															+ "INIT 4,0,SOUTH\nGPS_REPORT\n"
															+ "INIT 0,4,WEST\nGPS_REPORT\n"
															+ "INIT 4,4,EAST\nGPS_REPORT\n").getBytes());
		System.setIn(in);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		sm.initSimulation(null);
		
		assertEquals("0,0,NORTH" + System.lineSeparator()
				+ "4,0,SOUTH" + System.lineSeparator()
				+ "0,4,WEST" + System.lineSeparator()
				+ "4,4,EAST" + System.lineSeparator(), out.toString());
	}
	
	@Test
	public void testMultipleInvalidInitialisation() {
		ByteArrayInputStream in = new ByteArrayInputStream(("INIT -1,0,NORTH\nGPS_REPORT\n"
				+ "INIT 0,-1,SOUTH\nGPS_REPORT\n"
				+ "INIT 0,5,WEST\nGPS_REPORT\n"
				+ "INIT 5,5,EAST\nGPS_REPORT\n").getBytes());
		System.setIn(in);
		
		sm.initSimulation(null);
		assertTrue(sm.getCar() == null);
	}
	
	@Test
	public void testOutBoundaryMove() {
		ByteArrayInputStream in = new ByteArrayInputStream(("INIT 0,0,WEST\n"
				+ "FORWARD\n"
				+ "GPS_REPORT\n"
				+ "INIT 0,0,SOUTH\n"
				+ "FORWARD\n"
				+ "GPS_REPORT\n"
				+ "INIT 4,4,EAST\n"
				+ "FORWARD\n"
				+ "GPS_REPORT\n"
				+ "INIT 4,4,NORTH\n"
				+ "FORWARD\n"
				+ "GPS_REPORT\n").getBytes());
		System.setIn(in);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		
		sm.initSimulation(null);
		assertEquals("0,0,WEST" + System.lineSeparator()
					+ "0,0,SOUTH" + System.lineSeparator()
					+ "4,4,EAST" + System.lineSeparator()
					+ "4,4,NORTH" + System.lineSeparator(), out.toString());
	}
	
	@Test
	public void testFullCase() {
		ByteArrayInputStream in = new ByteArrayInputStream(("INIT 0,0,NORTH\n"
				+ "FORWARD\n"
				+ "TURN_RIGHT\n"
				+ "FORWARD\n"
				+ "TURN_LEFT\n"
				+ "FORWARD\n"
				+ "GPS_REPORT\n").getBytes());
		System.setIn(in);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		
		sm.initSimulation(null);
		assertEquals("1,2,NORTH" + System.lineSeparator(), out.toString());
	}
	
	@Before
	public void init() {
		sm = new Simulation();
	}
}
