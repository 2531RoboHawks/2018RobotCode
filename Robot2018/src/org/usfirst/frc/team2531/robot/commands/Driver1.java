package org.usfirst.frc.team2531.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frclib.time.Delay;

/**
 *
 */
public class Driver1 extends CommandGroup {

	public Driver1(String data) {
		// switch
		if (data.charAt(0) == 'L') {
			addSequential(new TimeDrive((long) SmartDashboard.getNumber("Time 1", 0), 0.5, 0));// drive out
			addSequential(new Turn2Angle(-90));// turn to drive
			addSequential(new TimeDrive((long) SmartDashboard.getNumber("Time 2", 0), 0.5, 0));// drive out
			addSequential(new Turn2Angle(-90));// turn to face switch
			addParallel(new MoveArmTo(50, 100));// move arm up while turning
			addSequential(new TimeDrive((long) SmartDashboard.getNumber("Time 3", 0), 0.5, 0));// drive over switch to
			addSequential(new MoveGrabber(true));// open claw
			addSequential(new Delay(1000));// delay for time for claw to react
			addSequential(new MoveGrabber(false));// close claw
		} else if (data.charAt(0) == 'R') {
			addSequential(new TimeDrive((long) SmartDashboard.getNumber("Time 1", 0), 0.5, 0));// drive out
			addSequential(new Turn2Angle(-90));// turn to face switch
			addParallel(new MoveArmTo(50, 100));// move arm up while turning
			addSequential(new TimeDrive((long) SmartDashboard.getNumber("Time 2", 0), 0.5, 0));// drive over switch to
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
