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

public class AutonConveyor extends CommandBase {
  /**
   * Creates a new AutonConveyor.
   */

  // Create a timer
  Timer timer = new Timer();

  public AutonConveyor() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.conveyor);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    // Set to the default auton speed
    Robot.conveyor.setSpeed(Parameters.auton.CONVEYOR_SPEED);
    
    // Reset the timer, starting it from 0
    timer.reset();
  }

  public void initialize(double time, double speed) {

    // Set to the set speed
    Robot.conveyor.setSpeed(speed);

    // Reset the timer, starting it from 0
    timer.reset();
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

    // Stop the conveyor
    Robot.conveyor.stop();

    // Stop the timer
    timer.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timer.hasElapsed(Parameters.auton.CONVEYOR_RUN_TIME);
  }
}
