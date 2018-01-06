package org.usfirst.frc.team2531.robot;

import org.usfirst.frc.team2531.robot.commands.TimeDriveGyro;
import org.usfirst.frc.team2531.robot.commands.Turn2Angle;
import org.usfirst.frc.team2531.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
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
		drive = new DriveSystem();
		oi = new OI();
		RobotMap.imu.calibrate();
		RobotMap.imu.reset();
		initSmartDashboard();
		RobotMap.cam = new Vision();
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
		RobotMap.imu.reset();
		autocommand = (Command) auto.getSelected();
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
		LiveWindow.run();
		updateSmartDashboard();
	}

	public void initSmartDashboard() {
		auto = new SendableChooser<Command>();
		auto.addDefault("No Auto", null);
		auto.addObject("Time Drive Gyro 2sec", new TimeDriveGyro(2000, 0.5));
		auto.addObject("Turn 90deg", new Turn2Angle(90));
		SmartDashboard.putData("Autonomous Mode", auto);
		SmartDashboard.putNumber("AngleX", RobotMap.imu.getAngleX());
		SmartDashboard.putNumber("AngleY", RobotMap.imu.getAngleY());
		SmartDashboard.putNumber("AngleZ", RobotMap.imu.getAngleZ());
	}

	public void updateSmartDashboard() {
		SmartDashboard.putNumber("AngleX", RobotMap.imu.getAngleX());
		SmartDashboard.putNumber("AngleY", RobotMap.imu.getAngleY());
		SmartDashboard.putNumber("AngleZ", RobotMap.imu.getAngleZ());
		RobotMap.cam.showLive();
	}

}