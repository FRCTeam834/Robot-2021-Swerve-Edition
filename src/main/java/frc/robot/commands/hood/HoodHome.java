/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.hood;

// Robot
import frc.robot.Robot;

// Parameters
import frc.robot.Parameters;

// WPI libraries
import edu.wpi.first.wpilibj2.command.CommandBase;

public class HoodHome extends CommandBase {
  /**
   * Creates a new HoodHome.
   */
  boolean finished = false;

  public HoodHome() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.hood);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.hood.setSpeed(Parameters.hood.HOME_SPEED);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    // Check to see if the hood has hit it's home position
    if (Robot.hood.getLimitSwitch()) {

      // Stop motor and set the home position
      Robot.hood.stop();
      Robot.hood.resetEncoder();

      // Finished should be set last in case of interrupt
      finished = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

    // Stop the motor
    Robot.hood.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finished;
  }
}
