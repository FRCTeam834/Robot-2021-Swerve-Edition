/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

// Parameters
import frc.robot.Parameters;

// Rev libraries
import com.revrobotics.CANSparkMax;

// WPI libraries
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Shooter extends SubsystemBase {
  /**
   * Creates a new Shooter.
   */

  // Create new object for shooter motor
  CANSparkMax shootMotor = new CANSparkMax(Parameters.shooter.MOTOR_ID, CANSparkMax.MotorType.kBrushless);

  public Shooter() {
    // Set the inversion
    shootMotor.setInverted(Parameters.shooter.INVERTED);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  // Sets the speed of the shooter motor in percent (0-1)
  public void setSpeed(double speed) {
    shootMotor.set(speed);
  }
  
  // Sets the voltage of the shooter motor in voltage
  public void setVoltage(double voltage) {
    shootMotor.setVoltage(voltage);
  }

  // Returns the encoder object of the Neo motor for shooting
  public double getVelocity() {
    return shootMotor.getEncoder().getVelocity();
  }

  // Returns the shooter motor's temperature in Celsius
  public double getMotorTemp() {
    return shootMotor.getMotorTemperature();
  }

  // Halts the shooter motor
  public void stop() {
    shootMotor.set(0);
  }
}
