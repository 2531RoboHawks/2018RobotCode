package org.usfirst.frc.team2531.robot;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import frclib.sensors.ADIS16448;
import frclib.vision.Vision;

public class RobotMap {
	public static ADIS16448 imu = new ADIS16448(); // gyro and accel class
	public static Vision cam; // vision class
	public static double heading = 0;
	public static double speedx = 0;
	public static double speedy = 0;
	public static String gameData;
	public static BuiltInAccelerometer acc = new BuiltInAccelerometer();
}
