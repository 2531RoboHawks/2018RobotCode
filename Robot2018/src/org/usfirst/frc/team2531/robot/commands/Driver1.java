package org.usfirst.frc.team2531.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Driver1 extends CommandGroup {

	public Driver1(boolean left) {
		addSequential(new TimeDrive(500, 1, 0));
		if (left) {
			addSequential(new TimeDrive(500, 1, 1));
			addSequential(new TimeDrive(500, 0, 1));
			addSequential(new TimeDrive(500, -1, 1));
			addSequential(new TimeDrive(500, -1, 0));
			addSequential(new TimeDrive(500, -1, -1));
			addSequential(new TimeDrive(500, 0, -1));
		} else {

		}
	}
}
