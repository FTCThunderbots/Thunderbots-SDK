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

package io.github.thunderbots.lightning.functionality;

/**
 * A {@code Correctable} object is anything that can have an error.
 * This allows a device to have a PID correcting value.
 *
 * @author Daniel Grimshaw
 */
public interface Correctable {
	/**
	 * Get the error associated with the device. 0 is no error, error
	 * usually works best when it produces a normal curve.
	 *
	 * @return A scalar double representing the error in the device
	 */
	public double getError();
}
