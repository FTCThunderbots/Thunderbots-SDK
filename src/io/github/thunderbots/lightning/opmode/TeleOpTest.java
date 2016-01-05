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
import io.github.thunderbots.lightning.utility.Telemetry;

/**
 * The {@code TeleOpTest} class is a base class that acts as a 'dummy' tele op. Everything
 * is exactly the same between a {@code TeleOp} and a {@code TeleOpTest}, except that a
 * tele op test program outputs the forward and clockwise motor powers to the screen of the
 * driver station phone, instead of actually controlling any motors.
 *
 * @author Zach Ohara
 */
public abstract class TeleOpTest extends TeleOp {

	@Override
	protected void setMovement() {
		Joystick drivingGamepad = Lightning.getJoystick(1);
		double forwardPower = this.getControlLayout().getForwardPower(drivingGamepad);
		double clockwisePower = this.getControlLayout().getClockwisePower(drivingGamepad);
		Telemetry.sendData("forward", forwardPower);
		Telemetry.sendData("Clockwise", clockwisePower);
	}

}
