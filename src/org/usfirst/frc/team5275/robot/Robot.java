
package org.usfirst.frc.team5275.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team5275.robot.subsystems.*;
import org.usfirst.frc.team5275.robot.commands.*;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
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
	public SeatMotor wrist = new SeatMotor();
	public static Arm armsystem = new Arm();
	UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
	public static NetworkTable nt;
	
	@Override
	public void robotInit() {
		// set up our robot
		oi = new OI();
		DriveTrain.initialize();
		wrist.initialize();
		armsystem.initialize();

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
		
		// our intake
        if (oi.driveStick.getRawButton(1))
          armsystem.stop();
        if (oi.driveStick.getRawButton(7))
          armsystem.rotateTo(20.0);
        if (oi.driveStick.getRawButton(9))
          armsystem.rotateTo(0.0);
        if (oi.driveStick.getRawButton(11))
          armsystem.rotateTo(-20.0);
        if (oi.driveStick.getRawButton(2))
          armsystem.rotateTo(-45.0);
        
        if (oi.driveStick.getRawButton(5))
          armsystem.intake.runFull();
        if (oi.driveStick.getRawButton(6))
          armsystem.intake.eject();
        if (oi.driveStick.getRawButton(3))
          armsystem.intake.hold();
        if (oi.driveStick.getRawButton(4))
          armsystem.intake.kill();

        if(oi.driveStick.getRawButton(10))
          wrist.motor.set(0);
        if(oi.driveStick.getRawButton(8))
          wrist.motor.set(wrist.speed);
        if(oi.driveStick.getRawButton(12))
          wrist.motor.set(-wrist.speed);
		
        armsystem.tick();
        wrist.tick();
        
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
