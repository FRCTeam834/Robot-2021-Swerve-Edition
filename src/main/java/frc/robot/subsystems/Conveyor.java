/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

// Parameters
import frc.robot.Parameters;

// CTRE libraries
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

// WPI libraries
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Conveyor extends SubsystemBase {
  /**
   * Creates a new Conveyor.
   */

  // Create all of the new objects
  WPI_VictorSPX conveyorMotor = new WPI_VictorSPX(Parameters.conveyor.MOTOR_ID);
  DigitalInput bottomSensor = new DigitalInput(Parameters.conveyor.BALL_SENSOR_PORT);
  DigitalInput topSensor = new DigitalInput(Parameters.conveyor.EMPTY_SENSOR_PORT);

  public Conveyor() {

    // Set the direction of the motor
    conveyorMotor.setInverted(Parameters.conveyor.INVERTED);
  }

  @Override
  public void periodic() {
    // This method will be called once per schedule run
  }

  // Gets the value of the bottom sensor. Returns true if triggered, false if not
  public boolean getBottomSensor() {
    return bottomSensor.get();
  }

  // Gets the value of the top sensor. Returns true if triggered, false if not
  public boolean getTopSensor() {
    return topSensor.get();
  }

  // Sets the conveyor to run at desired percentage
  public void setSpeed(double speed) {
    conveyorMotor.set(speed);
  }

  // Stops the conveyor
  public void stop() {
    conveyorMotor.set(0);
  }
}
