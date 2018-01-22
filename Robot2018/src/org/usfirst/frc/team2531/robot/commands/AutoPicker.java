package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoPicker extends Command {

	boolean done = false;
	int ds = 0;

	Command c = null;

	public AutoPicker(int ds) {
		this.ds = ds;
	}

	protected void initialize() {
		System.out.println("-> AutoPicker");
	}

	protected void execute() {
		if (RobotMap.gameData.charAt(1) == 'R') {
			// right
			switch (ds) {
			case 1:
				System.out.println("Driver1 Scale R");
				c = new Driver1(false);
				break;
			case 2:
				System.out.println("Driver2 Scale R");
				c = new Driver2(false);
				break;
			case 3:
				System.out.println("Driver3 Scale R");
				c = new Driver3(false);
				break;
			default:

			}
		} else {
			// left
			switch (ds) {
			case 1:
				System.out.println("Driver1 Scale L");
				c = new Driver1(true);
				break;
			case 2:
				System.out.println("Driver2 Scale L");
				c = new Driver2(true);
				break;
			case 3:
				System.out.println("Driver3 Scale L");
				c = new Driver3(true);
				break;
			default:

			}
		}
		if (c != null) {
			c.start();
		}
		done = true;
	}

	protected boolean isFinished() {
		return done;
	}

	protected void end() {
		System.out.println("-! AutoPicker");
	}

	protected void interrupted() {
		c.cancel();
		end();
	}
}
