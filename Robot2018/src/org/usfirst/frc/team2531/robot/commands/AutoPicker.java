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
		System.out.println("Driver " + ds);
		System.out.println("Switch " + RobotMap.gameData.charAt(0));
		System.out.println("Scale " + RobotMap.gameData.charAt(1));
		System.out.println("Switch Opponent " + RobotMap.gameData.charAt(2));
		System.out.println("Raw Data " + RobotMap.gameData);
	}

	protected void execute() {
		switch (ds) {
		case 1:
			c = new Driver1(RobotMap.gameData);
			break;
		case 2:
			c = new Driver2(RobotMap.gameData);
			break;
		case 3:
			c = new Driver3(RobotMap.gameData);
			break;
		default:
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
