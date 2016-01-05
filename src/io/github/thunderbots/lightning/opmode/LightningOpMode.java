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

package io.github.thunderbots.lightning.opmode;

import io.github.thunderbots.lightning.Lightning;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * The {@code LightningOpMode} is a customized version of {@code LinearOpMode} that should
 * be the superclass for all op modes written using Lightning SDK.
 *
 * @author Zach Ohara
 */
public abstract class LightningOpMode extends LinearOpMode {

	/**
	 * Determines if the op mode has be <em> fully </em> initialized. The overridden
	 * {@code initializeOpMode()} method will always be called, but serious problems are
	 * caused later if any initialization method does not call the initialization of its
	 * superclass. This value is used to determine if the initialization method defined in
	 * {@code LightningOpMode} has been called, indicating if any method did not call the
	 * supertype method.
	 * <p>
	 * This value is initialized to false, and only set to true once the version of
	 * {@link #initializeOpMode()} defined in {@code LightningOpMode} has been called.
	 */
	private boolean isInitialized;

	public LightningOpMode() {
		this.isInitialized = false;
	}

	/**
	 * Initializes the robot. This can be defined however it is seen fit by the specific
	 * overriding op mode. Any op mode that overrides this method should also call
	 * {@code super.initializeOpMode()} on the first line.
	 *
	 * @see #isInitialized
	 */
	protected void initializeOpMode() {
		this.isInitialized = true;
	}

	/**
	 * Executes the 'main procedure' of the op mode. This method will run either until
	 * interrupted by the system, or until the method returns.
	 */
	protected abstract void main();

	@Override
	public final void runOpMode() throws InterruptedException {
		Lightning.initializeLightning(this);
		this.initializeOpMode();
		if (!this.isInitialized) {
			throw new IllegalStateException("Op mode was not fully initialized. "
					+ "You are probably missing a call to super.initializeOpMode()");
		}
		this.waitForStart();
		this.main();
	}

}
