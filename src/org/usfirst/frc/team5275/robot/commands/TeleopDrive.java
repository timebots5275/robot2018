package org.usfirst.frc.team5275.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team5275.robot.Robot;

/**
 *
 */
public class TeleopDrive extends Command {
	public TeleopDrive() {
		// Use requires() here to declare subsystem dependencies
		//requires(Robot.ExampleSubsystem);
		requires(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		
	}

	//Scheduler.getInstance().run();

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		//TODO: check that these axes are correct
		Robot.drivetrain.drive.arcadeDrive(Robot.oi.driveStick.getRawAxis(1), Robot.oi.driveStick.getRawAxis(3));
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
