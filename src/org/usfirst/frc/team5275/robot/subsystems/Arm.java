package org.usfirst.frc.team5275.robot.subsystems;

import org.usfirst.frc.team5275.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 *
 */
public class Arm extends Subsystem {
    
    // defining our drive motors
    public static WPI_VictorSPX leadScrew = new WPI_VictorSPX(RobotMap.leadScrew);
    public static AnalogInput input = new AnalogInput(RobotMap.potentiometerPort);
    public static AnalogPotentiometer pot;
    public static double speed = 0.6;
    public boolean isMoving = true;
    public static double position = 0.0;
    public static Intake intake = new Intake();
    public DigitalInput downSwitch = new DigitalInput(RobotMap.downSwitch);
    public DigitalInput upSwitch = new DigitalInput(RobotMap.upSwitch);
    private static double targetPosition;
    private static double currentSpeed;
    
    
    public static void initialize() {
        // initialize all our stuff
        System.out.println("Arm initialized");
        pot = new AnalogPotentiometer(input, -270, 270 / 2);
        position = pot.get();
        targetPosition = pot.get();
        currentSpeed = speed;
        intake.initialize();
    }
    
    private void setMotor(double speed) {
//     if (speed == 0) 
//       isMoving = false;
//     else 
//       isMoving = true;
//      System.out.println("before-doctoring speed: " + speed);
       if (speed < 0 && !downSwitch.get()) {
         speed = 0;
       }
       else if (speed > 0 && !upSwitch.get()) {
         speed = 0;
       }
       leadScrew.set(speed);
//       System.out.println("Motor moving: " + isMoving + "  " + speed);
    }
    
    public void stop() {
      setMotor(0);
      isMoving = false;
      targetPosition = pot.get();
      System.err.println("Arm has been stopped! Watch arm carefully.");
    }
    
    public void reset() {
      setMotor(0);
      isMoving = true;
      targetPosition = pot.get();
      System.err.println("Arm re-enabled!");
      
    }
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void rotateTo(double desiredPosition) {
      targetPosition = desiredPosition;
    }
    
    public void tick() {
      position = pot.get();
      double difference = targetPosition - position;
//      System.out.println("tick called: " + targetPosition + " " + position + " " + difference);
      if (difference < 0.0) {
        System.out.println("speed inverted");
        currentSpeed = -speed;
      }
      else
        currentSpeed = speed;
      // as long as the difference is within two degrees and we're supposed to be moving
      if (!(difference < 2 && difference > -2) && isMoving)
        setMotor(currentSpeed);
      else
        setMotor(0);
    }
    
    
    
    public void initDefaultCommand() {}
}
