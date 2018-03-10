package org.usfirst.frc.team2531.robot;

import org.usfirst.frc.team2531.robot.commands.AutoPicker;
import org.usfirst.frc.team2531.robot.commands.MoveArmTo;
import org.usfirst.frc.team2531.robot.commands.TimeDrive;
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
		// set encoder scales
		RobotMap.liftencoder.setDistancePerPulse(0.1);
		RobotMap.wristencoder.setDistancePerPulse(0.5);
		// reset encoders
		RobotMap.liftencoder.reset();
		RobotMap.wristencoder.reset();
		// get encoder positions;
		RobotMap.liftpos = RobotMap.liftencoder.getDistance();
		RobotMap.wristpos = RobotMap.wristencoder.getDistance();
		// add smartdashboard options
		initSmartDashboard();
	}

	@Override
	public void disabledInit() {
		System.out.println("# Disabled");
		RobotMap.cam.setRes(160, 120);// set the resolution smaller for better fps
		// get encoder positions;
		RobotMap.liftpos = RobotMap.liftencoder.getDistance();
		RobotMap.wristpos = RobotMap.wristencoder.getDistance();
		RobotMap.imu.reset();// reset imu
		RobotMap.heading = 0;// reset heading
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		RobotMap.cam.showLive();// show the camera feed
		updateSmartDashboard();// update the values on the dash
		readMouse();
	}

	@Override
	public void autonomousInit() {
		System.out.println("# Autonomous");
		RobotMap.cam.setRes(640, 480);// set to larger resolution for vision tracking
		RobotMap.imu.reset();// reset gyro before the robot starts moving
		RobotMap.gameData = DriverStation.getInstance().getGameSpecificMessage();// game data retrieval
		// reset encoders
		RobotMap.liftencoder.reset();
		RobotMap.wristencoder.reset();
		// get encoder positions;
		RobotMap.liftpos = RobotMap.liftencoder.getDistance();
		RobotMap.wristpos = RobotMap.wristencoder.getDistance();
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
		readMouse();
	}

	@Override
	public void teleopInit() {
		System.out.println("# Teleop");
		// get encoder positions;
		RobotMap.liftpos = RobotMap.liftencoder.getDistance();
		RobotMap.wristpos = RobotMap.wristencoder.getDistance();
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
		readMouse();
	}

	@Override
	public void testPeriodic() {
		updateSmartDashboard();// update the values on the dash
		readMouse();
	}

	public void initSmartDashboard() {
		auto = new SendableChooser<Command>();// initalize the auto chooser
		// add command options for auto
		auto.addDefault("No Auto", null);
		auto.addObject("Driver 1", new AutoPicker(1, false));
		auto.addObject("Driver 2", new AutoPicker(2, false));
		auto.addObject("Driver 3", new AutoPicker(3, false));
		auto.addObject("Driver 1 Scale", new AutoPicker(1, true));
		auto.addObject("Driver 2 Scale", new AutoPicker(2, true));
		auto.addObject("Driver 3 Scale", new AutoPicker(3, true));
		auto.addObject("Baseline", new TimeDrive(2000, 1, 0));
		auto.addObject("Test Arm", new MoveArmTo(80, 80));
		SmartDashboard.putData("Autonomous Select", auto);// adds the auto chooser to the smartdashboard
		SmartDashboard.putNumber("AngleX", RobotMap.imu.getAngleX());// print gyro x
		SmartDashboard.putNumber("AngleY", RobotMap.imu.getAngleY());// print gyro y
		SmartDashboard.putNumber("AngleZ", RobotMap.imu.getAngleZ());// print gyro z
		SmartDashboard.putNumber("Head", RobotMap.heading);// print heading
		SmartDashboard.putNumber("Wrist Encoder", RobotMap.wristencoder.getDistance());// print wrist encoder
		SmartDashboard.putNumber("Lift Encoder", RobotMap.liftencoder.getDistance());// print lift encoder
		SmartDashboard.putNumber("Wrist Position", RobotMap.wristpos);// print wrist setpoint
		SmartDashboard.putNumber("Lift Position", RobotMap.liftpos); // print lift setpoint
		SmartDashboard.putBoolean("Lift Down", RobotMap.lowerliftlimit.get());// print limit switch lift down
		SmartDashboard.putBoolean("Lift Up", RobotMap.upperliftlimit.get());// print limit switch lift up
		// mouse
		SmartDashboard.putNumber("X", RobotMap.mousey);
		SmartDashboard.putNumber("Y", RobotMap.mousex);
	}

	public void updateSmartDashboard() {
		SmartDashboard.putNumber("AngleX", RobotMap.imu.getAngleX());// print gyro x
		SmartDashboard.putNumber("AngleY", RobotMap.imu.getAngleY());// print gyro y
		SmartDashboard.putNumber("AngleZ", RobotMap.imu.getAngleZ());// print gyro z
		SmartDashboard.putNumber("Head", RobotMap.heading);// print heading
		SmartDashboard.putNumber("Wrist Encoder", RobotMap.wristencoder.getDistance());// print wrist encoder
		SmartDashboard.putNumber("Lift Encoder", RobotMap.liftencoder.getDistance());// print lift encoder
		SmartDashboard.putNumber("Wrist Position", RobotMap.wristpos);// print wrist setpoint
		SmartDashboard.putNumber("Lift Position", RobotMap.liftpos); // print lift setpoint
		SmartDashboard.putBoolean("Lift Down", RobotMap.lowerliftlimit.get());// print limit switch lift down
		SmartDashboard.putBoolean("Lift Up", RobotMap.upperliftlimit.get());// print limit switch lift up
		// mouse
		SmartDashboard.putNumber("X", RobotMap.mousey);
		SmartDashboard.putNumber("Y", RobotMap.mousex);
		System.gc();// clean memory for camera
	}

	public void readMouse() {
		// Controller[] controllers =
		// ControllerEnvironment.getDefaultEnvironment().getControllers();
		// controllers[0].poll();
		// Component[] components = controllers[0].getComponents();
		// RobotMap.mousex += components[8].getPollData() / 200;
		// RobotMap.mousey += components[9].getPollData() / 200;
	}
}