package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.OI;
import org.usfirst.frc.team2531.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Drive extends Command {

	public Drive() {
		requires(Robot.drive);
	}

	protected void initialize() {
		System.out.println("-> Drive");
	}

	protected void execute() {
		Robot.drive.axisdrive(OI.right.getRawAxis(0), OI.right.getRawAxis(1), OI.left.getRawAxis(0));
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.drive.stop();
		System.out.println("-! Drive");
	}

	protected void interrupted() {
		end();
	}
}
