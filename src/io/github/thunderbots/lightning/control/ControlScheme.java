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

package io.github.thunderbots.lightning.control;

/**
 * @author Jake Ohara
 * @author Sean Knight
 */
public interface ControlScheme {
	
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
