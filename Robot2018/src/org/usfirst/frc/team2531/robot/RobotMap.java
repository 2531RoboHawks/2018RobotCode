package org.usfirst.frc.team2531.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import frclib.sensors.ADIS16448;
import frclib.vision.Vision;

public class RobotMap {
	public static ADIS16448 imu = new ADIS16448(); // gyro and accel class
	public static Vision cam; // vision class
	public static double heading = 0;
	public static double speedx = 0;
	public static double speedy = 0;
	public static String gameData;
	public static Encoder wristencoder = new Encoder(0, 1);
	public static Encoder liftencoder = new Encoder(2, 3);
	public static DigitalInput lowerliftlimit = new DigitalInput(4);
	public static DigitalInput upperliftlimit = new DigitalInput(5);

	public static double liftpos = 0;
	public static double wristpos = 0;

	public static double mousex = 0;
	public static double mousey = 0;
}
