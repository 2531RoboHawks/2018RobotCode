package org.usfirst.frc.team2531.robot.commands;

import java.util.ArrayList;

import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.usfirst.frc.team2531.robot.Robot;
import org.usfirst.frc.team2531.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import frclib.pid.PID;

public class TrackR extends Command {

	private PID turn = new PID(0.008, 0.005, 0, 320);
	private double turn_power = 0;
	private boolean stop = false;

	public TrackR(boolean ontargetstop) {
		stop = ontargetstop;
		requires(Robot.drive);
	}

	protected void initialize() {
		System.out.println("-> TrackR");
		turn.setOnTargetOffset(2);
		turn.setOutputLimits(-0.2, 0.2);
		turn.setOnTargetCount(10);
		turn.setLoopTime(10);
	}

	protected void execute() {
		Mat mat = RobotMap.cam.getImage();
		RobotMap.cam.setColor(100, 255, 0, 50, 0, 50);
		ArrayList<Rect> l = RobotMap.cam.RGBgetBlobs(mat);
		int x = 0;
		int y = 0;
		int size = 0;
		for (int i = 0; i < l.size(); i++) {
			Rect r = l.get(i);
			if (r != null && r.area() > 50) {
				x += r.x + (r.width / 2);
				y += r.y + (r.height / 2);
				size += 1;
			}
		}
		if (size > 0) {
			x /= size;
			y /= size;
			mat = RobotMap.cam.showBlobs(mat, l, new Scalar(0, 255, 0));
			turn_power = -turn.compute2(x);
			Robot.drive.axisdrive(0, 0, turn_power);
			RobotMap.cam.putImage(mat);
		} else {
			RobotMap.cam.putImage(mat);
			Robot.drive.stop();
		}
	}

	protected boolean isFinished() {
		if (stop) {
			return turn.onTarget();
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