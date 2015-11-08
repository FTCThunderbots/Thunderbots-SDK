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

package io.github.thunderbots.lightning.drive;

import io.github.thunderbots.lightning.hardware.MotorSet;

/**
 * A {@code DriveSystem} represents the motors used for driving the robot, their
 * arrangement, and in some cases, the type of wheels attached to the motor. Every subclass
 * of {@code DriveSystem} must be able to accept two vectors, forward and clockwise, and
 * calculate the exact power that must be sent to the individual motors for the robot to
 * move with the given two vectors.
 *
 * @author Zach Ohara
 */
public abstract class DriveSystem {

	/**
	 * The motors in this drive system.
	 *
	 * @see io.github.thunderbots.lightning.hardware.MotorSet
	 */
	private MotorSet motors;

	/**
	 * Constructs a new {@code DriveSystem} with the given {@code DriveMotorSet} as a base.
	 *
	 * @param wheels the {@code DriveMotorSet} of this drive system.
	 */
	public DriveSystem(MotorSet wheels) {
		this.motors = wheels;
	}

	/**
	 * Constructs a new {@code DriveSystem} that uses the motors with the given names.
	 *
	 * @param motornames the names of the motors to use with this drive system.
	 */
	public DriveSystem(String[] motornames) {
		this.motors = new MotorSet(motornames);
	}
	
	/**
	 * Gets and returns the average power of all encoder powers within the {@code DriveSystem}
	 * 
	 * @return an average of all encoder powers.
	 */
	public int getEncoderAverage() {
		long sum = 0;
		for (int i = 0; i < this.motors.getMotorArray().length; i++) {
			sum += this.motors.getMotorArray()[i].getEncoder().getPosition();
		}
		return (int) (sum / this.motors.getMotorArray().length);
	}
		
	/**
	 * Sets the power of the motors on the robot so that the robot moves as described by
	 * the two vectors.
	 *
	 * @param forward the forward-driving vector; between -1 and 1.
	 * @param clockwise the clockwise-spinning vector; between -1 and 1.
	 * @return the success of the operation.
	 */
	public abstract boolean setMovement(double forward, double clockwise);

	/**
	 * Gets a reference to the {@code DriveMotorSet} used by this drive system.
	 *
	 * @return the {@code DriveMotorSet} for this drive system.
	 * @see #motors
	 */
	protected MotorSet getWheelSet() {
		return this.motors;
	}

	/**
	 * Stops the robot.
	 *
	 * @return the success of the operation.
	 */
	public boolean halt() {
		return this.setMovement(0, 0);
	}

	/**
	 * Drives the robot forward with the given power.
	 *
	 * @param power the forward power; between -1 and 1.
	 * @return the success of the operation.
	 */
	public boolean drive(double power) {
		return this.setMovement(power, 0);
	}

	/**
	 * Spins the robot clockwise with the given power.
	 *
	 * @param power the clockwise power; between -1 and 1.
	 * @return the success of the operation.
	 */
	public boolean rotate(double power) {
		return this.setMovement(0, power);
	}

	/**
	 * Swings the robot with the given spin and forward power.
	 *
	 * @param clockwise {@code true} if the robot should swing clockwise, or {@code false}
	 * if the robot should spin counter-clockwise.
	 * @param power the forward power; between -1 and 1.
	 * @return the success of the operation.
	 */
	public boolean swing(boolean clockwise, int power) {
		int directionMultiplier = clockwise ? 1 : -1;
		return this.setMovement(power, Math.abs(power) * directionMultiplier);
	}

	/**
	 * Drives the robot forward with the given power and for the given time, then stops.
	 *
	 * @param power the forward power; between -1 and 1.
	 * @param seconds the time to move for.
	 * @return the success of the operation.
	 * @see #drive(double)
	 */
	public boolean driveSeconds(double power, double seconds) {
		return this.drive(power) && this.waitAndStop(seconds);
	}

	/**
	 * Spins the robot clockwise with the given power and for the given time, then stops.
	 *
	 * @param power the clockwise power; between -1 and 1.
	 * @param seconds the time to move for.
	 * @return the success of the operation.
	 * @see #rotate(double)
	 */
	public boolean rotateSeconds(double power, double seconds) {
		return this.rotate(power) && this.waitAndStop(seconds);
	}

	/**
	 * Swings the robot with the given spin and forward power, then stops after the given
	 * amount of time.
	 *
	 * @param clockwise the clockwise power; between -1 and 1.
	 * @param power the forward power; between -1 and 1.
	 * @param seconds the time to move for.
	 * @return the success of the operation.
	 * @see #swing(boolean, int)
	 */
	public boolean swingSeconds(boolean clockwise, int power, float seconds) {
		return this.swing(clockwise, power) && this.waitAndStop(seconds);
	}

	/**
	 * Waits the given amount of time, then stops the robot.
	 *
	 * @param seconds the time to wait before stopping the robot.
	 * @return {@code true} if no interrupt exception was thrown during the wait.
	 */
	public boolean waitAndStop(double seconds) {
		boolean uninterrupted = true;
		try {
			Thread.sleep((long) (seconds * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
			uninterrupted = false;
		} finally {
			this.halt();
		}
		return uninterrupted;
	}

}
