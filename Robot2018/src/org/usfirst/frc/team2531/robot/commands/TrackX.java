package org.usfirst.frc.team2531.robot.commands;

import java.util.ArrayList;

import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.usfirst.frc.team2531.robot.Robot;
import org.usfirst.frc.team2531.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import frclib.pid.PID;

public class TrackX extends Command {

	private PID turn = new PID(0.004, 0, 0, 0);
	private PID side = new PID(0.004, 0, 0, 320);
	private PID fore = new PID(0.004, 0, 0, 200);
	private double side_power = 0;
	private double turn_power = 0;
	private double fore_power = 0;
	private boolean stop = false;

	public TrackX(boolean ontargetstop) {
		stop = ontargetstop;
		requires(Robot.drive);
	}

	protected void initialize() {
		System.out.println("-> TrackR");
		side.setOnTargetOffset(2);
		side.setOutputLimits(-0.5, 0.5);
		side.setOnTargetCount(10);
		turn.setOnTargetOffset(2);
		turn.setOutputLimits(-0.5, 0.5);
		turn.setOnTargetCount(10);
		turn.setSetpoint(RobotMap.heading);
		fore.setOnTargetOffset(2);
		fore.setOutputLimits(-0.5, 0.5);
		fore.setOnTargetCount(10);
	}

	protected void execute() {
		Mat mat = RobotMap.cam.getImage();
		RobotMap.cam.setColor(100, 255, 0, 100, 0, 100);
		RobotMap.cam.setThreash(254);
		ArrayList<Rect> l = RobotMap.cam.TRGBgetBlobs(mat);
		int x = 0;
		int y = 0;
		int size = 0;
		for (int i = 0; i < l.size(); i++) {
			Rect r = l.get(i);
			if (r != null && r.area() > 200) {
				x += r.x + (r.width / 2);
				y += r.y + (r.height / 2);
				size += 1;
			}
		}
		if (size > 0) {
			x /= size;
			y /= size;
			mat = RobotMap.cam.showBlobs(mat, l, new Scalar(0, 255, 0));
			side_power = -side.compute(x);
			turn_power = turn.compute(RobotMap.imu.getAccelZ());
			fore_power = fore.compute(y);
			Robot.drive.axisdrive(side_power, fore_power, turn_power);
		} else {
			RobotMap.cam.putImage(mat);
			Robot.drive.stop();
		}
	}

	protected boolean isFinished() {
		if (stop) {
			return side.onTarget();
		} else {
			return false;
		}
	}

	protected void end() {
		Robot.drive.stop();
		System.out.println("-! TrackR");
	}

	protected void interrupted() {
		end();
	}
}