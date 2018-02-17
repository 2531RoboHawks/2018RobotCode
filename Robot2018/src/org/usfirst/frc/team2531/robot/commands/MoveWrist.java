package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.Robot;
import org.usfirst.frc.team2531.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import frclib.pid.PID;

/**
 *
 */
public class MoveWrist extends Command {

	private boolean finish = false;
	private double enc = 0;
	private PID pid = new PID(0.05, 0, 0, 0);

	public MoveWrist(double encoder, boolean finish) {
		requires(Robot.wrist);
		enc = encoder;
		this.finish = finish;
	}

	protected void initialize() {
		System.out.println("-> Move Wrist");
		pid.setOnTargetCount(10);
		pid.setOnTargetOffset(5);
		pid.setOutputLimits(-1, 1);
		pid.setSetpoint(enc);
	}

	protected void execute() {
		double pow = pid.compute(RobotMap.wristencoder.getDistance());
		Robot.wrist.set(-pow);
	}

	protected boolean isFinished() {
		if (finish) {
			return pid.onTarget();
		}
		return false;
	}

	protected void end() {
		Robot.wrist.stop();
		System.out.println("-! Move Wrist");
	}

	protected void interrupted() {
		end();
	}
}
