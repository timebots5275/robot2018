package org.usfirst.frc.team5275.robot.subsystems;

import org.usfirst.frc.team5275.robot.Robot;
import org.usfirst.frc.team5275.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 *
 */
public class Arm extends Subsystem {
	
	// defining our drive motors
	public static WPI_VictorSPX leadScrew = new WPI_VictorSPX(RobotMap.leadScrew);
	public static AnalogInput input = new AnalogInput(RobotMap.potentiometerPort);
	public static AnalogPotentiometer pot;
	public double speed = 0.5;
	public boolean isMoving = false;
	public static double position = 0.0;
	
	
	
	public static void initialize() {
		// initialize all our stuff
		System.out.println("Arm initialized");
		pot = new AnalogPotentiometer(input, 180, 0);
		position = pot.get();
	}
	
	private void setMotor(double speed) {
	   if (speed == 0) 
	     isMoving = false;
	   else 
	     isMoving = true;
	   leadScrew.set(speed);
	   System.out.println("Motor moving: " + isMoving);
	}
	
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public void rotateTo(double desiredPosition) {
	  double potVal = 0;
	  double locSpeed = speed;
	  double difference = 0.0;
	  if (desiredPosition < pot.get()) {
	    locSpeed = -speed;
	  }
	  // as long as the difference is within two degrees and we're supposed to be moving
	  while ((difference < 2 && difference > -2) && isMoving) {
	    setMotor(locSpeed);
	    potVal = pot.get();
	    position = pot.get();
	    difference = desiredPosition - position;
	  }
	  setMotor(0);
	}
	
	public void initDefaultCommand() {}
}
