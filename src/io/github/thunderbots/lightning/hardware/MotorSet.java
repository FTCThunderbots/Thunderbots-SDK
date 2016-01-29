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

import io.github.thunderbots.lightning.Lightning;

/**
 * A {@code MotorSet} is a collection of motors that should move in unison. The most common
 * use for this functionality is in a drive system, because all the motors of a drive
 * system move together.
 * <p>
 * It is <b> not </b> required that all of the motors in a motor set move
 * <em> exactly </em> in usison. In some cases, this would be catastrophic. In the case of
 * a drive system, the robot would only be able to spin clockwise or counterclockwise.
 * Instead, a motor set comprises motors that should be controlled together, even if they
 * should be given different individual powers.
 *
 * @author Zach Ohara
 * @author Daniel Grimshaw
 */
public class MotorSet {

	/**
	 * The array of motors that this {@code MotorSet} comprises.
	 */
	private Motor[] motors;

	/**
	 * Constructs a new {@code MotorSet} using the motors with the given names.
	 *
	 * @param names the names of all the motors to use in this motor set.
	 */
	public MotorSet(String[] names) {
		this.motors = new Motor[names.length];
		for (int i = 0; i < names.length; i++) {
			this.motors[i] = Lightning.getMotor(names[i]);
		}
	}

	/**
	 * Constructs a new {@code MotorSet} using the given motor array.
	 *
	 * @param motors an array of motors to use in this motor set.
	 */
	public MotorSet(Motor[] motors) {
		this.motors = motors;
	}

	/**
	 * Gets the array of motors that this {@code MotorSet} comprises.
	 *
	 * @return an array of motors.
	 */
	public Motor[] getMotorArray() {
		return this.motors;
	}

	/**
	 * Sets the power of all the motors in this set to the given power.
	 *
	 * @param power the power to send to all the motors in this motor set.
	 */
	public void setMotorPower(double power) {
		for (Motor m : this.motors) {
			m.setPower(power);
		}
	}

	/**
	 * Sets the power of the motors in this motor set from the corresponding power values
	 * in the given double array. For example, the motor at {@code motors[n]} will be
	 * assigned the power value at {@code powers[n]}. If the lengths of {@code motors} and
	 * {@code powers} are mismatched, then all the available pairs will be matched, and the
	 * extra values in either array will be discarded.
	 *
	 * @param powers the power values to assign to the corresponding motors.
	 */
	public void setMotorPowers(double[] powers) {
		int motorSet = Math.min(powers.length, this.motors.length);
		for (int i = 0; i < motorSet; i++) {
			this.motors[i].setPower(powers[i]);
		}
	}

	/**
	 * Set all of the motors to go at speed.
	 * 
	 * Uses PID
	 *
	 * @param speed goal speed of the motors
	 */
	public void setMotorSpeed(double speed) {
		for (Motor m : this.motors) {
			m.setSpeed(speed);
		}
	}
	
	/**
	 * Sets the goal speed of the motors in this motor set from the corresponding speed values
	 * in the given double array. For example, the motor at {@code motors[n]} will be
	 * assigned the goal speed value at {@code speeds[n]}. If the lengths of {@code motors} and
	 * {@code speeds} are mismatched, then all the available pairs will be matched, and the
	 * extra values in either array will be discarded.
	 *
	 * @param speeds the goal speed values to assign to the corresponding motors.
	 */
	public void setMotorSpeeds(double[] speeds) {
		int motorSet = Math.min(speeds.length, this.motors.length);
		for (int i = 0; i < motorSet; i++) {
			this.motors[i].setSpeed(speeds[i]);
		}
	}
	
	/**
	 * Stops all the motors in this motor set
	 */
	public void stopAllMotors() {
		for (Motor m : this.motors) {
			m.stop();
		}
	}

	/**
	 * Gets the average power of all the motors in this motor set.
	 *
	 * @return the average power of the motors in this set.
	 */
	public double getAveragePower() {
		double sum = 0;
		for (Motor m : this.motors) {
			sum += m.getPower();
		}
		return sum / this.motors.length;
	}

	/**
	 * Gets the average encoder reading for all the motors in this motor set.
	 *
	 * @return the average encoder reading for the motors in this set.
	 */
	public int getAverageEncoderValue() {
		long sum = 0;
		for (int i = 0; i < this.motors.length; i++) {
			sum += this.motors[i].getEncoder().getPosition();
		}
		return (int) (sum / this.motors.length);
	}

}
