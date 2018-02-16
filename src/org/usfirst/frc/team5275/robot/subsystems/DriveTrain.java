package org.usfirst.frc.team5275.robot.subsystems;

import org.usfirst.frc.team5275.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
/**
 *
 */
public class DriveTrain extends Subsystem {
	
	// defining our drive motors
	public static WPI_TalonSRX frontLeft = new WPI_TalonSRX(RobotMap.frontLeftCAN);
	public static WPI_TalonSRX rearLeft = new WPI_TalonSRX(RobotMap.rearLeftCAN);
	public static WPI_TalonSRX frontRight = new WPI_TalonSRX(RobotMap.frontRightCAN);
	public static WPI_TalonSRX rearRight = new WPI_TalonSRX(RobotMap.rearRightCAN);
	static SpeedControllerGroup m_left = new SpeedControllerGroup(frontLeft,rearLeft);
	static SpeedControllerGroup m_right = new SpeedControllerGroup(frontRight,rearRight);
	// defining our DifferentialDrive object
	public static DifferentialDrive drive = new DifferentialDrive(m_left, m_right);
	// method runs at robot init, to start the drivetrain
	
	public static void initialize() {
		// initialize all our stuff

		System.out.println("Drive Train initialized");
	}
	
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
