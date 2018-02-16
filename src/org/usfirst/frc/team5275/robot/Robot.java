
package org.usfirst.frc.team5275.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team5275.robot.subsystems.*;
import org.usfirst.frc.team5275.robot.commands.*;
import org.usfirst.frc.team5275.robot.subsystems.SeatMotor;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	public static DriveTrain drivetrain = new DriveTrain();
	Command driveCommand = new TeleopDrive();
	public SeatMotor wristMotor = new SeatMotor();
	public static Arm armsystem = new Arm();
	UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();

	@Override
	public void robotInit() {
		// set up our robot
		oi = new OI();
		drivetrain.initialize();
		wristMotor.initialize(RobotMap.wristMotor);

	}


	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {

		if (autonomousCommand != null)
			autonomousCommand.cancel();
		
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		driveCommand.start();
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
