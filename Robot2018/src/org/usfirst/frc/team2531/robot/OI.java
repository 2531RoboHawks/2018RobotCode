package org.usfirst.frc.team2531.robot;

import org.usfirst.frc.team2531.robot.commands.MoveLift;
import org.usfirst.frc.team2531.robot.commands.MoveWrist;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {

	public static Joystick right = new Joystick(0);
	public static Joystick left = new Joystick(1);
	public static Joystick gamepad = new Joystick(2);

	private static JoystickButton lifttrigger = new JoystickButton(right, 1);
	private static JoystickButton wristtrigger = new JoystickButton(right, 2);

	public OI() {
		lifttrigger.whenPressed(new MoveLift(1000, false));
		lifttrigger.whenReleased(new MoveLift(0, false));
		wristtrigger.whenPressed(new MoveWrist(800, false));
		wristtrigger.whenReleased(new MoveWrist(0, false));
	}
}
