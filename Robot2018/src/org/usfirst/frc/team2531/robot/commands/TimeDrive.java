package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.Robot;
import org.usfirst.frc.team2531.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import frclib.pid.PID;

/**
 *
 */
public class TimeDrive extends Command {
	private long endTime;
	private boolean end;
	private long time;
	private double powY, powX;
	private PID pid = new PID(0.05, 0, 0, 0);

	public TimeDrive(long t, double pY, double pX) {
		requires(Robot.drive);
		powY = pY;
		powX = pX;
		time = t;
		pid.setOutputLimits(-0.5, 0.5);// stop robot from turning and not moving forward
	}

	protected void initialize() {
		System.out.println("-> TimeDrive");
		endTime = time + System.currentTimeMillis();// get the time in the future that robot needs to stop at
		end = false;
		pid.setSetpoint(RobotMap.heading);// set the angle the robot should follow
	}

	protected void execute() {
		double t = pid.compute(RobotMap.imu.getAngleX());// compute pid
		Robot.drive.axisdrive(-powX, -powY, -t);// move robot
		if (System.currentTimeMillis() > endTime)// check to see if time is up
			end = true;
	}

	protected boolean isFinished() {
		return end;
	}

	protected void end() {
		Robot.drive.stop();// stop all motors
		System.out.println("-! TimeDrive");
	}

	protected void interrupted() {
		end();
	}
}
