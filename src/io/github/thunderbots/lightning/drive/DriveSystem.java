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

import io.github.thunderbots.lightning.functionality.Correctable;
import io.github.thunderbots.lightning.functionality.PID;
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
 * @author Daniel Grimshaw
 */
public abstract class DriveSystem implements Correctable {

	/**
	 * Enumeration of movement types.
	 * Used by get_error.
	 * 
	 * @see #get_error
	 * 
	 * @author Daniel Grimshaw
	 */
	private enum MovementType {
		DRIVE,
		ROTATE,
		SWING,
		NONE
	}
	
	/**
	 * The motors in this drive system.
	 *
	 * @see io.github.thunderbots.lightning.hardware.MotorSet
	 */
	private MotorSet motors;
	
	/**
	 * The Proportional Integral Derivative controller.
	 * 
	 * @see io.github.thunderbots.lightning.functionality.PID
	 */
	private PID pid;

	/**
	 * Describes the movement type for this.get_error()
	 * 
	 * @see #get_error()
	 */
	private MovementType movementType;
	
	/**
	 * Target location for PID
	 * 
	 * @see #get_error()
	 * @see io.github.thunderbots.lightning.functionality.PID
	 */
	private double targetPos;
	
	/**
	 * Clockwise for PID
	 * 
	 * @see #get_error()
	 */
	private boolean clockwise = false;
	
	/**
	 * Sets the amount of ticks that should be expected if the robot drives forward one inch.
	 * <p>
	 * This number cannot have a default value because different types of encoders will be
	 * used, and each may have a different definition of a tick.
	 * 
	 * @param ticks the amount of encoder ticks in one drive-inch of this drive system.
	 */
	private double encoderTicksPerDriveInch;
	
	/**
	 * Sets the amount of ticks that should be expected if the robot rotates one degree.
	 * <p>
	 * This number cannot have a default value because different types of encoders will be
	 * used, and each may have a different definition of a tick.
	 *
	 * @param ticks the amount of encoder ticks in one rotation degree of this drive system.
	 */
	private double encoderTicksPerRotationDegree;
	
	/**
	 * Sets the amount of ticks that should be expected if the robot swings one degree.
	 * <p>
	 * This number cannot have a default value because different types of encoders will be
	 * used, and each may have a different definition of a tick.
	 *
	 * @param ticks the amount of encoder ticks in one swing degree of this drive system.
	 */
	private double encoderTicksPerSwingDegree;

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
	
	/* 
	 * +-----------------------------------------+
	 * |                                         |
	 * |    Encoder setup and utility methods    |
	 * |                                         |
	 * +-----------------------------------------+
	 */
	
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
	 * Get the error associated with the device. 0 is no error, error
	 * usually works best when it produces a normal curve.
	 *
	 * @return A scalar double representing the error in the device
	 */
	public double get_error() {
		switch(this.movementType) {
		case DRIVE:
			return this.targetPos - this.getDriveTicks();
		case ROTATE:
			return this.targetPos - this.getRotateTicks();
		case SWING:
			return this.targetPos - this.getSwingTicks(this.clockwise);
		case NONE:
			return 0.0;
		default:
			throw new UnsupportedOperationException("Unknown movement type");			
		}
	}
	
	/**
	 * Converts between drive inches and encoder ticks.
	 * 
	 * @param inches inches to convert.
	 * @return number of encoder ticks.
	 */
	private double driveInchesToTicks(double inches) {
		return inches * this.encoderTicksPerDriveInch;
	}
	
	/**
	 * Converts between rotation degrees and encoder ticks.
	 * 
	 * @param degrees degrees to convert.
	 * @return number of encoder ticks.
	 */
	private double rotateDegreesToTicks(double degrees) {
		return degrees * this.encoderTicksPerRotationDegree;
	}
	
	/**
	 * Converts between swing degrees and encoder ticks.
	 * 
	 * @param degrees degrees to convert.
	 * @return the number of encoder ticks.
	 */
	private double swingDegreesToTicks(double degrees) {
		return degrees * this.encoderTicksPerSwingDegree;
	}
	
	/**
	 * Gets the distance that the robot has driven forward since the last encoder reset.
	 * <p>
	 * If other (non-drive) movement has occurred since the last encoder reset, this method
	 * will not return an accurate result.
	 *
	 * @return the distance, in inches, that the robot has driven forward.
	 */
	public double getDriveInches() {
		return this.getDriveTicks() / this.encoderTicksPerDriveInch;
	}
	
	/**
	 * Gets the degrees that the robot has rotated clockwise since the last encoder reset.
	 * <p>
	 * If other (non-rotation) movement has occurred since the last encoder reset, this method
	 * will not return an accurate result.
	 *
	 * @return the degrees that the robot has rotated clockwise.
	 */
	public double getRotationDegrees() {
		return this.getRotateTicks() / this.encoderTicksPerRotationDegree;
	}
	
	/**
	 * Gets the degrees that the robot has swung in the given direction since the last encoder reset.
	 * <p>
	 * If other (non-swing) movement has occurred since the last encoder reset, this method
	 * will not return an accurate result.
	 *
	 * @param clockwise {@code true} if the robot should swing clockwise, or {@code false}
	 * if the robot should spin counter-clockwise.
	 * @return the degrees that the robot has swung in the given direction.
	 */
	public double getSwingDegrees(boolean clockwise) {
		return this.getSwingTicks(clockwise) / this.encoderTicksPerSwingDegree;
	}
	
	/**
	 * Resets the encoder of each motor in the drive system.
	 */
	public void resetEncoders() {
		for (Motor m : this.motors.getMotorArray()) {
			m.getEncoder().reset();
		}
	}
	
	/**
	 * Sets the amount of ticks that should be expected if the robot drives forward one inch.
	 * 
	 * @param ticks the amount of encoder ticks in one drive-inch of this drive system.
	 * @see #encoderTicksPerDriveInch
	 */
	public void setEncoderTicksPerDriveInch(double ticks) {
		this.encoderTicksPerDriveInch = ticks;
	}
	
	/**
	 * Sets the amount of ticks that should be expected if the robot rotates one degree.
	 *
	 * @param ticks the amount of encoder ticks in one rotation degree of this drive system.
	 * @see #encoderTicksPerRotationDegree
	 */
	public void setEncoderTicksPerRotationDegree(double ticks) {
		this.encoderTicksPerRotationDegree = ticks;
	}
	
	/**
	 * Sets the amount of ticks that should be expected if the robot swings one degree.
	 *
	 * @param ticks the amount of encoder ticks in one swing degree of this drive system.
	 * @see #encoderTicksPerSwingDegree
	 */
	public void setEncoderTicksPerSwingDegree(double ticks) {
		this.encoderTicksPerSwingDegree = ticks;
	}
	
	/*
	 * +-----------------------------------+
	 * |                                   |
	 * |       Raw movement methods:       |
	 * |      No time and no encoders      |
	 * |                                   |
	 * +-----------------------------------+
	 */

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
	
	/*
	 * +-----------------------------------+
	 * |                                   |
	 * |    Time-based movement methods    |
	 * |                                   |
	 * +-----------------------------------+
	 */

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
	 * @param clockwise {@code true} if the robot should swing clockwise, or {@code false}
	 * if the robot should spin counter-clockwise.
	 * @param power the forward power; between -1 and 1.
	 * @param seconds the time to move for.
	 * @return the success of the operation.
	 * @see #swing(boolean, int)
	 */
	public boolean swingSeconds(boolean clockwise, double power, double seconds) {
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
	
	/*
	 * +-----------------------------------+
	 * |                                   |
	 * |  Encoder-based movement methods   |
	 * |                                   |
	 * +-----------------------------------+
	 */
	
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
	 * @param clockwise {@code true} if the robot should swing clockwise, or {@code false}
	 * if the robot should spin counter-clockwise.
	 * @param power the forward power; between -1 and 1.
	 * @param ticks the amount of encoder ticks to move for.
	 * @return the success of the operation.
	 * @see #swing(boolean, double)
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
	 * Drives the robot forward with the given power and for the given distance,
	 * then stops.
	 *
	 * @param power the forward power; between -1 and 1.
	 * @param inches the distance to drive forward, in inches.
	 * @return the success of the operation.
	 */
	public boolean driveInches(double power, double inches) {
		return this.driveTicks(power, (int)(this.driveInchesToTicks(inches)));
	}
	
	/**
	 * Spins the robot clockwise with the given power and for the given amount of degrees,
	 * then stops.
	 *
	 * @param power the clockwise power; between -1 and 1.
	 * @param degrees the degrees to rotate the robot.
	 * @return the success of the operation.
	 * @see #rotate(double)
	 */
	public boolean rotateDegrees(double power, double degrees) {
		return this.rotateTicks(power, (int)(this.rotateDegreesToTicks(degrees)));
	}
	
	/**
	 * Swings the robot with the given spin and forward power, and for the given amount of degrees,
	 * then stops.
	 *
	 * @param clockwise {@code true} if the robot should swing clockwise, or {@code false}
	 * if the robot should spin counter-clockwise.
	 * @param power the forward power; between -1 and 1.
	 * @param ticks the amount of encoder ticks to move for.
	 * @return the success of the operation.
	 * @see #swing(boolean, double)
	 */
	public boolean swingDegrees(boolean clockwise, double power, double degrees) {
		return this.swingTicks(clockwise, power, (int)(this.swingDegreesToTicks(degrees)));
	}

}
