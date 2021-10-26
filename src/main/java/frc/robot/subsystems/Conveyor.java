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
  DigitalInput ballSensor = new DigitalInput(Parameters.conveyor.BALL_SENSOR_PORT);

  public Conveyor() {

    // Set the direction of the motor
    conveyorMotor.setInverted(Parameters.conveyor.INVERTED);
  }

  @Override
  public void periodic() {
    // This method will be called once per schedule run
  }

  // Gets the value of the ball sensor. Returns true if triggered, false if not
  public boolean getBallSensor() {
    return ballSensor.get();
  }

  // Sets the conveyor to run at desired percentage
  public void setSpeed(double speed) {
    conveyorMotor.set(speed);
  }

  // Runs the conveyor forward (loads balls in)
  public void runForward() {
    conveyorMotor.set(Parameters.conveyor.FORWARD_SPEED);
  }

  // Runs the conveyor backward (ejects balls)
  public void runBackward() {
    conveyorMotor.set(Parameters.conveyor.BACKWARD_SPEED);
  }

  // Stops the conveyor
  public void stop() {
    conveyorMotor.stopMotor();
  }
}
