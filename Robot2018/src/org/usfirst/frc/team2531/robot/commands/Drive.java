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
		if (!OI.stick.getRawButton(1)) {
			Robot.drive.axisdrive(-OI.stick.getRawAxis(0), OI.stick.getRawAxis(1), OI.stick.getRawAxis(3));
		} else {
			Robot.drive.axisdrive(-OI.stick.getRawAxis(0) / 2, OI.stick.getRawAxis(1) / 2, OI.stick.getRawAxis(3) / 2);
		}
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
