package org.usfirst.frc.team2531.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveArm extends CommandGroup {

	public MoveArm(double arm, double wrist) {
		addSequential(new HoldLift(arm), 1);
		addParallel(new HoldWrist(wrist));
	}
}
