/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous;

// Parameters
import frc.robot.Parameters;

// Robot
import frc.robot.Robot;

// WPI libraries
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;

public class EmptyShooterNoVision extends CommandBase {
  /**
   * Creates a new EmptyShooterNoVision.
   */

  // A variable to store the start time
  long startTime;

  // Timer
  Timer timer = new Timer();

  public EmptyShooterNoVision() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.shooter, Robot.conveyor);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    // Turn on the motors for the shooter and conveyor
    Robot.shooter.setVoltage(Parameters.shooter.WHEEL_VOLTAGE);
    Robot.conveyor.setSpeed(Parameters.conveyor.AUTON_SPEED);

    // Start the timer
    timer.reset();
    timer.start();
  }
  

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

    // Stop both of the motors
    Robot.shooter.stop();
    Robot.conveyor.stop();

    // Reset the timer
    timer.stop();
    timer.reset();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timer.hasElapsed(Parameters.shooter.UNLOAD_TIME);
  }
}
