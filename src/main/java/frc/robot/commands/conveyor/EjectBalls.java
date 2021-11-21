/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.conveyor;

/**
 * @author Christian Piper (@CAP1Sup), Mohammed Durrani (@mdurrani808)
 * @since 5/26/21
 */

// Imports
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Parameters;
import frc.robot.Robot;

public class EjectBalls extends CommandBase {

    Timer timer = new Timer();

    /** Creates a new RunConveyor. */
    public EjectBalls() {
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(Robot.conveyor);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {

        // Set the conveyor to the correct speed
        Robot.conveyor.runBackward();
        Robot.intake.runBackward();

        // Reset, then start the timer
        timer.reset();
        timer.start();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {}

    // Called once the ends or is interrupted.
    @Override
    public void end(boolean interrupted) {

        // Stop the conveyor
        Robot.intake.stop();
        Robot.conveyor.stop();
        Parameters.conveyor.BALL_COUNT = 0;
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return timer.hasElapsed(Parameters.conveyor.EJECT_TIME);
    }
}
