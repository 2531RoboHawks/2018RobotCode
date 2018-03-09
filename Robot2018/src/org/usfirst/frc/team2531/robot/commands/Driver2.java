package org.usfirst.frc.team2531.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Driver2 extends CommandGroup {

	public Driver2(String data) {
		// switch
		if (data.charAt(0) == 'R') {
			addSequential(new TimeDrive(1000, 0, 1));
			addSequential(new TimeDrive(1000, 0.5, 0));
		} else if (data.charAt(0) == 'L') {
			addSequential(new TimeDrive(1000, 0, -1));
			addSequential(new TimeDrive(1000, 0.5, 0));
		}

		// scale
		if (data.charAt(1) == 'L') {

		} else if (data.charAt(1) == 'R') {

		}

		// far switch
		if (data.charAt(2) == 'L') {

		} else if (data.charAt(2) == 'R') {

		}
	}
}
