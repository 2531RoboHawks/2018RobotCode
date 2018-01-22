package org.usfirst.frc.team2531.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Driver1 extends CommandGroup {

	public Driver1(boolean left) {
		addSequential(new Turn2Angle(90));
	}
}
