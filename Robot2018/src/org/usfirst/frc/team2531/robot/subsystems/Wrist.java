package org.usfirst.frc.team2531.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Wrist extends Subsystem {

	private WPI_TalonSRX motor = new WPI_TalonSRX(9);

	public void initDefaultCommand() {
		// setDefaultCommand(new HoldWrist());
	}

	public void set(double power) {
		motor.set(power);
	}

	public void stop() {
		motor.set(0);
	}
}
