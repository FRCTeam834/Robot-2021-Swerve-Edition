/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.conveyor;

// Import Parameters
import frc.robot.Parameters;

// Import Robot
import frc.robot.Robot;

// WPI libraries
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunConveyor extends CommandBase {
  /**
   * Creates a new RunConveyor.
   */

  public RunConveyor() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.conveyor);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    // Set the conveyor to the correct speed
    Robot.conveyor.runForward();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

    // Stop the motor, we're finished
    Robot.conveyor.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
