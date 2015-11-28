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

import io.github.thunderbots.lightning.hardware.Motor;
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
	 * Gets a reference to the {@code MotorSet} used by this drive system.
	 *
	 * @return the {@code MotorSet} for this drive system.
	 * @see #motors
	 */
	public MotorSet getMotorSet() {
		return this.motors;
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
	 * Gets the average value in raw ticks of the encoders for driving,
	 * assuming the robot is traveling in a straight line.
	 *
	 * @return the average value of the encoders for driving.
	 */
	public abstract int getDriveTicks();
	
	/**
	 * Gets the average value in raw ticks of the encoders for driving,
	 * assuming the robot is rotating.
	 *
	 * @return the average value of the encoders for rotating.
	 */	
	public abstract int getRotateTicks();

	/**
	 * Gets the average value in raw ticks of the encoders for driving,
	 * assuming the robot is swinging.
	 *
	 * @return the average value of the encoders for swinging.
	 */
	public abstract int getSwingTicks(boolean clockwise);
	
	/**
	 * Resets the encoder of each motor in the drive system.
	 */
	public void resetEncoders() {
		for (Motor m : this.motors.getMotorArray()) {
			m.getEncoder().reset();
		}
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
	public boolean swing(boolean clockwise, double power) {
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
	public boolean swingSeconds(boolean clockwise, double power, double seconds) {
		return this.swing(clockwise, power) && this.waitAndStop(seconds);
	}
	
	/**
	 * Drives the robot forward with the given power and for the given tick distance,
	 * then stops.
	 *
	 * @param power the forward power; between -1 and 1.
	 * @param ticks the amount of encoder ticks to move for.
	 * @return the success of the operation.
	 * @see #drive(double)
	 */
	public boolean driveTicks(double power, int ticks) {
		int start = this.getDriveTicks();
		int end = start + ticks;
		this.drive(power);
		while (this.getDriveTicks() < end) {
			//do nothing
		}
		this.halt();
		return true;
	}
	
	/**
	 * Spins the robot clockwise with the given power and for the given tick distance,
	 * then stops.
	 *
	 * @param power the clockwise power; between -1 and 1.
	 * @param ticks the amount of encoder ticks to move for.
	 * @return the success of the operation.
	 * @see #rotate(double)
	 */
	public boolean rotateTicks(double power, int ticks) {
		int start = this.getRotateTicks();
		int end = start + ticks;
		this.rotate(power);
		while (this.getRotateTicks() < end) {
			//do nothing
		}
		this.halt();
		return true;
	}
	
	/**
	 * Swings the robot with the given spin and forward power, and for the given tick distance,
	 * then stops.
	 *
	 * @param clockwise the clockwise power; between -1 and 1.
	 * @param power the forward power; between -1 and 1.
	 * @param ticks the amount of encoder ticks to move for.
	 * @return the success of the operation.
	 * @see #drive(double)
	 */
	public boolean swingTicks(boolean clockwise, double power, int ticks) {
		int start = this.getSwingTicks(clockwise);
		int end = start + ticks;
		this.swing(clockwise, power);
		while (this.getSwingTicks(clockwise) < end) {
			//do nothing
		}
		this.halt();
		return true;
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
