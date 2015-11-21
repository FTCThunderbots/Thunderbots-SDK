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
 * A {@code DriveSpinControlLayout} is a {@code ControlLayout} that represents the 'default'
 * control layout for FTC robots. The y-axis of the left thumbstick is responsible for
 * forward/backward movement, and the x-axis of the right thumbstick is responsible for the
 * clockwise/counter-clockwise spin of the robot.
 * 
 * @author Zach Ohara
 */
public class DriveSpinControlLayout implements ControlLayout {
	
	@Override
	public double getForwardPower(Joystick joy) {
		return joy.leftStickY();
	}
	
	@Override
	public double getClockwisePower(Joystick joy) {
		return joy.rightStickX();
	}
	
}
