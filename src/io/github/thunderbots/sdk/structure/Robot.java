/* 
 * Robot.java
 * Copyright (C) 2015 Thunderbots-5604
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

package io.github.thunderbots.sdk.structure;

import io.github.thunderbots.sdk.background.BackgroundTaskManager;
import io.github.thunderbots.sdk.drive.DriveSystem;
import io.github.thunderbots.sdk.drive.TankDrive;
import io.github.thunderbots.sdk.utility.GameTimeManager;

public class Robot {

	private BackgroundTaskManager taskManager;
	private DriveSystem driveSystem;
	private GameTimeManager gameTimer;
	// TODO: Add sensors, if any
	// TODO: Add servos, if any
	// TODO: Add functions, if any

	private static final long DEFAULT_TIME_UNTIL_START = 10000L;

	/**
	 * Constructs a new robot using all default settings.
	 */
	public Robot() {
		this(new TankDrive());
	}

	/**
	 * Constructs a new robot with the specified time until start, and a
	 * default drive system.
	 * @param untilStart the time, in milliseconds, between the
	 * creation of this object and the start of the match
	 */
	public Robot(long untilStart) {
		this(untilStart, new TankDrive());
	}

	/**
	 * Contructs a new robot with the specified drive system, and a default
	 * time to wait until the start of the match.
	 * @param driver the drive system for this robot
	 */
	public Robot(DriveSystem driver) {
		this(DEFAULT_TIME_UNTIL_START, driver);
	}

	/**
	 * Constructs a new robot with the specified drive system, and a specified
	 * time to wait until the start of the match.
	 * @param untilStart the time, in milliseconds, between the
	 * creation of this object and the start of the match
	 * @param driver the drive system for this robot
	 */
	public Robot(long untilStart, DriveSystem driver) {
		this.taskManager = new BackgroundTaskManager();
		this.driveSystem = driver;
		this.gameTimer = new GameTimeManager(untilStart);
	}

}
