// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.conveyor;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Parameters;
import frc.robot.Robot;

public class EmptyConveyor extends CommandBase {

  // Timer (for delay before shooting)
  Timer timer = new Timer();

  /** Creates a new EmptyConveyor. */
  public EmptyConveyor() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.conveyor, Robot.shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    // Start the shooter, wait for it to start up, then begin firing
    Robot.shooter.startup();
    timer.reset();
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    // Check if the shooter is spun up
    if (timer.hasElapsed(Parameters.shooter.SPOOL_TIME)) {
      Robot.conveyor.runForward();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.shooter.stop();
    Robot.conveyor.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timer.hasElapsed(Parameters.shooter.SPOOL_TIME + Parameters.conveyor.UNLOAD_TIME);
  }
}
