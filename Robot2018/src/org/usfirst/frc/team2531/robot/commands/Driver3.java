package org.usfirst.frc.team2531.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Driver3 extends CommandGroup {

	public Driver3(String data) {
		// switch
		if (data.charAt(0) == 'R') {
			addSequential(new MoveGrabber(false));
			addSequential(new TimeDrive(2500, 0.5, 0));
			addParallel(new MoveArmTo(100, 100));
			addSequential(new Turn2Angle(90), 2);
			addSequential(new TimeDrive(1000, 0.5, 0));
			addSequential(new MoveGrabber(true));
		} else if (data.charAt(0) == 'L') {
			addSequential(new MoveGrabber(false));
			addSequential(new TimeDrive(2000, 1, 0));
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
