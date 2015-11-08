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

/**
 * The {@link io.github.thunderbots.lightning.drive} package is a system for controlling the
 * drive system of a robot. A drive system is an abstract way of representing the set of
 * motors that are responsible for driving the robot, and their specific arrangement in the
 * drive system. The drive system can handle abstract operations such as driving, turning,
 * and spinning, and is responsible for all the math that is required to distribute those
 * operations to the individual motors comprised by the drive system.
 * <p>
 * The class that primarily represents the concept of a drive system is, fittingly,
 * {@link io.github.thunderbots.lightning.drive.DriveSystem DriveSystem}.
 */
package io.github.thunderbots.lightning.drive;
