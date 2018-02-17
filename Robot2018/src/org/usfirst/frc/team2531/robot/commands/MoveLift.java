package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.Robot;
import org.usfirst.frc.team2531.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import frclib.pid.PID;

/**
 *
 */
public class MoveLift extends Command {

	private boolean finish = false;
	private double enc = 0;
	private PID pid = new PID(0.004, 0, 0, 0);

	public MoveLift(double encoder, boolean finish) {
		requires(Robot.lift);
		enc = encoder;
		this.finish = finish;
	}

	protected void initialize() {
		System.out.println("-> Move Lift");
		pid.setOnTargetCount(10);
		pid.setOnTargetOffset(5);
		pid.setOutputLimits(0, 0.5);
		pid.setSetpoint(enc);
	}

	protected void execute() {
		double pow = pid.compute(RobotMap.liftencoder.getDistance());
		if ((RobotMap.lowerliftlimit.get() && pow < 0)) {
			Robot.lift.stop();
		} else {
			Robot.lift.set(pow);
		}
	}

	protected boolean isFinished() {
		if (finish) {
			return pid.onTarget();
		}
		return false;
	}

	protected void end() {
		Robot.lift.stop();
		System.out.println("-! Move Lift");
	}

	protected void interrupted() {
		end();
	}
}
