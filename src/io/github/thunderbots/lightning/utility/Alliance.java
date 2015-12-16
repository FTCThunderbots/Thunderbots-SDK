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

package io.github.thunderbots.lightning.utility;

/**
 * The {@code Alliance} is an enumeration of possible sides of the field. This could be
 * used to write autonomous code that can adapt to changing sides of the field.
 */
public enum Alliance {

	BLUE(1),
	RED(-1);

	/**
	 * The integer that represents the side of the field. Blue is 1, and red is -1.
	 */
	private final int side;

	/**
	 * Constructs a new {@code Alliance}.
	 *
	 * @param side the side of the field that this alliance represents.
	 */
	private Alliance(int side) {
		this.side = side;
	}

	/**
	 * Gets the integer side of the field.
	 *
	 * @return the side of the field.
	 * @see #side
	 */
	public int getSide() {
		return this.side;
	}

}
