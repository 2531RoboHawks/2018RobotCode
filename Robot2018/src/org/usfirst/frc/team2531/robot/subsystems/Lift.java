package org.usfirst.frc.team2531.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Lift extends Subsystem {

	private WPI_TalonSRX motor1 = new WPI_TalonSRX(5);
	private WPI_TalonSRX motor2 = new WPI_TalonSRX(6);

	public void initDefaultCommand() {
		// setDefaultCommand(new MoveLift(0, false));
	}

	public void set(double power) {
		motor1.set(power);
		motor2.set(power);
	}

	public void stop() {
		motor1.set(0);
		motor2.set(0);
	}
}
