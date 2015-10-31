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

package io.github.thunderbots.lightning.robot;

import io.github.thunderbots.lightning.drive.DriveSystem;
import io.github.thunderbots.lightning.drive.TankDrive;

/**
 * {@code Robot} is an abstract class that should be extended by any class representing a
 * physical robot.
 *
 * @author Pranav Mathur
 */
public abstract class Robot {
	
	public Robot() {
		this.drive = this.createDriveSystem();
	}
	
	/**
	 * Gets an array of Strings representing the names of the motors used for driving.
	 * <p>
	 * If the robot has four motors, this array should be in the format of:
	 *
	 * <pre>
	 *  [front left, front right, back left, back right]
	 * </pre>
	 *
	 * If the robot has only two motors, this array should be in the format of:
	 *
	 * <pre>
	 *  [left, right]
	 * </pre>
	 *
	 * @return the names of the driving motors.
	 */
	protected abstract String[] getDriveMotorNames();
	
	private DriveSystem drive;

	/**
	 * Initializes the robot. The implementation of this method should be used in place of
	 * a constructor. Instance variables for a robot object should initialize from the
	 * available hardware maps in this method.
	 */
	public abstract void initializeRobot();

	/**
	 * Gets a reference to the {@code DriveSystem} being used to control the robot.
	 *
	 * @return the drive system for this robot.
	 */
	public DriveSystem getDrive() {
		return this.drive;
	}

	/**
	 * Constructs a DriveSystem that the robot should use. TankDrive is assumed by default,
	 * but this can be changed on an individual basis by overriding this method.
	 *
	 * @return a constructed {@code DriveSystem} that is specific to this robot or op mode.
	 */
	public DriveSystem createDriveSystem() {
		return new TankDrive(this.getDriveMotorNames());
	}

}
