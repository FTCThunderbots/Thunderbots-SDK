/* Copyright (C) 2015 Zach Ohara
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

package io.github.thunderbots.lightning.drive;

import java.util.Arrays;

import io.github.thunderbots.lightning.Lightning;
import io.github.thunderbots.lightning.hardware.Motor;

/**
 * A {@code DriveMotorSet} is a collection of motors used for the sole purpose of driving the
 * robot.
 * 
 * @author Zach Ohara
 */
public class DriveMotorSet {

	/**
	 * The array of motors that this {@code DriveMotorSet} comprises.
	 */
	private Motor[] motors;

	/**
	 * Constructs a new {@code DriveMotorSet} using the motors with the given names.
	 *
	 * @param names the names of all the motors to use with this motor set.
	 */
	public DriveMotorSet(String[] names) {
		this.motors = new Motor[names.length];
		for (int i = 0; i < names.length; i++) {
			this.motors[i] = Lightning.getMotor(names[i]);
		}
	}

	/**
	 * Set the power of the motors in this motor set from the corresponding power values
	 * in the given double array. For example, the motor at {@code motors[n]} will be
	 * assigned the power value at {@code powers[n]}. If the lengths of {@code motors}
	 * and {@code powers} are mismatched, then all the available pairs will be matched, and
	 * the extra values will be discarded.
	 *
	 * @param powers the power values to assign to the corresponding motors.
	 */
	public void setMotorPowers(double[] powers) {
		Lightning.sendTelemetryData("Motors", Arrays.toString(powers));
		int motorSet = Math.min(powers.length, this.motors.length);
		for (int i = 0; i < motorSet; i++) {
			this.motors[i].setPower(powers[i]);
		}
	}

}
