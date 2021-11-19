/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

// Robot
import frc.robot.Robot;

// Parameters
import frc.robot.Parameters;



// WPI libraries
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RunShooter extends CommandBase {
  /**
   * Creates a new RunShooter.
   */
  public RunShooter() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    // Start the motor at the specified voltage
    Robot.shooter.startup();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Robot.leds.set(Parameters.LEDColors.PINK);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

    // Stop the motor
    Robot.shooter.stop();
    Robot.leds.set(Parameters.LEDColors.PARTY);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
