package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.OI;
import org.usfirst.frc.team2531.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveChair extends Command {

	public DriveChair() {
		requires(Robot.drive);
	}

	protected void initialize() {
		System.out.println("-> Drive Chair");
	}

	protected void execute() {
		if (!OI.left.getRawButton(1)) {
			Robot.drive.axisdrive(0, OI.left.getRawAxis(1) * OI.left.getRawAxis(2),
					OI.left.getRawAxis(0) * OI.left.getRawAxis(2));
		} else {
			Robot.drive.axisdrive(OI.left.getRawAxis(0) * OI.left.getRawAxis(2),
					OI.left.getRawAxis(1) * OI.left.getRawAxis(2), 0);
		}
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.drive.stop();
		System.out.println("-! Drive Chair");
	}

	protected void interrupted() {
		end();
	}
}
