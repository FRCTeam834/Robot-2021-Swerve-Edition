/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

/**
 * @author Christian Piper (@CAP1Sup), Mohammed Durrani (@mdurrani808), Jadon Trackim (@JadonTrackim), Krishna Dihora (@kjdih2)
 * @since 5/8/20
 */

package frc.robot;

// WPI libraries
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.cscore.UsbCamera;
//import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Spark;

// Subsystems
import frc.robot.subsystems.NavX;
import frc.robot.DriverProfiles.ProfilingManagement;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.swerve.DriveTrain;
import frc.robot.subsystems.Hood;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  // Main defines
  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;

  // Subsystems
  public static ProfilingManagement profilingManagement;
  public static NavX navX;
  public static DriveTrain driveTrain;
  public static Intake intake;
  public static Shooter shooter;
  public static Conveyor conveyor;
  public static Climber climber;
  public UsbCamera camera;
  public static Hood hood;

  // Lights!
  public static double colorSetting = -.45;
  public static Spark leds;

  // Global variables
  String gameData = "";

  // Commands
  // public static LetsRoll2Joysticks letsRoll2Joysticks;
  // public static LetsRoll1Joystick letsRoll1Joystick;
  // public static ZeroCanCoders zeroCanCoders;
  // public static PullNTSwerveParams pullNTSwerveParams;
  // public static TestModulePID testPID;
  // public static SaveSwerveParameters saveSwerveParameters;

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {

    // Profiling management should be first to avoid errors!
    profilingManagement = new ProfilingManagement();
    navX = new NavX();
    driveTrain = new DriveTrain();
    shooter = new Shooter();
    hood = new Hood();
    climber = new Climber();
    intake = new Intake();
    // controlPanelManip = new ControlPanelManip();
    conveyor = new Conveyor();
    // EVSNetworkTables = new EVSNetworkTables();
    m_robotContainer = new RobotContainer();
    driveTrain.resetOdometry(new Pose2d());
    leds = new Spark(9);

    // Commands
    // letsRoll2Joysticks = new LetsRoll2Joysticks();
    // letsRoll1Joystick = new LetsRoll1Joystick();
    // zeroCanCoders = new ZeroCanCoders();
    // pullNTSwerveParams = new PullNTSwerveParams();
    // testPID = new TestModulePID();
    // saveSwerveParameters = new SaveSwerveParameters();

    // Instantiate our RobotContainer. This will perform all our button bindings,
    // and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();

    // Reset the robot's yaw
    navX.resetYaw();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler. This is responsible for polling buttons, adding
    // newly-scheduled
    // commands, running already-scheduled commands, removing finished or
    // interrupted commands,
    // and running subsystem periodic() methods. This must be called from the
    // robot's periodic
    // block in order for anything in the Command-based framework to work.
    gameData = DriverStation.getInstance().getGameSpecificMessage();
    CommandScheduler.getInstance().run();
  }

  public void simulationPeriodic() {
    DriverStation.getInstance().silenceJoystickConnectionWarning(true);
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your
   * {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {

    // Set the LED value
    leds.set(colorSetting);
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {

    // Set the LED value
    leds.set(colorSetting);

    // Publish dashboard values
    SmartDashboard.putNumber("Ball Count", Parameters.conveyor.BALL_COUNT);
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
