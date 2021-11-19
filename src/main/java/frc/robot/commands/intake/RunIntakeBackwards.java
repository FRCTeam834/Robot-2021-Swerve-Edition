/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.intake;

/**
 * @author Christian Piper (@CAP1Sup)
 * @since 5/26/21
 */

// Imports
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class RunIntakeBackwards extends CommandBase {
  /** Creates a new RunIntake. */
  public RunIntakeBackwards() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.intake.runBackward();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.intake.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
