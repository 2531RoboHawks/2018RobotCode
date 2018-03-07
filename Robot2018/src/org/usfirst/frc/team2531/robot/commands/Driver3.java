package org.usfirst.frc.team2531.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frclib.time.Delay;

/**
 *
 */
public class Driver3 extends CommandGroup {

	public Driver3(String data) {
		// switch
		if (data.charAt(0) == 'R') {

		} else if (data.charAt(0) == 'L') {
			addSequential(new TimeDrive(2000, 0.5, 0));// drive out
			addSequential(new Turn2Angle(-90));// turn to face switch
			addParallel(new MoveArmTo(100, 100));// move arm up while turning
			addSequential(new TimeDrive(500, 0.5, 0));// drive over switch to drop
			addSequential(new MoveGrabber(true));// open claw
			addSequential(new Delay(1000));// delay for time for claw to react
			addSequential(new MoveGrabber(false));// close claw
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
