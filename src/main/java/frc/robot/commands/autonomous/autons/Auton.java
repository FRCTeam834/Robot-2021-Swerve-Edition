// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous.autons;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Parameters;
import frc.robot.commands.conveyor.EmptyConveyor;
import frc.robot.commands.swerve.DriveDistanceCheap;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Auton extends SequentialCommandGroup {
  /** Creates a new Auton. */
  public Auton() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new DriveDistanceCheap(Parameters.driveTrain.auton.DRIVE_DISTANCE, Parameters.driveTrain.auton.DRIVE_SPEED), new LineUpAndShoot());
  }
}
