package org.usfirst.frc.team2531.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Driver1 extends CommandGroup {

	public Driver1(String data) {
		addSequential(new TimeDrive(1000, 1, 0));
		addSequential(new Turn2Angle(90));
		addParallel(new MoveArm(60, 60));
	}
}
