package org.usfirst.frc.team2531.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveArmTo extends CommandGroup {

	public MoveArmTo(double arm, double wrist) {
		addSequential(new HoldLift(arm), 1);
		addParallel(new HoldWrist(wrist));
	}
}
