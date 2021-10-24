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
  boolean sensorClear = false;
  int counter = 0;
  Timer timer = new Timer();

  public RunConveyorSensor() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.conveyor, Robot.ballIntake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    sensorClear = false;
    Robot.ballIntake.setSpeed(Parameters.intake.INTAKE_SPEED);
    Robot.conveyor.stop();
    Robot.leds.set(Parameters.LEDColors.ORANGE);
    timer.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    sensorClear = Robot.conveyor.getBottomSensor();
    if (sensorClear == false) {
      Robot.ballIntake.stop();
      Robot.conveyor.setSpeed(.75);
      if(counter == 0)
      {
        timer.start();
      }
      counter++;
    }

    if (timer.hasElapsed(Parameters.conveyor.TIME) && sensorClear == true) {
      Robot.ballIntake.setSpeed(Parameters.intake.INTAKE_SPEED);
      Robot.conveyor.stop();
      timer.stop();
      timer.reset();
      counter = 0;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    sensorClear = false;
    Robot.ballIntake.stop();
    Robot.conveyor.stop();
    Robot.leds.set(-.43);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}