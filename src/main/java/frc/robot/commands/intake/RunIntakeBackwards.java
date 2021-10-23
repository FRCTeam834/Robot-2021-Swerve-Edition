/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.intake;

// Robot
import frc.robot.Robot;

// Parameters
import frc.robot.Parameters;

// WPI libraries
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunIntakeBackwards extends CommandBase {
  /**
   * Creates a new RunIntake.
   */
  public RunIntakeBackwards() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.ballIntake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    Robot.ballIntake.setSpeed(-Parameters.intake.EXPORT_SPEED);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.ballIntake.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}