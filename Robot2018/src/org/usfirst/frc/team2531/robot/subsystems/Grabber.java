package org.usfirst.frc.team2531.robot.subsystems;

import org.usfirst.frc.team2531.robot.commands.MoveGrabber;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Grabber extends Subsystem {

	private Solenoid open = new Solenoid(0);
	private Solenoid close = new Solenoid(1);

	public void initDefaultCommand() {
		setDefaultCommand(new MoveGrabber(false));
	}

	public void set(boolean on) {
		open.set(on);
		close.set(!on);
	}

	public boolean get() {
		return open.get();
	}
}
