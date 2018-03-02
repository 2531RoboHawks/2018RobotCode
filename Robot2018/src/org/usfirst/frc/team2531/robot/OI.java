package org.usfirst.frc.team2531.robot;

import org.usfirst.frc.team2531.robot.commands.MoveGrabber;
import org.usfirst.frc.team2531.robot.commands.MoveLift;
import org.usfirst.frc.team2531.robot.commands.MoveWrist;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {

	public static Joystick stick = new Joystick(0);
	public static Joystick gamepad = new Joystick(1);

	private static JoystickButton open = new JoystickButton(stick, 2);
	private static JoystickButton close = new JoystickButton(stick, 4);

	private static JoystickButton A = new JoystickButton(gamepad, 1);
	private static JoystickButton B = new JoystickButton(gamepad, 2);
	private static JoystickButton X = new JoystickButton(gamepad, 3);
	private static JoystickButton Y = new JoystickButton(gamepad, 4);
	private static JoystickButton R1 = new JoystickButton(gamepad, 6);
	private static JoystickButton L1 = new JoystickButton(gamepad, 5);

	// wrist 0-600
	// lift 0-2000

	public OI() {
		Y.whileHeld(new MoveLift(0.8));
		A.whileHeld(new MoveLift(-0.4));
		L1.whileHeld(new MoveWrist(-0.5));
		R1.whileHeld(new MoveWrist(0.5));
		open.whileHeld(new MoveGrabber(true));
		close.whileHeld(new MoveGrabber(false));
	}
}
