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
	 * The minimum position of the servo. At the time of writing this, it is currently
	 * -0.0, but this may change in the future.
	 */
	public static final double MIN_POSITION = com.qualcomm.robotcore.hardware.Servo.MIN_POSITION;

	/**
	 * The maximum position of the servo. At the time of writing this, it is currently 1.0,
	 * but this may change in the future.
	 */
	public static final double MAX_POSITION = com.qualcomm.robotcore.hardware.Servo.MAX_POSITION;

	/**
	 * Constructs a new servo that uses the given robotcore {@code Servo} as a base.
	 *
	 * @param baseServo the base robotcore {@code Servo}
	 */
	public Servo(com.qualcomm.robotcore.hardware.Servo baseServo) {
		this.baseServo = baseServo;
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
	public void moveTo(double position) {
		this.baseServo.setPosition(position);
	}

	@Override
	public String toString() {
		return this.baseServo.toString();
	}

}
