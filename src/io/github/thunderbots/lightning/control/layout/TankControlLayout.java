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

package io.github.thunderbots.lightning.control.layout;

import io.github.thunderbots.lightning.control.Joystick;

/**
 * A {@code TankControlLayout} is a {@code ControlLayout} that represents a tank-like
 * control system. In a tank control system, the y-axis of the left thumbstick is used
 * to control the forward/backward power of the left side of the robot, and the right
 * thumbstick is used to control the forward/backward power of the right side of the
 * robot.
 * <p>
 * Since the movement methods of {@link io.github.thunderbots.lightning.drive.DriveSystem
 * DriveSystem} accept only a single forward vector and a single clockwise vector, some
 * math is required to convert the relevant joystick inputs to a form that can be used by
 * the drive system. The forward power is calculated as the average of the two y-values,
 * and the clockwise power is calculated as the difference between the y-values of the two
 * thumbsticks.
 */
public class TankControlLayout implements ControlLayout {

	@Override
	public double getForwardPower(Joystick joy) {
		return (joy.leftStickY() + joy.rightStickY()) / 2;
	}

	@Override
	public double getClockwisePower(Joystick joy) {
		return (joy.leftStickY() - joy.rightStickY()) / 2;
	}

}
