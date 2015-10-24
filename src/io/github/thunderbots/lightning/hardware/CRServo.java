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

package io.github.thunderbots.lightning.hardware;

/**
 * A {@code CRServo} represents any physical continuous-rotation servo that is connected to
 * the robot. Normal servos are limited in their rotation, but a continuous-rotation servo
 * can rotate infinitely. Because of this, a CR servo is functionally more similar to a
 * motor than it is to a servo. This is reflected by {@code CRServo} being a subclass of
 * {@link io.github.thunderbots.lightning.hardware.Motor Motor} instead of a subclass of
 * {@link io.github.thunderbots.lightning.hardware.Servo Servo}.
 * <p>
 * Because of this inheritance, a physical CR servo can be referenced in software as a
 * motor <em>OR</em> as a servo. To use it as a servo, access it through
 * {@code Lightning.getServo(String)}. To use a CR servo as a motor instead, call the
 * {@code Lightning.getMotor(String)} method.
 *
 * @author Zach Ohara
 */
public class CRServo extends Motor {

	/**
	 * The servo that this object is based on.
	 */
	private Servo baseServo;

	/**
	 * Determines if the directionality of this cr servo is reversed or not.
	 */
	private boolean isReversed;

	/**
	 * The maximum power of the servo.
	 */
	public static final double FORWARD = 1;

	/**
	 * The power of the servo when the motor is at rest.
	 */
	public static final double REST = 0.5;

	/**
	 * The maximum power of the servo when the motor is spinning backwards.
	 */
	public static final double REVERSE = 0;

	public CRServo(Servo baseservo) {
		super(null);
	}

	@Override
	public String getName() {
		return this.baseServo.getName();
	}

	/**
	 * @deprecated A CR servo has no way to track its position. Calling this method on a
	 * {@code CRServo} will return null.
	 */
	@Override
	@Deprecated
	public Encoder getEncoder() {
		return null;
	}

	/**
	 * @deprecated A CR servo has no way to track its position. Calling this method on a
	 * {@code CRServo} will return 0.
	 */
	@Override
	@Deprecated
	public int getRawPosition() {
		return 0;
	}

	@Override
	public boolean isReversed() {
		return this.isReversed;
	}

	@Override
	public void setReversed(boolean reversed) {
		this.isReversed = reversed;
	}

	@Override
	public double getPower() {
		if (this.baseServo.getPosition() == CRServo.REVERSE) {
			return Motor.MIN_POWER;
		} else if (this.baseServo.getPosition() == CRServo.FORWARD) {
			return Motor.MAX_POWER;
		} else { // getPosition() == REST
			return Motor.REST_POWER;
		}
	}

	@Override
	public void setPower(double power) {
		if (this.isReversed) {
			power = -power;
		}
		if (power < 0) {
			this.baseServo.moveToPosition(CRServo.REVERSE);
		} else if (power > 0) {
			this.baseServo.moveToPosition(CRServo.FORWARD);
		} else { // power == 0
			this.baseServo.moveToPosition(CRServo.REST);
		}
	}

	@Override
	public String toString() {
		return this.baseServo.toString();
	}

}
