package org.usfirst.frc.team2531.robot.subsystems;

import org.usfirst.frc.team2531.robot.commands.Drive;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveSystem extends Subsystem {

	CANTalon motorFL = new CANTalon(2);// front left speed controller
	CANTalon motorFR = new CANTalon(1);// front right speed controller
	CANTalon motorBL = new CANTalon(4);// back left speed controller
	CANTalon motorBR = new CANTalon(3);// back right speed controller

	public void initDefaultCommand() {
		// set drive command to run when nothing else is using the drive system for
		// control
		setDefaultCommand(new Drive());
	}

	/**
	 * runs tank drive
	 * 
	 * @param left
	 *            left side power
	 * @param right
	 *            right side power
	 */
	public void tankDrive(double left, double right) {
		motorFL.set(left);
		motorBL.set(left);
		motorFR.set(right);
		motorBR.set(right);
	}

	/**
	 * runs arcade drive
	 * 
	 * @param x
	 *            steering
	 * @param y
	 *            forward power
	 */
	public void arcadeDrive(double x, double y) {
		double left = (y + x);
		double right = (y - x);
		motorFL.set(left);
		motorBL.set(left);
		motorFR.set(right);
		motorBR.set(right);
	}

	/**
	 * runs mechanum drive
	 * 
	 * @param x
	 *            right power
	 * @param y
	 *            forward power
	 * @param r
	 *            rotation power
	 */
	public void axisdrive(double x, double y, double r) {
		double a = Math.atan2(y, x);
		double pFL = (Math.abs(Math.sin(a)) * y) - (Math.abs(Math.cos(a)) * x) + r;
		double pFR = (Math.abs(Math.sin(a)) * y) + (Math.abs(Math.cos(a)) * x) - r;
		double pBL = (Math.abs(Math.sin(a)) * y) + (Math.abs(Math.cos(a)) * x) + r;
		double pBR = (Math.abs(Math.sin(a)) * y) - (Math.abs(Math.cos(a)) * x) - r;
		motorFL.set(pFL);
		motorFR.set(pFR);
		motorBL.set(pBL);
		motorBR.set(pBR);
	}

	/**
	 * stops all motors
	 */
	public void stop() {
		motorFL.set(0);
		motorFR.set(0);
		motorBL.set(0);
		motorBR.set(0);
	}
}
