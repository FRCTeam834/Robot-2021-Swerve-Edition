// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Parameters;
import frc.robot.Robot;

public class ClimberDown extends CommandBase {
  /** Creates a new ClimberDown. */
  boolean finished;
  public ClimberDown() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    finished = false;
    if(!Robot.climber.getLimitBottom()) {
    Robot.climber.down(Parameters.climber.CLIMBER_MOTOR_SPEED);
    } else {
    finished = true;
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(Robot.climber.getLimitBottom() == true) {
      finished = true;
      Robot.climber.stop();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.climber.stop();

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}