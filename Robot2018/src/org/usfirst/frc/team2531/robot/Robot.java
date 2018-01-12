package org.usfirst.frc.team2531.robot;

import java.util.ArrayList;

import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.usfirst.frc.team2531.robot.commands.TimeDriveGyro;
import org.usfirst.frc.team2531.robot.commands.Turn2Angle;
import org.usfirst.frc.team2531.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frclib.vision.Vision;

public class Robot extends IterativeRobot {

	public static OI oi;
	public static DriveSystem drive;

	SendableChooser<Command> auto;
	Command autocommand;

	@Override
	public void robotInit() {
		System.out.println("# Robot");
		// initialize the subsystems and joysticks / buttons
		drive = new DriveSystem();
		oi = new OI();
		// reset and calibrate the sensors for accuracy
		RobotMap.imu.calibrate();
		RobotMap.imu.reset();
		// add smartdashboard options
		initSmartDashboard();
		RobotMap.cam = new Vision(160, 120);
	}

	@Override
	public void disabledInit() {
		System.out.println("# Disabled");
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		updateSmartDashboard();
	}

	@Override
	public void autonomousInit() {
		System.out.println("# Autonomous");
		RobotMap.imu.reset();// reset gyro before the robot starts moving
		autocommand = (Command) auto.getSelected();// get the choice form the smartdashboard selected by the drive team
		// start the autonomous command
		if (autocommand != null) {
			autocommand.start();
		}
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		updateSmartDashboard();
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
		updateSmartDashboard();
	}

	@Override
	public void testPeriodic() {
		updateSmartDashboard();
	}

	public void initSmartDashboard() {
		auto = new SendableChooser<Command>();// initalize the auto chooser
		auto.addDefault("No Auto", null);
		auto.addObject("Time Drive Gyro 2sec", new TimeDriveGyro(2000, 0.5));
		auto.addObject("Turn 90deg", new Turn2Angle(90));
		SmartDashboard.putData("Autonomous Select", auto);// adds the auto chooser to the smartdashboard
		SmartDashboard.putNumber("AngleX", RobotMap.imu.getAngleX());// print gyro x
		SmartDashboard.putNumber("AngleY", RobotMap.imu.getAngleY());// print gyro y
		SmartDashboard.putNumber("AngleZ", RobotMap.imu.getAngleZ());// print gyro z
	}

	public void updateSmartDashboard() {
		SmartDashboard.putNumber("AngleX", RobotMap.imu.getAngleX());// print gyro x
		SmartDashboard.putNumber("AngleY", RobotMap.imu.getAngleY());// print gyro y
		SmartDashboard.putNumber("AngleZ", RobotMap.imu.getAngleZ());// print gyro z
		RobotMap.cam.showLive();// show the camera feed
		// proc();// find red
		System.gc();
	}

	public void proc() {
		Mat src = RobotMap.cam.getImage();// get frame
		RobotMap.cam.setColor(200, 255, 0, 100, 0, 100);// set the color that we want to look for
		ArrayList<Rect> blobs = RobotMap.cam.RGBgetBlobs(src);// search the image for the color
		Mat dst = RobotMap.cam.showBlobs(src, blobs, new Scalar(255, 0, 0));// show the results
		RobotMap.cam.putImage(dst);// send image to smartdashboard
	}

}