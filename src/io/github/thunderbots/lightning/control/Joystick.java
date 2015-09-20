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

import com.qualcomm.robotcore.hardware.Gamepad;

import io.github.thunderbots.lightning.utility.MathUtil;

/**
 * A {@code Joystick} object represents one of the physical game controllers that are
 * connected to the driver station.
 *
 * @author Zach Ohara
 */
public class Joystick {

	/**
	 * The {@code Gamepad} that this object should recieve information from.
	 *
	 * @see com.qualcomm.robotcore.hardware.Gamepad
	 */
	private Gamepad baseGamepad;

	/**
	 * The threshold that must be exceeded by the raw thumb stick values before it should
	 * register at all. The purpose of this value is to prevent random joystick noise and
	 * false movements from being registered and acted upon.
	 */
	public static final double JOYSTICK_THRESHOLD = 0.6;

	/**
	 * The maximum value that can be expected from the thumb stick values.
	 */
	public static final double JOYSTICK_MAX = 1.0;

	/**
	 * The value that should be expected from the thumb sticks when the thumb stick is at
	 * rest.
	 */
	public static final double JOYSTICK_REST = 0.0;

	/**
	 * The minimum value that can be expected from the thumb stick values.
	 */
	public static final double JOYSTICK_MIN = -1.0;

	/**
	 * The maximum value that can be expected from the trigger values.
	 */
	public static final double TRIGGER_MAX = 1.0;

	/**
	 * The maximum value that can be expected from the trigger values.
	 */
	public static final double TRIGGER_MIN = 0.0;

	/**
	 * Constructs a new {@code Joystick} with the given {@code Gamepad} as a base.
	 *
	 * @param baseGamepad the base {@code Gamepad}
	 */
	public Joystick(Gamepad baseGamepad) {
		this.baseGamepad = baseGamepad;
		this.baseGamepad.setJoystickDeadzone((float) Joystick.JOYSTICK_THRESHOLD);
	}

	/**
	 * Scales the given input value to a number between the rest position and the maximum
	 * position of the thumb stick, based on its relative position between the minimum and
	 * maximum positions. This allows the thumb sticks to show a smooth transition between
	 * the minimum and the maximum, even though any value less than the threshold will be
	 * reported as zero.
	 *
	 * @param raw the raw input value from the joystick, between the threshhold and maximum
	 * positions.
	 * @return the corresponding value between the minimum and maximum positions.
	 */
	private static double scaleJoystickInput(double raw) {
		return MathUtil.scaleToRange(raw, new double[] {Joystick.JOYSTICK_THRESHOLD, Joystick.JOYSTICK_MAX},
				new double[] {Joystick.JOYSTICK_REST, Joystick.JOYSTICK_MAX});
	}

	/**
	 * Returns {@code true} if and only if the A-button on the joystick is currently being
	 * pressed.
	 *
	 * @return whether the A-button is being pressed.
	 */
	public boolean aButton() {
		return this.baseGamepad.a;
	}

	/**
	 * Returns {@code true} if and only if the B-button on the joystick is currently being
	 * pressed.
	 *
	 * @return whether the B-button is being pressed.
	 */
	public boolean bButton() {
		return this.baseGamepad.b;
	}

	/**
	 * Returns {@code true} if and only if the X-button on the joystick is currently being
	 * pressed.
	 *
	 * @return whether the X-button is being pressed.
	 */
	public boolean xButton() {
		return this.baseGamepad.x;
	}

	/**
	 * Returns {@code true} if and only if the Y-button on the joystick is currently being
	 * pressed.
	 *
	 * @return whether the Y-button is being pressed.
	 */
	public boolean yButton() {
		return this.baseGamepad.y;
	}

	/**
	 * Returns {@code true} if and only if the up-direction button on the joystick is
	 * currently being pressed.
	 *
	 * @return whether the up-direction is being pressed.
	 */
	public boolean upButton() {
		return this.baseGamepad.dpad_up;
	}

	/**
	 * Returns {@code true} if and only if the down-direction button on the joystick is
	 * currently being pressed.
	 *
	 * @return whether the down-direction is being pressed.
	 */
	public boolean downButton() {
		return this.baseGamepad.dpad_down;
	}

	/**
	 * Returns {@code true} if and only if the left-direction button on the joystick is
	 * currently being pressed.
	 *
	 * @return whether the left-direction is being pressed.
	 */
	public boolean leftButton() {
		return this.baseGamepad.dpad_left;
	}

	/**
	 * Returns {@code true} if and only if the right-direction button on the joystick is
	 * currently being pressed.
	 *
	 * @return whether the right-direction is being pressed.
	 */
	public boolean rightButton() {
		return this.baseGamepad.dpad_right;
	}

	/**
	 * Returns {@code true} if and only if the left bumper on the joystick is currently
	 * being pressed.
	 *
	 * @return whether the left bumper is being pressed.
	 */
	public boolean leftBumper() {
		return this.baseGamepad.left_bumper;
	}

	/**
	 * Returns {@code true} if and only if the right bumper on the joystick is currently
	 * being pressed.
	 *
	 * @return whether the right bumper is being pressed.
	 */
	public boolean rightBumper() {
		return this.baseGamepad.right_bumper;
	}

	/**
	 * Returns {@code true} if and only if the left-stick button on the joystick is
	 * currently being pressed.
	 *
	 * @return whether the left-stick button is being pressed.
	 */
	public boolean leftStickButton() {
		return this.baseGamepad.left_stick_button;
	}

	/**
	 * Returns {@code true} if and only if the right-stick button on the joystick is
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
	 * Returns the current position of the right trigger on the joystick.
	 *
	 * @return the current position of the right trigger.
	 */
	public double rightTrigger() {
		return this.baseGamepad.right_trigger;
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
	 * Returns the current y-position of the left thumb stick.
	 *
	 * @return the current y-position of the left thumb stick.
	 */
	public double leftStickY() {
		return Joystick.scaleJoystickInput(-this.baseGamepad.left_stick_y);
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
	 * Returns the current y-position of the right thumb stick.
	 *
	 * @return the current y-position of the right thumb stick.
	 */
	public double rightStickY() {
		return Joystick.scaleJoystickInput(-this.baseGamepad.right_stick_y);
	}

}
