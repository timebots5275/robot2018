/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5275.robot.subsystems;

import org.usfirst.frc.team5275.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.command.Subsystem;

public class SeatMotor extends Subsystem {
	public Counter counter = new Counter();
	public WPI_VictorSPX motor = new WPI_VictorSPX(RobotMap.wristMotor);
	public double speed = 1.0;
	public double count = 0;
	private double desiredRotations = 0.0;
	private double currentSpeed = 0.0;
	private double currentRotations;
	// rotations per degree
	public double ratio = (174.9 / 360);
	
	public void initialize(int port){
		counter.setUpSource(0);
		counter.setUpDownCounterMode();
		counter.setDistancePerPulse(1);
		motor = new WPI_VictorSPX(port);
		counter.reset();
		if (speed <= 0) {
			System.out.println(" speed is less than or equal to zero: " + speed + " - ensure that this value is correct!");
		}
	}
	public void initialize(){
		counter.setUpSource(0);
		counter.setUpDownCounterMode();
		counter.setDistancePerPulse(1);
		counter.reset();
		if (speed <= 0) {
			System.out.println(" speed is less than or equal to zero: " + speed + " - ensure that this value is correct!");
		}
	}
	
	public void singleRotation(boolean forward) {
		count = 0;
		double locSpeed = speed;
		if (!forward) {
			locSpeed = -speed;
		}
		while (count < 1) {
			count = counter.getDistance();
			motor.set(locSpeed);
		}
		motor.set(0);
		count = 0;
	}
	
	
	public void rotateBy(int rotations) {
	    desiredRotations = rotations;
  		count = 0;
  		double locSpeed = speed;
  		currentSpeed = speed;
  		if (rotations < 0) {
  			locSpeed = -speed;
  			rotations = rotations * -1;
  		}
  		while (count < rotations) {
  			count = counter.getDistance();
  			motor.set(locSpeed);
  			System.out.println(count);
  		}
  		motor.set(0);
  		count = 0;
	}
	
	public void rotateTo(double degrees) {
		int rotations;
		rotations = (int) Math.round(degrees * ratio);
		desiredRotations = rotations;
	}
	
	public void tick() {
	  double currentDistance = counter.getDistance();
	  double delta = count - currentDistance;
	  System.out.println(delta + " " + currentDistance + " " + count);
	  if (currentSpeed < 0) 
	    currentRotations -= delta;
	  else if (currentSpeed > 0)
	    currentRotations += delta;
	  double difference = desiredRotations - currentRotations;
	  if (difference < 0) {
	    currentSpeed = -speed;
	  }
	  else {
	    currentSpeed = speed;
	  }
	  System.out.println("Difference: " + difference);
	  System.out.println("CurrentSpeed: " + currentSpeed);
	  System.out.println("Position: " + currentRotations);
	  if (!((difference < 2.0) && (difference > -2.0))){
	    motor.set(currentSpeed);
	  }
	  else {
	    motor.set(0.0);
	  }
	  count = currentDistance;
	}
	
	public void initDefaultCommand() {}
}