package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.Robot;
import org.usfirst.frc.team2531.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveLift extends Command {

	private double pow = 0;

	public MoveLift(double power) {
		requires(Robot.lift);
		pow = power;
	}

	protected void initialize() {
		System.out.println("-! Move Lift");
	}

	protected void execute() {
		if ((RobotMap.lowerliftlimit.get() && pow < 0) || (RobotMap.upperliftlimit.get() && pow > 0)) {
			Robot.lift.stop();
		} else {
			Robot.lift.set(pow);
		}
		RobotMap.liftpos = RobotMap.liftencoder.getDistance();
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.lift.stop();
		RobotMap.liftpos = RobotMap.liftencoder.getDistance();
		System.out.println("-! Move Lift");
	}

	protected void interrupted() {
		end();
	}
}
