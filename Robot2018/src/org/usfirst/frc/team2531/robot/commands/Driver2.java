package org.usfirst.frc.team2531.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Driver2 extends CommandGroup {

	public Driver2(boolean left) {
		if (left) {
			addSequential(new TimeDrive(400, 1, 0));
			addSequential(new TimeDrive(2000, 0, 1));
			addSequential(new TimeDrive(1900, 1, 0.1));
		} else {
			addSequential(new TimeDrive(400, 1, 0));
			addSequential(new TimeDrive(1000, 0, 1));
			addSequential(new TimeDrive(1900, 1, -0.1));
		}
	}
}
