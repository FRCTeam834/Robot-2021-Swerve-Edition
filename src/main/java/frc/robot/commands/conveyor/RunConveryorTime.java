// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.conveyor;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class RunConveryorTime extends CommandBase {
  /** Creates a new RunConveryorTime. */
  Timer timer = new Timer();
  double time;
  public RunConveryorTime(double time) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.time = time;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Robot.conveyor.runForward();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

    // Stop the conveyor motor once finished
    Robot.conveyor.stop();

    // Reset the ball count
    Parameters.conveyor.BALL_COUNT = 0;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timer.hasElapsed(time);
  }
}
