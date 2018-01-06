package org.usfirst.frc.team2531.robot.commands;

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
		// handle input sub system drive action here
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		// sub system stop action here
		System.out.println("-! Drive");
	}

	protected void interrupted() {
		end();
	}
}
