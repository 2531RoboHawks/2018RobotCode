package org.usfirst.frc.team2531.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Square2 extends CommandGroup {

	public Square2() {
		addSequential(new TimeDrive(1000, 0.5, 0));
		addSequential(new Turn2Angle(90));
		addSequential(new TimeDrive(1000, 0.5, 0));
		addSequential(new Turn2Angle(90));
		addSequential(new TimeDrive(1000, 0.5, 0));
		addSequential(new Turn2Angle(90));
		addSequential(new TimeDrive(1000, 0.5, 0));
		addSequential(new Turn2Angle(90));
	}
}
