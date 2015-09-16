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

/**
 * A {@code CompoundOpMode} is an op mode that includes the functionality of several
 * different op modes.
 * 
 * @author Zach Ohara
 */
public class CompoundOpMode extends LightningOpMode {

	/**
	 * The list of op modes that this compound op mode combines the functionality of.
	 */
	private LightningOpMode[] opmodes;

	/**
	 * Constructs a new compound op mode with the given list of op modes.
	 *
	 * @param opmodes the list of op modes to be combined.
	 * @see #opmodes
	 */
	public CompoundOpMode(LightningOpMode[] opmodes) {
		this.opmodes = opmodes;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void initializeRobot() {
		for (LightningOpMode opmode : this.opmodes) {
			opmode.initializeRobot();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void main() {
		for (LightningOpMode opmode : this.opmodes) {
			opmode.start();
		}
		while (this.opModeIsActive()) {
			for (LightningOpMode opmode : this.opmodes) {
				opmode.loop();
			}
		}
		for (LightningOpMode opmode : this.opmodes) {
			opmode.stop();
		}
	}

}
