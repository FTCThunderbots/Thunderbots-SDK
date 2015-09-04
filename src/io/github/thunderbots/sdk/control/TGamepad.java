package io.github.thunderbots.sdk.control;

import com.qualcomm.robotcore.hardware.Gamepad;
import io.github.thunderbots.sdk.utility.MathUtil;

/**
 * 
 *
 * @author Zach Ohara
 */
public class TGamepad {
	
	private Gamepad baseGamepad;
	
	public static final double JOYSTICK_THRESHOLD = 0.1;
	public static final double JOYSTICK_MAX = 1.0;
	public static final double JOYSTICK_REST = 0.0;
	public static final double JOYSTICK_MIN = -1.0;
	public static final double TRIGGER_MAX = 1.0;
	public static final double TRIGGER_MIN = 0.0;

	public TGamepad(Gamepad baseGamepad) {
		this.baseGamepad = baseGamepad;
		this.baseGamepad.setJoystickDeadzone((float)TGamepad.JOYSTICK_THRESHOLD);
	}
	
	private static double scaleJoystickInput(double raw) {
		return MathUtil.scaleToRange(raw, new double[] {JOYSTICK_THRESHOLD, JOYSTICK_MAX},
				new double[] {JOYSTICK_REST, JOYSTICK_MAX});
	}
	
	public boolean aButton() {
		return baseGamepad.a;
	}
	
	public boolean bButton() {
		return baseGamepad.b;
	}
	
	public boolean xButton() {
		return baseGamepad.x;
	}
	
	public boolean yButton() {
		return baseGamepad.y;
	}
	
	public boolean upButton() {
		return baseGamepad.dpad_up;
	}
	
	public boolean downButton() {
		return baseGamepad.dpad_down;
	}
	
	public boolean leftButton() {
		return baseGamepad.dpad_left;
	}
	
	public boolean rightButton() {
		return baseGamepad.dpad_right;
	}
	
	public boolean leftBumper() {
		return baseGamepad.left_bumper;
	}
	
	public boolean rightBumper() {
		return baseGamepad.right_bumper;
	}
	
	public boolean leftStickButton() {
		return baseGamepad.left_stick_button;
	}
	
	public boolean rightStickButton() {
		return baseGamepad.right_stick_button;
	}
	
	public double leftTrigger() {
		return baseGamepad.left_trigger;
	}
	
	public double rightTrigger() {
		return baseGamepad.right_trigger;
	}
	
	public double leftStickX() {
		return scaleJoystickInput(baseGamepad.left_stick_x);
	}
	
	public double leftStickY() {
		return scaleJoystickInput(-baseGamepad.left_stick_y);
	}
	
	public double rightStickX() {
		return scaleJoystickInput(baseGamepad.right_stick_x);
	}
	
	public double rightStickY() {
		return scaleJoystickInput(-baseGamepad.right_stick_y);
	}
	
}
