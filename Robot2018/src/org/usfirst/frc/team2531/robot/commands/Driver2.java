package org.usfirst.frc.team2531.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frclib.time.Delay;

/**
 *
 */
public class Driver2 extends CommandGroup {

	public Driver2(String data) {
		// switch
		if (data.charAt(0) == 'L') {
			addSequential(new TimeDrive((long) SmartDashboard.getNumber("Time 1", 0), 0, 0.5));// crab
			addSequential(new TimeDrive((long) SmartDashboard.getNumber("Time 2", 0), 0.5, 0));// drive to switch
			addParallel(new MoveArmTo(50, 100));// move arm up while driving
			addSequential(new MoveGrabber(true));// open claw
			addSequential(new Delay(1000));// delay for time for claw to react
			addSequential(new MoveGrabber(false));// close claw
		} else if (data.charAt(0) == 'R') {
			addSequential(new TimeDrive((long) SmartDashboard.getNumber("Time 1", 0), 0, -0.5));// crab
			addSequential(new TimeDrive((long) SmartDashboard.getNumber("Time 2", 0), 0.5, 0));// drive to switch
			addParallel(new MoveArmTo(50, 100));// move arm up while driving
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
