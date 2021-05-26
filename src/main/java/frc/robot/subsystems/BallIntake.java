/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

// Parameters
import frc.robot.Parameters;

// CTRE Victor library
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

// WPI libraries
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BallIntake extends SubsystemBase {
  /**
   * Creates a new BallIntake.
   */

  // Main intake motor declaration
  WPI_VictorSPX intakeMotor = new WPI_VictorSPX(Parameters.intake.MOTOR_ID);

  public BallIntake() {
    // Set if the intake should be inverted
    intakeMotor.setInverted(Parameters.intake.INVERTED);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  // Start the intake at desired speed
  public void setSpeed(double speed) {

    // Just set the intake to the correct speed
    intakeMotor.set(speed);
  }

  // Stop the intake (by setting it to zero)
  public void stop() {
    intakeMotor.set(0);
  }

}
