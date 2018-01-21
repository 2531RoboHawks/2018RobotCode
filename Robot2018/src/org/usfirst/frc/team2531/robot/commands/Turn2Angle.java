package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.Robot;
import org.usfirst.frc.team2531.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import frclib.pid.PID;

/**
 *
 */
public class Turn2Angle extends Command {

	private PID pid = new PID(0.05, 0, 0, 0);
	private double angle;

	public Turn2Angle(double degrees) {
		requires(Robot.drive);// requires drive subsystem
		angle = degrees;
		pid.setOutputLimits(-0.5, 0.5);// limit how fast robot turns
		pid.setOnTargetCount(10);// set the about of times to check for on target
		pid.setOnTargetOffset(1);// set the range that the input is from the setpoint to be ontarget
	}

	protected void initialize() {
		System.out.println("-> Turn2Angle");
		RobotMap.heading += angle;// add the angle to the heading
		pid.setSetpoint(RobotMap.heading);// set the setpoint or target for the robot to goto
	}

	protected void execute() {
		double p = pid.compute(RobotMap.imu.getAngleZ());// compute pid algorithm
		Robot.drive.axisdrive(0, 0, p);// move robot
	}

	protected boolean isFinished() {
		return pid.onTarget();
	}

	protected void end() {
		Robot.drive.stop();// stop all motors
		System.out.println("-! Turn2Angle");
	}

	protected void interrupted() {
		end();
	}
}
