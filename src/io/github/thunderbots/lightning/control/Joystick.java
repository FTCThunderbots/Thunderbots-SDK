/* Copyright (C) 2015-2016 Thunderbots Robotics
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.github.thunderbots.lightning.control;

import java.util.LinkedList;
import java.util.List;

import io.github.thunderbots.lightning.utility.MathUtil;

import com.qualcomm.robotcore.hardware.Gamepad;



/**
 * A {@code Joystick} object represents one of the physical game controllers that are
 * connected to the driver station.
 *
 * @author Zach Ohara
 */
public class Joystick {

	/**
	 * The {@code Gamepad} that this object should receive information from.
	 *
	 * @see com.qualcomm.robotcore.hardware.Gamepad
	 */
	private Gamepad baseGamepad;

	/**
	 * The threshold that must be exceeded by the raw thumb stick values before it should
	 * register at all. The purpose of this value is to prevent random noise and
	 * false movements from being registered and acted upon.
	 */
	public static final double THUMBSTICK_THRESHOLD = 0.2;

	/**
	 * The maximum value that can be expected from the thumb stick values.
	 */
	public static final double THUMBSTICK_MAX = 1.0;

	/**
	 * The value that should be expected from the thumb sticks when the thumb stick is at
	 * rest.
	 */
	public static final double THUMBSTICK_REST = 0.0;

	/**
	 * The minimum value that can be expected from the thumb stick values.
	 */
	public static final double THUMBSTICK_MIN = -1.0;

	/**
	 * The maximum value that can be expected from the trigger values.
	 */
	public static final double TRIGGER_MAX = 1.0;

	/**
	 * The minimum value that can be expected from the trigger values.
	 */
	public static final double TRIGGER_MIN = 0.0;
	
	/**
	 * The threshold that is used to determine if a joystick is sufficiently 'fully' pressed.
	 */
	public static final double JOYSTICK_PRESS_THRESHOLD = 0.95;
	
	/**
	 * The threshold that is used to determine if a trigger is sufficiently 'fully' pressed.
	 */
	public static final double TRIGGER_PRESS_THRESHOLD = 0.95;

	/**
	 * Constructs a new {@code Joystick} with the given {@code Gamepad} as a base.
	 *
	 * @param baseGamepad the base {@code Gamepad}
	 */
	public Joystick(Gamepad baseGamepad) {
		this.baseGamepad = baseGamepad;
		this.baseGamepad.setJoystickDeadzone((float) Joystick.THUMBSTICK_THRESHOLD);
	}

	/**
	 * Scales the given input value to a number between the rest position and the maximum
	 * position of the thumb stick, based on its relative position between the minimum and
	 * maximum positions. This allows the thumb sticks to show a smooth transition between
	 * the minimum and the maximum, even though any value less than the threshold will be
	 * reported as zero.
	 * <p>
	 * This method acts as a delegate to
	 * {@link io.github.thunderbots.lightning.utility.MathUtil#scaleToRange(double, double[], double[])
	 * MathUtil.scaleToRange()}, but supplies the thumbstick constants as the range bounds.
	 *
	 * @param raw the raw input value from the joystick, between the threshold and maximum
	 * positions.
	 * @return the corresponding value between the minimum and maximum positions.
	 * @see io.github.thunderbots.lightning.utility.MathUtil#scaleToRange(double, double[], double[])
	 */
	private static double scaleJoystickInput(double raw) {
		return MathUtil.scaleToRange(raw, new double[] {Joystick.THUMBSTICK_THRESHOLD, Joystick.THUMBSTICK_MAX},
				new double[] {Joystick.THUMBSTICK_REST, Joystick.THUMBSTICK_MAX});
	}

	/**
	 * Returns {@code true} if the A-button on the joystick is currently being pressed.
	 *
	 * @return whether the A-button is being pressed.
	 */
	public boolean aButton() {
		return this.baseGamepad.a;
	}

	/**
	 * Returns {@code true} if the B-button on the joystick is currently being pressed.
	 *
	 * @return whether the B-button is being pressed.
	 */
	public boolean bButton() {
		return this.baseGamepad.b;
	}

	/**
	 * Returns {@code true} if the X-button on the joystick is currently being
	 * pressed.
	 *
	 * @return whether the X-button is being pressed.
	 */
	public boolean xButton() {
		return this.baseGamepad.x;
	}

	/**
	 * Returns {@code true} if the Y-button on the joystick is currently being
	 * pressed.
	 *
	 * @return whether the Y-button is being pressed.
	 */
	public boolean yButton() {
		return this.baseGamepad.y;
	}

	/**
	 * Returns {@code true} if the up-direction button on the joystick is
	 * currently being pressed.
	 *
	 * @return whether the up-direction is being pressed.
	 */
	public boolean upButton() {
		return this.baseGamepad.dpad_up;
	}

	/**
	 * Returns {@code true} if down-direction button on the joystick is
	 * currently being pressed.
	 *
	 * @return whether the down-direction is being pressed.
	 */
	public boolean downButton() {
		return this.baseGamepad.dpad_down;
	}

	/**
	 * Returns {@code true} if the left-direction button on the joystick is
	 * currently being pressed.
	 *
	 * @return whether the left-direction is being pressed.
	 */
	public boolean leftButton() {
		return this.baseGamepad.dpad_left;
	}

	/**
	 * Returns {@code true} if the right-direction button on the joystick is
	 * currently being pressed.
	 *
	 * @return whether the right-direction is being pressed.
	 */
	public boolean rightButton() {
		return this.baseGamepad.dpad_right;
	}

	/**
	 * Returns {@code true} if the left bumper on the joystick is currently
	 * being pressed.
	 *
	 * @return whether the left bumper is being pressed.
	 */
	public boolean leftBumper() {
		return this.baseGamepad.left_bumper;
	}

	/**
	 * Returns {@code true} if the right bumper on the joystick is currently
	 * being pressed.
	 *
	 * @return whether the right bumper is being pressed.
	 */
	public boolean rightBumper() {
		return this.baseGamepad.right_bumper;
	}

	/**
	 * Returns {@code true} if the left-stick button on the joystick is
	 * currently being pressed.
	 *
	 * @return whether the left-stick button is being pressed.
	 */
	public boolean leftStickButton() {
		return this.baseGamepad.left_stick_button;
	}

	/**
	 * Returns {@code true} if the right-stick button on the joystick is
	 * currently being pressed.
	 *
	 * @return whether the right-stick button is being pressed.
	 */
	public boolean rightStickButton() {
		return this.baseGamepad.right_stick_button;
	}

	/**
	 * Returns the current position of the left trigger on the joystick.
	 *
	 * @return the current position of the left trigger.
	 */
	public double leftTrigger() {
		return this.baseGamepad.left_trigger;
	}
	
	/**
	 * Returns {@code true} if the left trigger value is above the set threshold, or
	 * {@code false} otherwise.
	 *
	 * @return {@code true} if the left trigger value is above the set threshold.
	 * @see #TRIGGER_PRESS_THRESHOLD
	 */
	public boolean leftTriggerPressed() {
		return this.leftTrigger() > Joystick.TRIGGER_PRESS_THRESHOLD;
	}

	/**
	 * Returns the current position of the right trigger on the joystick.
	 *
	 * @return the current position of the right trigger.
	 */
	public double rightTrigger() {
		return this.baseGamepad.right_trigger;
	}
	
	/**
	 * Returns {@code true} if the right trigger value is above the set threshold, or
	 * {@code false} otherwise.
	 *
	 * @return {@code true} if the right trigger value is above the set threshold.
	 * @see #TRIGGER_PRESS_THRESHOLD
	 */
	public boolean rightTriggerPressed() {
		return this.rightTrigger() > Joystick.TRIGGER_PRESS_THRESHOLD;
	}

	/**
	 * Returns the current x-position of the left thumb stick.
	 *
	 * @return the current x-position of the left thumb stick.
	 */
	public double leftStickX() {
		return Joystick.scaleJoystickInput(this.baseGamepad.left_stick_x);
	}
	
	/**
	 * Returns {@code true} if the x-position of the left stick is below a set threshold, or
	 * {@code false} otherwise.
	 *
	 * @return {@code true} if the x-position of the left stick is below a set threshold.
	 * @see #JOYSTICK_PRESS_THRESHOLD
	 */
	public boolean leftStickNegativeX() {
		return this.leftStickX() < -Joystick.JOYSTICK_PRESS_THRESHOLD;
	}
	
	/**
	 * Returns {@code true} if the x-position of the left stick is above a set threshold, or
	 * {@code false} otherwise.
	 *
	 * @return {@code true} if the x-position of the left stick is above a set threshold.
	 * @see #JOYSTICK_PRESS_THRESHOLD
	 */
	public boolean leftStickPositiveX() {
		return this.leftStickX() > Joystick.JOYSTICK_PRESS_THRESHOLD;
	}

	/**
	 * Returns the current y-position of the left thumb stick.
	 *
	 * @return the current y-position of the left thumb stick.
	 */
	public double leftStickY() {
		return Joystick.scaleJoystickInput(-this.baseGamepad.left_stick_y);
	}
	
	/**
	 * Returns {@code true} if the y-position of the left stick is below a set threshold, or
	 * {@code false} otherwise.
	 *
	 * @return {@code true} if the y-position of the left stick is below a set threshold.
	 * @see #JOYSTICK_PRESS_THRESHOLD
	 */
	public boolean leftStickNegativeY() {
		return this.leftStickY() < -Joystick.JOYSTICK_PRESS_THRESHOLD;
	}
	
	/**
	 * Returns {@code true} if the y-position of the left stick is above a set threshold, or
	 * {@code false} otherwise.
	 *
	 * @return {@code true} if the y-position of the left stick is above a set threshold.
	 * @see #JOYSTICK_PRESS_THRESHOLD
	 */
	public boolean leftStickPositiveY() {
		return this.leftStickY() > Joystick.JOYSTICK_PRESS_THRESHOLD;
	}

	/**
	 * Returns the current x-position of the right thumb stick.
	 *
	 * @return the current x-position of the right thumb stick.
	 */
	public double rightStickX() {
		return Joystick.scaleJoystickInput(this.baseGamepad.right_stick_x);
	}
	
	/**
	 * Returns {@code true} if the x-position of the right stick is below a set threshold, or
	 * {@code false} otherwise.
	 *
	 * @return {@code true} if the x-position of the right stick is below a set threshold.
	 * @see #JOYSTICK_PRESS_THRESHOLD
	 */
	public boolean rightStickNegativeX() {
		return this.rightStickX() < -Joystick.JOYSTICK_PRESS_THRESHOLD;
	}
	
	/**
	 * Returns {@code true} if the x-position of the right stick is above a set threshold, or
	 * {@code false} otherwise.
	 *
	 * @return {@code true} if the x-position of the right stick is above a set threshold.
	 * @see #JOYSTICK_PRESS_THRESHOLD
	 */
	public boolean rightStickPositiveX() {
		return this.rightStickX() > Joystick.JOYSTICK_PRESS_THRESHOLD;
	}

	/**
	 * Returns the current y-position of the right thumb stick.
	 *
	 * @return the current y-position of the right thumb stick.
	 */
	public double rightStickY() {
		return Joystick.scaleJoystickInput(-this.baseGamepad.right_stick_y);
	}
	
	/**
	 * Returns {@code true} if the y-position of the right stick is below a set threshold, or
	 * {@code false} otherwise.
	 *
	 * @return {@code true} if the y-position of the right stick is below a set threshold.
	 * @see #JOYSTICK_PRESS_THRESHOLD
	 */
	public boolean rightStickNegativeY() {
		return this.rightStickY() < -Joystick.JOYSTICK_PRESS_THRESHOLD;
	}
	
	/**
	 * Returns {@code true} if the y-position of the right stick is above a set threshold, or
	 * {@code false} otherwise.
	 *
	 * @return {@code true} if the y-position of the right stick is above a set threshold.
	 * @see #JOYSTICK_PRESS_THRESHOLD
	 */
	public boolean rightStickPositiveY() {
		return this.rightStickY() > Joystick.JOYSTICK_PRESS_THRESHOLD;
	}
	
	/**
	 * Generates a list of {@code JoystickButton}s that represent all the buttons that are
	 * currently pressed on this joystick.
	 *
	 * @return the buttons currently pressed on the joystick.
	 */
	public List<JoystickButton> toButtonList() {
		List<JoystickButton> pressedButtons = new LinkedList<JoystickButton>();
		if (this.aButton()) {
			pressedButtons.add(JoystickButton.A);
		}
		if (this.bButton()) {
			pressedButtons.add(JoystickButton.B);
		}
		if (this.xButton()) {
			pressedButtons.add(JoystickButton.X);
		}
		if (this.yButton()) {
			pressedButtons.add(JoystickButton.Y);
		}
		if (this.upButton()) {
			pressedButtons.add(JoystickButton.DPAD_UP);
		}
		if (this.downButton()) {
			pressedButtons.add(JoystickButton.DPAD_DOWN);
		}
		if (this.leftButton()) {
			pressedButtons.add(JoystickButton.DPAD_LEFT);
		}
		if (this.rightButton()) {
			pressedButtons.add(JoystickButton.DPAD_RIGHT);
		}
		if (this.leftBumper()) {
			pressedButtons.add(JoystickButton.LEFT_BUMPER);
		}
		if (this.rightBumper()) {
			pressedButtons.add(JoystickButton.RIGHT_BUMPER);
		}
		if (this.leftStickButton()) {
			pressedButtons.add(JoystickButton.LEFT_STICK);
		}
		if (this.rightStickButton()) {
			pressedButtons.add(JoystickButton.RIGHT_STICK);
		}
		if (this.leftTriggerPressed()) {
			pressedButtons.add(JoystickButton.LEFT_TRIGGER);
		}
		if (this.rightTriggerPressed()) {
			pressedButtons.add(JoystickButton.RIGHT_TRIGGER);
		}
		if (this.leftStickNegativeX()) {
			pressedButtons.add(JoystickButton.LEFT_X_NEG);
		}
		if (this.leftStickPositiveX()) {
			pressedButtons.add(JoystickButton.LEFT_X_POS);
		}
		if (this.leftStickNegativeY()) {
			pressedButtons.add(JoystickButton.LEFT_Y_NEG);
		}
		if (this.leftStickPositiveY()) {
			pressedButtons.add(JoystickButton.LEFT_Y_POS);
		}
		if (this.rightStickNegativeX()) {
			pressedButtons.add(JoystickButton.RIGHT_X_NEG);
		}
		if (this.rightStickPositiveX()) {
			pressedButtons.add(JoystickButton.RIGHT_X_POS);
		}
		if (this.rightStickNegativeY()) {
			pressedButtons.add(JoystickButton.RIGHT_Y_NEG);
		}
		if (this.rightStickPositiveY()) {
			pressedButtons.add(JoystickButton.RIGHT_Y_POS);
		}
		return pressedButtons;
	}

}
