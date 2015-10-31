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

/**
 * A {@code MecanumControlLayout} is a {@code ControlLayout} that can control a robot with
 * a mecanum drive system. There is no 'standard' accepted way to control a mecanum drive
 * system, but in this implementation, the y-axis of the left thumbstick is responsible
 * for forward/backward movement, the x-axis of the left thumbstick is responsible for
 * left/right strafing movement, and the x-axis of the right thumbstick controls
 * clockwise/counter-clockwise rotation of the robot.
 */
public class MecanumControlLayout {

}
