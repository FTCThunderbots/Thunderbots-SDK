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
 * The {@code Util} class contains static methods for common utilities.
 *
 * @author Pranav Mathur
 */
public final class Util {

	/**
	 * {@code Util} should not be instantiable.
	 */
	private Util() {

	}

	/**
	 * Blocks the thread for the given amount of milliseconds.
	 *
	 * @param milliseconds the amount of time to block.
	 * @return {@code true} if the block sucedded without being interrupted, or
	 * {@code false} if the thread was interrupted.
	 */
	public static boolean sleep(long milliseconds) {
		try {
			Thread.sleep(milliseconds);;
		} catch (InterruptedException ex) {
			return false;
		}
		return true;
	}

}
