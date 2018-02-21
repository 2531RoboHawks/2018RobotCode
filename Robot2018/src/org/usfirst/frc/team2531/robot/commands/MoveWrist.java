package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.Robot;
import org.usfirst.frc.team2531.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveWrist extends Command {

	private double pow = 0;

	public MoveWrist(double power) {
		requires(Robot.wrist);
		pow = power;
	}

	protected void initialize() {
		System.out.println("-> Move Wrist");
	}

	protected void execute() {
		Robot.wrist.set(pow);
		RobotMap.wristpos = RobotMap.wristencoder.getDistance();
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.wrist.stop();
		RobotMap.wristpos = RobotMap.wristencoder.getDistance();
		System.out.println("-! Move Wrist");
	}

	protected void interrupted() {
		end();
	}
}
