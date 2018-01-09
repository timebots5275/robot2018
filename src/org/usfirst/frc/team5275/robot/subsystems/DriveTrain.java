package org.usfirst.frc.team5275.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team5275.robot.RobotMap;


import com.ctre.phoenix.motorcontrol.can.*;
/**
 *
 */
public class DriveTrain extends Subsystem {
	private static RobotMap robotMap = new RobotMap();
	//defining our motors
	public WPI_TalonSRX frontLeft = new WPI_TalonSRX(robotMap.frontLeftCAN);
	public WPI_TalonSRX rearLeft = new WPI_TalonSRX(robotMap.rearLeftCAN);
	public WPI_TalonSRX frontRight = new WPI_TalonSRX(robotMap.frontRightCAN);
	public WPI_TalonSRX rearRight = new WPI_TalonSRX(robotMap.rearRightCAN);
	
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
