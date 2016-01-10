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
 * A control layout is a method of converting a joystick state (i.e left stick forward,
 * right stick backward, etc) into both forward and clockwise power, which are interpreted
 * by methods included in {@link io.github.thunderbots.lightning.drive.DriveSystem DriveSystem}.
 * This package includes many different control layouts, such as DriveSpin, Tank, and Mecanum. It
 * also includes the base methods that all control layouts are meant to return.
 * The Control Layout interface should be implemented by anything that is a joystick control layout.
 * Refer to {@link  io.github.thunderbots.lightning.opmode.TeleOp TeleOp} to see implementation.
 *
 *
 * @author Jake Ohara
 */

package io.github.thunderbots.lightning.control.layout;