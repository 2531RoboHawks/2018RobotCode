package org.usfirst.frc.team2531.robot;

import org.usfirst.frc.team2531.robot.commands.MoveGrabber;
import org.usfirst.frc.team2531.robot.commands.MoveLift;
import org.usfirst.frc.team2531.robot.commands.MoveWrist;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {

	public static Joystick right = new Joystick(0);
	public static Joystick left = new Joystick(1);
	public static Joystick gamepad = new Joystick(2);

	private static JoystickButton lifttrigger = new JoystickButton(right, 2);
	private static JoystickButton wristtrigger = new JoystickButton(right, 3);
	private static JoystickButton wristclawtrigger = new JoystickButton(right, 4);

	// wrist 0-600
	// lift 0-2000

	public OI() {
		lifttrigger.whenPressed(new MoveLift(1500, true));
		lifttrigger.whenReleased(new MoveLift(0, true));
		wristtrigger.whenPressed(new MoveWrist(300, true));
		wristtrigger.whenReleased(new MoveWrist(0, true));
		wristclawtrigger.whenPressed(new MoveGrabber(true));
		wristclawtrigger.whenReleased(new MoveGrabber(false));
	}
}
