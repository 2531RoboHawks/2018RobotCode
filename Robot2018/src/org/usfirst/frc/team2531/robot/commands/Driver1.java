package org.usfirst.frc.team2531.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Driver1 extends CommandGroup {

	public Driver1(boolean left) {
		if (left) {
			addSequential(new TimeDrive(400, 1, 0));
			addSequential(new TimeDrive(1900, 1, 0.1));
		} else {
			addSequential(new TimeDrive(400, 1, 0));
			addSequential(new TimeDrive(3200, 0, 1));
			addSequential(new TimeDrive(1900, 1, -0.1));
		}
	}
}
