package org.usfirst.frc.team5275.robot;

/**
 * map of ports used
 */
public class RobotMap {

	
	// these integers represent the IDs of the CAN controllers
	public static int frontLeftCAN = 0;
	public static int frontRightCAN = 1;
	public static int rearLeftCAN = 2;
	public static int rearRightCAN = 3;
	
	// Other Motor Controllers
	public static int leadScrew = 0;
	public static int wristMotor = 1;
	public static int[] intake = {2,3};
	
	// IO ports
	public static int potentiometerPort = 3; // Analog 1
	public static int upSwitch = 2; // Digital 2
	public static int downSwitch = 1; // Digital 1
}
