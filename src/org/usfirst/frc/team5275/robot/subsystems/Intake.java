package org.usfirst.frc.team5275.robot.subsystems;

import org.usfirst.frc.team5275.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

/**
 *
 */
public class Intake extends Subsystem {
  
  private WPI_VictorSPX intake0 = new WPI_VictorSPX(2);
  private WPI_VictorSPX intake1 = new WPI_VictorSPX(3);
  public double maxSpeed = 0.3;
  public double ejectSpeed = 1.0;
  public double holdSpeed = 0.1;
  
  public void initialize(int[] ports) {
    intake0 = new WPI_VictorSPX(ports[0]);
    intake1 = new WPI_VictorSPX(ports[1]);
    intake1.follow(intake0);
  }
  public void initialize() {
    intake1.follow(intake0);
  }
  
  public void runFull() {
    intake0.set(maxSpeed);
  }
  
  public void hold() {
    intake0.set(holdSpeed);
  }
  
  public void eject() {
    intake0.set(-ejectSpeed);
  }
  
  public void kill() {
    intake0.set(0);
  }
  
  
  public void initDefaultCommand() {}
}
