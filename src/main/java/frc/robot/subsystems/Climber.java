/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Parameters;

public class Climber extends SubsystemBase {
  /**
   * Creates a new Climber.
   */
  WPI_VictorSPX climberMotor = new WPI_VictorSPX(Parameters.climber.CLIMBER_MOTOR_PORT);
  DigitalInput limitBottom = new DigitalInput(Parameters.climber.CLIMBER_LIMIT_SWITCH_PORT);

  public Climber() {
    climberMotor.setInverted(Parameters.climber.CLIMBER_INVERTED);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void up() {
    climberMotor.set(Parameters.climber.CLIMBER_MOTOR_SPEED);
  }

  public void down() {
    climberMotor.set(-Parameters.climber.CLIMBER_MOTOR_SPEED);
  }

  public void stop() {
    climberMotor.stopMotor();
  }

  // returns true if limit switch is being activated
  public boolean getLimitBottom() {
    return limitBottom.get();
  }

}