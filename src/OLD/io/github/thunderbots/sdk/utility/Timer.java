/* 
 * Timer.java
 * Copyright (C) 2015 Thunderbots-5604
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

package OLD.io.github.thunderbots.sdk.utility;

public class Timer {

	private long startTime;

	/**
	 * Constructs and starts a timer
	 */
	public Timer() {
		this.reset();
	}

	/**
	 * Reset the timer to start counting from the current time
	 */
	public void reset() {
		this.startTime = System.currentTimeMillis();
	}

	/**
	 * Get the current time, in milliseconds, since this timer was started.
	 * One milleseconds is 1/1000 of a second.
	 * @return the time in milleseconds
	 */
	public long getMilliseconds() {
		return System.currentTimeMillis() - this.startTime;
	}

	/**
	 * Get the current time, in centiseconds, since this timer was started.
	 * One centisecond is 1/100 of a second.
	 * @return the time in centiseconds
	 */
	public long getCentiseconds() {
		return this.getMilliseconds() / 10;
	}

	/**
	 * Get the current time, in deciseconds, since this timer was started.
	 * One centisecond is 1/10 of a second.
	 * @return the time in deciseconds
	 */
	public int getDeciseconds() {
		return (int) (this.getMilliseconds() / 100);
	}

	/**
	 * Get the current time, in seconds, since this timer was started.
	 * @return the time in seconds
	 */
	public int getSeconds() {
		return (int) (this.getMilliseconds() / 1000);
	}

}
