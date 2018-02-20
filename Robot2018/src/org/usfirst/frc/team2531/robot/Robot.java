package org.usfirst.frc.team2531.robot;

import org.usfirst.frc.team2531.robot.commands.AutoPicker;
import org.usfirst.frc.team2531.robot.commands.Square;
import org.usfirst.frc.team2531.robot.commands.Square2;
import org.usfirst.frc.team2531.robot.commands.ThereBack;
import org.usfirst.frc.team2531.robot.commands.TimeDrive;
import org.usfirst.frc.team2531.robot.commands.TrackF;
import org.usfirst.frc.team2531.robot.commands.TrackX;
import org.usfirst.frc.team2531.robot.commands.Turn2Angle;
import org.usfirst.frc.team2531.robot.subsystems.DriveSystem;
import org.usfirst.frc.team2531.robot.subsystems.Grabber;
import org.usfirst.frc.team2531.robot.subsystems.Lift;
import org.usfirst.frc.team2531.robot.subsystems.Wrist;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frclib.vision.Vision;

public class Robot extends IterativeRobot {

	public static OI oi;
	public static DriveSystem drive;
	public static Grabber grabber;
	public static Lift lift;
	public static Wrist wrist;

	SendableChooser<Command> auto;
	Command autocommand;

	@Override
	public void robotInit() {
		System.out.println("# Robot");
		// initialize the subsystems and joysticks / buttons
		drive = new DriveSystem();
		grabber = new Grabber();
		lift = new Lift();
		wrist = new Wrist();
		oi = new OI();
		// reset and calibrate the sensors for accuracy
		RobotMap.imu.calibrate();
		RobotMap.imu.reset();
		RobotMap.cam = new Vision(160, 120);// create camera
		RobotMap.liftencoder.reset();
		RobotMap.wristencoder.reset();
		// add smartdashboard options
		initSmartDashboard();
	}

	@Override
	public void disabledInit() {
		System.out.println("# Disabled");
		RobotMap.cam.setRes(160, 120);// set the resolution smaller for better fps
		RobotMap.imu.reset();
		RobotMap.heading = 0;
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		RobotMap.cam.showLive();// show the camera feed
		updateSmartDashboard();// update the values on the dash
	}

	@Override
	public void autonomousInit() {
		System.out.println("# Autonomous");
		RobotMap.cam.setRes(640, 480);// set to larger resolution for vision tracking
		RobotMap.imu.reset();// reset gyro before the robot starts moving
		RobotMap.gameData = DriverStation.getInstance().getGameSpecificMessage();
		autocommand = (Command) auto.getSelected();// get the command from the chooser that was selected
		// start the autonomous command
		if (autocommand != null) {
			autocommand.start();
		}
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		updateSmartDashboard();// update the values on the dash
	}

	@Override
	public void teleopInit() {
		System.out.println("# Teleop");
		// stop auto before teleop starts
		if (autocommand != null) {
			autocommand.cancel();
		}
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		RobotMap.cam.showLive();// show the camera feed
		updateSmartDashboard();// update the values on the dash
	}

	@Override
	public void testPeriodic() {
		updateSmartDashboard();// update the values on the dash
	}

	public void initSmartDashboard() {
		auto = new SendableChooser<Command>();// initalize the auto chooser
		// add command options for auto
		auto.addDefault("No Auto", null);
		auto.addObject("Test Time Drive Gyro 1sec S", new TimeDrive(1000, 0, 1));
		auto.addObject("Test Time Drive Gyro 1sec F", new TimeDrive(1000, 1, 0));
		auto.addObject("Test Turn 90deg", new Turn2Angle(90));
		auto.addObject("Test Square", new Square());
		auto.addObject("Test Square2", new Square2());
		auto.addObject("Test There Back", new ThereBack());
		auto.addObject("Test VT F", new TrackF(false));
		auto.addObject("Test VT X", new TrackX(false));
		auto.addObject("Driver1", new AutoPicker(1));
		auto.addObject("Driver2", new AutoPicker(2));
		auto.addObject("Driver3", new AutoPicker(3));
		SmartDashboard.putData("Autonomous Select", auto);// adds the auto chooser to the smartdashboard
		SmartDashboard.putNumber("AngleX", RobotMap.imu.getAngleX());// print gyro x
		SmartDashboard.putNumber("AngleY", RobotMap.imu.getAngleY());// print gyro y
		SmartDashboard.putNumber("AngleZ", RobotMap.imu.getAngleZ());// print gyro z
		SmartDashboard.putNumber("Head", RobotMap.heading);// print heading
		SmartDashboard.putNumber("Wrist Encoder", RobotMap.wristencoder.getDistance());
		SmartDashboard.putNumber("Lift Encoder", RobotMap.liftencoder.getDistance());
		SmartDashboard.putBoolean("Lift Down", !RobotMap.lowerliftlimit.get());
		SmartDashboard.putBoolean("Lift Up", !RobotMap.upperliftlimit.get());
	}

	public void updateSmartDashboard() {
		SmartDashboard.putNumber("AngleX", RobotMap.imu.getAngleX());// print gyro x
		SmartDashboard.putNumber("AngleY", RobotMap.imu.getAngleY());// print gyro y
		SmartDashboard.putNumber("AngleZ", RobotMap.imu.getAngleZ());// print gyro z
		SmartDashboard.putNumber("Head", RobotMap.heading);// print heading
		SmartDashboard.putNumber("Wrist Encoder", RobotMap.wristencoder.getDistance());
		SmartDashboard.putNumber("Lift Encoder", RobotMap.liftencoder.getDistance());
		SmartDashboard.putBoolean("Lift Down", RobotMap.lowerliftlimit.get());
		SmartDashboard.putBoolean("Lift Up", RobotMap.upperliftlimit.get());
		System.gc();// clean memory for camera
	}

}