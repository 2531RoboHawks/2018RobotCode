package org.usfirst.frc.team2531.robot.subsystems;

import org.usfirst.frc.team2531.robot.commands.Drive;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveSystem extends Subsystem {

	CANTalon motorFL = new CANTalon(2);
	CANTalon motorFR = new CANTalon(1);
	CANTalon motorBL = new CANTalon(4);
	CANTalon motorBR = new CANTalon(3);

	public void initDefaultCommand() {
		setDefaultCommand(new Drive());
	}

	public void tankDrive(double left, double right) {
		motorFL.set(left);
		motorBL.set(left);
		motorFR.set(right);
		motorBR.set(right);
	}

	public void arcadeDrive(double x, double y) {
		double left = (y + x);
		double right = (y - x);
		motorFL.set(left);
		motorBL.set(left);
		motorFR.set(right);
		motorBR.set(right);
	}

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

	public void stop() {
		motorFL.set(0);
		motorFR.set(0);
		motorBL.set(0);
		motorBR.set(0);
	}
}
