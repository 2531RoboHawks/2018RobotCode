package org.usfirst.frc.team2531.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Grabber extends Subsystem {

	private Solenoid leftclose = new Solenoid(0);
	private Solenoid leftopen = new Solenoid(1);
	private Solenoid rightclose = new Solenoid(2);
	private Solenoid rightopen = new Solenoid(3);

	public void initDefaultCommand() {

	}

	public void set(boolean open) {
		leftclose.set(!open);
		leftopen.set(open);
		rightclose.set(!open);
		rightopen.set(open);
	}
}
