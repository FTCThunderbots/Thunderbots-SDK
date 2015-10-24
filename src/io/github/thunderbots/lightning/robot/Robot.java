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

/**
 * {@code Robot} is an interface that should be implemented by any class representing a
 * physical robot. In the future, this interface will be used to provide more seamless
 * operation between op modes objects and robot objects.
 *
 * @author Pranav Mathur
 */
public interface Robot {

	/**
	 * Initializes the robot. The implementation of this method should be used in place of
	 * a constructor. Instance variables for a robot object should initialize from the
	 * available hardware maps in this method.
	 */
	public void initializeRobot();

}
