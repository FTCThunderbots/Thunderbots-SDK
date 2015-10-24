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
import io.github.thunderbots.lightning.control.Joystick;

/**
 * The TeleOp class is a base class that all tele-op programs should extend. It will handle
 * everything directly related to driving and moving the robot, but nothing substantial
 * beyond that.
 *
 * @author Zach Ohara
 */
public abstract class TeleOp extends SimpleOpMode {

	@Override
	protected void main() {
		while (this.opModeIsActive()) {
			this.setMovement();
			this.mainLoop();
		}
	}

	/**
	 * Sets the instantaneous movement of the robot based on readings from the joysticks.
	 * This can be overridden by any op mode that should not use the default drive/turn
	 * controls.
	 */
	protected void setMovement() {
		Joystick drivingGamepad = Lightning.getJoystick(1);
		this.getDrive().setMovement(drivingGamepad.leftStickY(), drivingGamepad.rightStickX());
	}

	/**
	 * Executes the 'body' of any tele op. Everything controlled by a teleop that would
	 * normally be done inside a while loop should be placed in this method. Movement is
	 * excluded from this, and should instead be defined in {@link #setMovement()}.
	 */
	protected void mainLoop() {

	}

}
