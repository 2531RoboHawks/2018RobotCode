package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.Robot;
import org.usfirst.frc.team2531.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import frclib.pid.PID;

/**
 *
 */
public class HoldLift extends Command {

	private double enc = 0;
	private boolean set = false;
	private PID pid = new PID(0.008, 0, 0, 0);

	public HoldLift() {
		requires(Robot.lift);
	}

	public HoldLift(double encoder) {
		requires(Robot.lift);
		enc = encoder;
		set = true;
	}

	protected void initialize() {
		System.out.println("-> Hold Lift");
		pid.setOnTargetCount(10);
		pid.setOnTargetOffset(20);
		pid.setOutputLimits(0, 0.8);
		if (set) {
			RobotMap.liftpos = enc;
		}
		pid.setSetpoint(RobotMap.liftpos);
	}

	protected void execute() {
		double pow = pid.compute(RobotMap.liftencoder.getDistance());
		if ((RobotMap.lowerliftlimit.get() && pow < 0) || (RobotMap.upperliftlimit.get() && pow > 0)) {
			Robot.lift.stop();
		} else {
			Robot.lift.set(pow);
		}
	}

	protected boolean isFinished() {
		if (set) {
			return pid.onTarget();
		}
		return false;
	}

	protected void end() {
		Robot.lift.stop();
		System.out.println("-! Hold Lift");
	}

	protected void interrupted() {
		end();
	}
}
