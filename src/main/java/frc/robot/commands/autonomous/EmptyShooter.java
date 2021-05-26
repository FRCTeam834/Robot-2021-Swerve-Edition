/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous;

// Robot
import frc.robot.Robot;

// Parameters
import frc.robot.Parameters;

// WPI libraries
import edu.wpi.first.wpilibj2.command.CommandBase;

public class EmptyShooter extends CommandBase {
  /**
   * Creates a new EmptyShooter.
   */

  // This variable will say if the sensor was previously covered or uncovered
  // so we can tell when a new ball is passing in front of it.
  boolean prevBottomSensorStatus;
  boolean prevTopSensorStatus;

  public EmptyShooter() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.shooter, Robot.conveyor);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    // Note the starting values of the sensors
    prevBottomSensorStatus = Robot.conveyor.getBottomSensor();
    prevTopSensorStatus = Robot.conveyor.getTopSensor();

    // Set the power of the motors
    Robot.shooter.setVoltage(Parameters.shooter.WHEEL_VOLTAGE);
    Robot.conveyor.setSpeed(Parameters.conveyor.AUTON_SPEED);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    // ! Needs work

    /*if (RobotContainer.ballCount == 0) {
      isFinished = true; //may need to remove exclamation point
    }

    //check if a ball is now entering while previous there was no ball
    //also checks if a ball is exiting the robot
    if (RobotContainer.Conveyor.getBottomSensor() == true && prevBottomSensorStatus == false) {
      prevBottomSensorStatus = true;
      RobotContainer.ballCount++;
    } else if (RobotContainer.Conveyor.getTopSensor() == true && prevTopSensorStatus == false) {
      prevTopSensorStatus = true;
      RobotContainer.ballCount--;
    }

    if (RobotContainer.Conveyor.getBottomSensor() == false && prevBottomSensorStatus == true) {
      prevBottomSensorStatus = false;
    } else if (RobotContainer.Conveyor.getTopSensor() == false && prevTopSensorStatus == true) {
      prevTopSensorStatus = false;
    }
    */

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

    // Halt all motors
    Robot.conveyor.stop();
    Robot.shooter.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
