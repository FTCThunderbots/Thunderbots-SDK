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

import io.github.thunderbots.lightning.utility.MathUtil;

/**
 * A {@code Servo} represents any physical servo on that is connected to the robot.
 *
 * @author Zach Ohara
 */
public class Servo {

	/**
	 * The servo that this object is based on.
	 */
	private com.qualcomm.robotcore.hardware.Servo baseServo;

	/**
	 * The 'center' position of this servo. The default value is {@link #CENTER_POSITION}.
	 */
	private double centerPos;

	/**
	 * The minimum position of the servo. At the time of writing this, it is currently 0.0,
	 * but this may change in the future.
	 */
	public static final double MIN_POSITION = com.qualcomm.robotcore.hardware.Servo.MIN_POSITION;

	/**
	 * The maximum position of the servo. At the time of writing this, it is currently 1.0,
	 * but this may change in the future.
	 */
	public static final double MAX_POSITION = com.qualcomm.robotcore.hardware.Servo.MAX_POSITION;

	/**
	 * The default center position of the servo. It is the average value of the minimum and
	 * maximum positions.
	 */
	public static final double CENTER_POSITION = (Servo.MIN_POSITION + Servo.MAX_POSITION) / 2;

	/**
	 * Constructs a new servo that uses the given robotcore {@code Servo} as a base.
	 *
	 * @param baseServo the base robotcore {@code Servo}
	 */
	public Servo(com.qualcomm.robotcore.hardware.Servo baseServo) {
		this.baseServo = baseServo;
		this.centerPos = Servo.CENTER_POSITION;
	}

	/**
	 * Gets the name of the device as it is defined in the configuration file.
	 *
	 * @return the name of the servo.
	 */
	public String getName() {
		return this.baseServo.getDeviceName();
	}

	/**
	 * Gets the 'center' position of this servo.
	 *
	 * @see #centerPos
	 * @return the 'center' position of this servo.
	 */
	public double getCenterPosition() {
		return this.centerPos;
	}

	/**
	 * Sets the 'center' position of this servo.
	 *
	 * @see #centerPos
	 * @param position the new 'center' position of this servo.
	 */
	public void setCenterPosition(double position) {
		this.centerPos = position;
	}

	/**
	 * Gets the current position of this servo.
	 *
	 * @return the current position of this servo.
	 */
	public double getPosition() {
		return this.baseServo.getPosition();
	}

	/**
	 * Moves the servo to the given position.
	 *
	 * @param position the position to move to.
	 */
	public void moveToPosition(double position) {
		this.baseServo.setPosition(position);
	}

	/**
	 * Moves the servo to its current position plus the given increment.
	 *
	 * @param increment the change in servo position.
	 */
	public void move(double increment) {
		double newPosition = this.getPosition() + increment;
		newPosition = MathUtil.truncateToRange(newPosition, Servo.MIN_POSITION, Servo.MAX_POSITION);
		this.moveToPosition(newPosition);
	}

	/**
	 * Moves the servo to its center position.
	 */
	public void center() {
		this.moveToPosition(this.centerPos);
	}

	@Override
	public String toString() {
		return this.baseServo.toString();
	}

}
