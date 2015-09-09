package io.github.thunderbots.lightning.control;

import com.qualcomm.robotcore.hardware.Gamepad;

import io.github.thunderbots.lightning.utility.MathUtil;

/**
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
		this.baseGamepad.setJoystickDeadzone((float) TGamepad.JOYSTICK_THRESHOLD);
	}

	private static double scaleJoystickInput(double raw) {
		return MathUtil.scaleToRange(raw, new double[] {TGamepad.JOYSTICK_THRESHOLD, TGamepad.JOYSTICK_MAX},
				new double[] {TGamepad.JOYSTICK_REST, TGamepad.JOYSTICK_MAX});
	}

	public boolean aButton() {
		return this.baseGamepad.a;
	}

	public boolean bButton() {
		return this.baseGamepad.b;
	}

	public boolean xButton() {
		return this.baseGamepad.x;
	}

	public boolean yButton() {
		return this.baseGamepad.y;
	}

	public boolean upButton() {
		return this.baseGamepad.dpad_up;
	}

	public boolean downButton() {
		return this.baseGamepad.dpad_down;
	}

	public boolean leftButton() {
		return this.baseGamepad.dpad_left;
	}

	public boolean rightButton() {
		return this.baseGamepad.dpad_right;
	}

	public boolean leftBumper() {
		return this.baseGamepad.left_bumper;
	}

	public boolean rightBumper() {
		return this.baseGamepad.right_bumper;
	}

	public boolean leftStickButton() {
		return this.baseGamepad.left_stick_button;
	}

	public boolean rightStickButton() {
		return this.baseGamepad.right_stick_button;
	}

	public double leftTrigger() {
		return this.baseGamepad.left_trigger;
	}

	public double rightTrigger() {
		return this.baseGamepad.right_trigger;
	}

	public double leftStickX() {
		return TGamepad.scaleJoystickInput(this.baseGamepad.left_stick_x);
	}

	public double leftStickY() {
		return TGamepad.scaleJoystickInput(-this.baseGamepad.left_stick_y);
	}

	public double rightStickX() {
		return TGamepad.scaleJoystickInput(this.baseGamepad.right_stick_x);
	}

	public double rightStickY() {
		return TGamepad.scaleJoystickInput(-this.baseGamepad.right_stick_y);
	}

}
