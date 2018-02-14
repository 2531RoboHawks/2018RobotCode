package org.usfirst.frc.team2531.robot;

import org.usfirst.frc.team2531.robot.commands.MoveGrabber;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {

	public static Joystick right = new Joystick(0);
	public static Joystick left = new Joystick(1);
	public static Joystick gamepad = new Joystick(2);

	private static JoystickButton trigger = new JoystickButton(right, 1);

	public OI() {
		trigger.whenPressed(new MoveGrabber(false));
		trigger.whenReleased(new MoveGrabber(true));
	}
}
