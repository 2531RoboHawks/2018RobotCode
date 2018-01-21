package org.usfirst.frc.team2531.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ThereBack extends CommandGroup {

	public ThereBack() {
		for (int i = 0; i < 50; i++) {
			addSequential(new TimeDrive(1000, 1, 0));
			addSequential(new Turn2Angle(180));
			addSequential(new TimeDrive(1000, 1, 0));
			addSequential(new Turn2Angle(-180));
		}
	}
}
