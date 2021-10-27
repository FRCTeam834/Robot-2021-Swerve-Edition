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
  Timer timer = new Timer();
  boolean currentReading = false, prevReading = false;

  public RunConveyorSensor() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.conveyor, Robot.intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.conveyor.stop();
    Robot.intake.runForward();
    Robot.leds.set(Parameters.LEDColors.ORANGE);
    timer.reset();
    timer.stop();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    // Set the current reading value (makes code faster and prevents errors)
    currentReading = Robot.conveyor.getBallSensor();

    // Start moving the conveyor if there is a new ball detected
    if (!prevReading && currentReading) {
      Robot.intake.stop();
      Robot.conveyor.runForward();

    }
    // Start the timer once the ball has passed
    else if (prevReading && !currentReading) {
      timer.reset();
      timer.start();
    }

    // Fix the prev reading value for the next cycle
    prevReading = currentReading;

    // Continuously set the LED color to orange
    Robot.leds.set(Parameters.LEDColors.ORANGE);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.intake.stop();
    Robot.conveyor.stop();
    timer.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timer.hasElapsed(Parameters.conveyor.INTAKE_TIME);
  }
}