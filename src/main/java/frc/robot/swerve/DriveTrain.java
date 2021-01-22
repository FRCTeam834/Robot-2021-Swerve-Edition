/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.swerve;

// WPI libraries
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.kinematics.SwerveDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.SwerveDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;

// Vendor libraries
import com.revrobotics.ControlType;

// Import Parameters
import frc.robot.Parameters;

// Import robot
import frc.robot.Robot;

// Internal libraries
import frc.robot.swerve.SwerveModule;


public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new Drivetrain.
   */

  SwerveModule frontLeft;
  SwerveModule frontRight;
  SwerveModule backLeft;
  SwerveModule backRight;

  Translation2d FLLocation = new Translation2d(Parameters.DRIVE_LENGTH / 2, Parameters.DRIVE_WIDTH / 2);
  Translation2d FRLocation = new Translation2d(Parameters.DRIVE_LENGTH / 2, -Parameters.DRIVE_WIDTH / 2);
  Translation2d BLLocation = new Translation2d(-Parameters.DRIVE_LENGTH / 2, Parameters.DRIVE_WIDTH / 2);
  Translation2d BRLocation = new Translation2d(-Parameters.DRIVE_LENGTH / 2, -Parameters.DRIVE_WIDTH / 2);

  SwerveDriveKinematics kinematics = new SwerveDriveKinematics(FLLocation, FRLocation, BLLocation, BRLocation);


  public DriveTrain() {

    frontLeft =  new SwerveModule(Parameters.FRONT_LEFT_STEER_ID,  Parameters.FRONT_LEFT_DRIVE_ID,  Parameters.FRONT_LEFT_CODER_ID,  Parameters.FL_T_PID_PARAM, Parameters.FL_D_PID_PARAM);
    frontRight = new SwerveModule(Parameters.FRONT_RIGHT_STEER_ID, Parameters.FRONT_RIGHT_DRIVE_ID, Parameters.FRONT_RIGHT_CODER_ID, Parameters.FR_T_PID_PARAM, Parameters.FR_D_PID_PARAM);
    backLeft =   new SwerveModule(Parameters.BACK_LEFT_STEER_ID,   Parameters.BACK_LEFT_DRIVE_ID,   Parameters.BACK_LEFT_CODER_ID,   Parameters.BL_T_PID_PARAM, Parameters.BL_D_PID_PARAM);
    backRight =  new SwerveModule(Parameters.BACK_RIGHT_STEER_ID,  Parameters.BACK_RIGHT_DRIVE_ID,  Parameters.BACK_RIGHT_CODER_ID,  Parameters.BR_T_PID_PARAM, Parameters.BR_D_PID_PARAM);

  }
  
  private final SwerveDriveOdometry odometry = new SwerveDriveOdometry(kinematics, Robot.navX.getFusedRotation2d());

  public void drive(double xSpeed, double ySpeed, double rot, boolean fieldRelative) {
    var swerveModuleStates = kinematics.toSwerveModuleStates(
        fieldRelative ? ChassisSpeeds.fromFieldRelativeSpeeds(
            xSpeed, ySpeed, rot, Robot.navX.getFusedRotation2d())
            : new ChassisSpeeds(xSpeed, ySpeed, rot)
    );
    SwerveDriveKinematics.normalizeWheelSpeeds(swerveModuleStates, Parameters.MAX_MODULE_SPEED);
    frontLeft.setDesiredState(SwerveModuleState.optimize(swerveModuleStates[0], new Rotation2d(frontLeft.getAngle())));
    frontRight.setDesiredState(SwerveModuleState.optimize(swerveModuleStates[1], new Rotation2d(frontLeft.getAngle())));
    backLeft.setDesiredState(SwerveModuleState.optimize(swerveModuleStates[2], new Rotation2d(frontLeft.getAngle())));
    backRight.setDesiredState(SwerveModuleState.optimize(swerveModuleStates[3], new Rotation2d(frontLeft.getAngle())));
  }

  public void lockemUp() {
    // Makes an X pattern with the swerve base
    // Set the modules to 45 degree angles
    frontLeft.setAngle(-45);
    frontRight.setAngle(45);
    backLeft.setAngle(45);
    backRight.setAngle(-45);

    // Halt all the motors and hold them there
    frontLeft.setSpeed(0, ControlType.kVelocity);
    frontRight.setSpeed(0, ControlType.kVelocity);
    backRight.setSpeed(0, ControlType.kVelocity);
    backLeft.setSpeed(0, ControlType.kVelocity);

  }

  /**
   * Updates the field relative position of the robot.
   */
  public void updateOdometry() {
    odometry.update(
        Robot.navX.getFusedRotation2d(),
        frontLeft.getState(),
        frontRight.getState(),
        backLeft.getState(),
        backRight.getState()
    );
  }

  public void resetOdometry(Pose2d currentPosition) {
    odometry.resetPosition(currentPosition, Robot.navX.getFusedRotation2d());
  }

  public void updateParameters() {
    // Set steering parameters
    frontLeft.setSteerMParams(Parameters.FL_T_PID_PARAM);
    frontRight.setSteerMParams(Parameters.FR_T_PID_PARAM);
    backLeft.setSteerMParams(Parameters.BL_T_PID_PARAM);
    backRight.setSteerMParams(Parameters.BR_T_PID_PARAM);

    // Set driving parameters
    frontLeft.setDriveMParams(Parameters.FL_D_PID_PARAM, Parameters.currentDriverProfile.DRIVE_RAMP_RATE, Parameters.currentDriverProfile.DRIVE_IDLE_MODE);
    frontRight.setDriveMParams(Parameters.FR_D_PID_PARAM, Parameters.currentDriverProfile.DRIVE_RAMP_RATE, Parameters.currentDriverProfile.DRIVE_IDLE_MODE);
    backLeft.setDriveMParams(Parameters.BL_D_PID_PARAM, Parameters.currentDriverProfile.DRIVE_RAMP_RATE, Parameters.currentDriverProfile.DRIVE_IDLE_MODE);
    backRight.setDriveMParams(Parameters.BR_D_PID_PARAM, Parameters.currentDriverProfile.DRIVE_RAMP_RATE, Parameters.currentDriverProfile.DRIVE_IDLE_MODE);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
