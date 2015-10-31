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
 * The {@code ControlLayout} interface should be implemented by anything that represents
 * a joystick control layout. The responsibility of any control layout is to convert a
 * joystick state into a forward power and a clockwise power, which are accepted by
 * movement methods in {@link io.github.thunderbots.lightning.drive.DriveSystem DriveSystem}.
 * <p>
 * For example, possibly the most common drive system used in FTC robots is a drive-spin system.
 * In this scheme, the y-axis of the left thumbstick controls the forward/backward power of the
 * robot, and the x-axis of the right thumbstick control the clockwise/counter-clockwise spin
 * of the robot. This is potentially the simplest system, because no math is required to convert
 * the state of the joystick into the forward and clockwise powers that are accepted by
 * {@code DriveSystem}.
 * 
 * @author Jake Ohara
 * @author Sean Knight
 */
public interface ControlLayout {
	
	/**
	 * Gets the forward power that should be sent to the robot based on the current
	 * state of the given joystick.
	 *
	 * @param joy the joystick to use for power calculations.
	 * @return the forward drive power of the robot.
	 */
	public double getForwardPower(Joystick joy);
	
	/**
	 * Gets the clockwise power that should be sent to the robot based on the current
	 * state of the given joystick.
	 *
	 * @param joy the joystick to use for power calculations.
	 * @return the clockwise drive power of the robot.
	 */
	public double getClockwisePower(Joystick joy);
	
}
