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
import com.ctre.phoenix.motorcontrol.TalonSRXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

// WPI libraries
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Hood extends SubsystemBase {
  /**
   * Creates a new ShooterPivot.
   */

  // Create new motor and limit switch objects
  WPI_TalonSRX hoodMotor = new WPI_TalonSRX(Parameters.hood.MOTOR_ID);
  DigitalInput limitSwitch = new DigitalInput(Parameters.hood.LIMIT_SWITCH_PORT);

  // The desired angle of the pivot
  double desiredAngle = 0;

  // Main constructor
  public Hood() {

    // Setup the basic config of the motor
    hoodMotor.setInverted(Parameters.hood.INVERTED);
    hoodMotor.configSelectedFeedbackSensor(TalonSRXFeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);

    // PID loop settings
    // pivot.configClosedloopRamp(0.5);
    // pivot.config_kP(0, 0.125);
    // pivot.config_kI(0, 0);
    // pivot.config_kD(0, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  // Set the motor at the desired speed
  public void setSpeed(double speed) {
    hoodMotor.set(speed);
  }

  // Set the motor at the desired speed
  public void up() {
    hoodMotor.set(Parameters.hood.MOVEMENT_SPEED);
  }

  // Set the motor at the desired speed
  public void down() {
    hoodMotor.set(-Parameters.hood.MOVEMENT_SPEED);
  }

  // Tilts the shooter up by the desired angle
  public void tiltUp(double angleInterval) {

    // Increment the desired angle, then move there
    // setDesiredAngle(desiredAngle + angleInterval);
  }

  // Tilts the shooter down by the desired angle
  public void tiltDown(double angleInterval) {

    // Increment the desired angle, then move there
    // setDesiredAngle(desiredAngle - angleInterval);
  }

  /*
   * // Moves the pivot to the desired angle public void setDesiredAngle(double
   * desiredAngle) {
   *
   * // Save the new value this.desiredAngle = desiredAngle;
   *
   * // Set the motor to move to the new position
   * pivotMotor.set(ControlMode.Position, ((desiredAngle / 360) * 4096) /
   * Parameters.shooter.HOOD_GEAR_RATIO); }
   */

  // Halts the pivot
  public void stop() {
    hoodMotor.set(0);
  }

  // Returns the angle of the motor
  public double getCurrentMotorAngle() {

    // Returns the rotations of the sensor * 360 deg per rotation
    return ((hoodMotor.getSelectedSensorPosition() / 4096) * 360);
  }

  // Returns the angle of the hood
  public double getCurrentHoodAngle() {
    return getCurrentMotorAngle() * Parameters.hood.GEAR_RATIO;
  }

  // Returns if the limit switch is pressed
  public boolean getLimitSwitch() {
    return limitSwitch.get();
  }

  // Sets the encoder's reference back to zero
  public void resetEncoder() {
    hoodMotor.setSelectedSensorPosition(0);
  }
}
