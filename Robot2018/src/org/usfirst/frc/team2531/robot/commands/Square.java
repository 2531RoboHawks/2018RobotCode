package org.usfirst.frc.team2531.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Square extends CommandGroup {

	public Square() {
		addSequential(new TimeDrive(1000, 0.5, 0));
		addSequential(new TimeDrive(1000, 0, 0.5));
		addSequential(new TimeDrive(1000, -0.5, 0));
		addSequential(new TimeDrive(1000, 0, -0.5));
	}
}
