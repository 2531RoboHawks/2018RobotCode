package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.Robot;
import org.usfirst.frc.team2531.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import frclib.pid.PID;

public class HoldWrist extends Command {

	private double enc = 0;
	private PID pid = new PID(0.05, 0, 0, 0);
	private boolean set = false;

	public HoldWrist() {
		requires(Robot.wrist);
	}

	public HoldWrist(double encoder) {
		requires(Robot.wrist);
		enc = encoder;
		set = true;
	}

	protected void initialize() {
		System.out.println("-> Hold Wrist");
		pid.setOnTargetCount(10);
		pid.setOnTargetOffset(5);
		pid.setOutputLimits(-0.5, 0.5);
		if (set) {
			RobotMap.wristpos = enc;
		}
		pid.setSetpoint(RobotMap.wristpos);
	}

	protected void execute() {
		pid.setSetpoint(RobotMap.wristpos);
		double pow = pid.compute(RobotMap.wristencoder.getDistance());
		Robot.wrist.set(-pow);
	}

	protected boolean isFinished() {
		if (set) {
			return pid.onTarget();
		}
		return false;
	}

	protected void end() {
		Robot.wrist.stop();
		if (set) {
			RobotMap.wristpos = RobotMap.wristencoder.getDistance();
		}
		System.out.println("-! Hold Wrist");
	}

	protected void interrupted() {
		end();
	}
}
