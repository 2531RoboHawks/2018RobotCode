package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.OI;
import org.usfirst.frc.team2531.robot.Robot;
import org.usfirst.frc.team2531.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AxisLift extends Command {

	public AxisLift() {
		requires(Robot.lift);
	}

	protected void initialize() {
		System.out.println("-! Axis Lift");
	}

	protected void execute() {
		double pow = OI.gamepad.getRawAxis(3);
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
		System.out.println("-! Axis Lift");
	}

	protected void interrupted() {
		end();
	}
}
