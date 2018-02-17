package org.usfirst.frc.team2531.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Grabber extends Subsystem {

	private Solenoid open = new Solenoid(0);

	public void initDefaultCommand() {
	}

	public void set(boolean on) {
		open.set(on);
	}

	public boolean get() {
		return open.get();
	}
}
