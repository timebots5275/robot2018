package org.usfirst.frc.team5275.robot.subsystems;

import org.usfirst.frc.team5275.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
/**
 *
 */
public class Arm extends Subsystem {
	
	// defining our drive motors
	public static WPI_VictorSPX leadScrew = new WPI_VictorSPX(RobotMap.leadScrew);
	public static WPI_VictorSPX armJoint = new WPI_VictorSPX(RobotMap.armJoint);
	public static Solenoid clampPiston = new Solenoid(RobotMap.clampPiston);
	
	
	public static void initialize() {
		// initialize all our stuff
		System.out.println("Arm initialized");
	}
	
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
