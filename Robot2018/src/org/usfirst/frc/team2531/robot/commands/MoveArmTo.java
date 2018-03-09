package org.usfirst.frc.team2531.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveArmTo extends CommandGroup {

	public MoveArmTo(double arm, double wrist) {
		addParallel(new HoldWrist(wrist));
		addSequential(new HoldLift(arm));
	}
}
