package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveGrabber extends Command {

	boolean state;

	public MoveGrabber(boolean state) {
		requires(Robot.grabber);
		this.state = state;
	}

	protected void initialize() {
		System.out.println("-> Move Grabber");
	}

	protected void execute() {
		Robot.grabber.set(state);

	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
		System.out.println("-! Move Grabber");
	}

	protected void interrupted() {
		end();
	}
}
