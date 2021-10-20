/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
// ! Needs more work
package frc.robot.commands.conveyor;

import frc.robot.Parameters;
// Robot, RobotContainer
import frc.robot.Robot;
import frc.robot.RobotContainer;

// WPI libraries
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;

public class RunConveyorSensor extends CommandBase {
  /**
   * Creates a new RunConveyorSensor.
   */
  boolean sensorClear;
  Timer timer = new Timer();

  public RunConveyorSensor() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.conveyor, Robot.ballIntake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    sensorClear = false;
    Robot.ballIntake.setSpeed(Parameters.intake.EXPORT_SPEED);
    

    Robot.conveyor.stop();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    sensorClear = Robot.conveyor.getBottomSensor();

    //check if ball blocking sensor, if it's been long enough, start the motor
    if (sensorClear == false) {
      Robot.ballIntake.stop();
      timer.start();
      Robot.conveyor.setSpeed(.75);
    }

    if (timer.hasElapsed(Parameters.conveyor.TIME)) {
      Robot.ballIntake.setSpeed(Parameters.intake.EXPORT_SPEED);
      Robot.conveyor.stop();
      timer.stop();
      timer.reset();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    sensorClear = false;
    Robot.ballIntake.stop();
    Robot.conveyor.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}