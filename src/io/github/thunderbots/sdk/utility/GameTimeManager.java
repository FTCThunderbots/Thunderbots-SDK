/* 
 * GameTimeManager.java
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

package io.github.thunderbots.sdk.utility;

public class GameTimeManager extends Timer {

	/* The time the match will start (zero on the match timer) relative to the
	 * time this object was created.
	 */
	private long matchStartTime;
	// The length, in milliseconds, of the period this object is timing
	private int gameLength;

	// The default length, in seconds, of the game 
	public static final int DEFAULT_GAME_LEGNTH = 0;
	// TODO: change this number to something

	/**
	 * Constructs a new time manager with a specified amount of time to wait
	 * until the start of the match.
	 * @param untilStart the time, in milliseconds, between the
	 * creation of this object and the start of the match
	 */
	public GameTimeManager(long untilStart) {
		this(untilStart, DEFAULT_GAME_LEGNTH);
	}

	/**
	 * Constructs a new time manager with a specified amount of time to wait
	 * until the start of the match, and a specified length of the game.
	 * @param untilStart the time, in milliseconds, between the
	 * creation of this object and the start of the match
	 * @param length the duration, in seconds, of the game
	 */
	public GameTimeManager(long untilStart, int length) {
		super();
		this.matchStartTime = untilStart;
		this.gameLength = length;
	}

	/**
	 * Gets the length, in seconds, that this game will last.
	 * @return the length, in seconds, that this game will last.
	 */
	public int getGameLength() {
		return gameLength;
	}

	@Override
	public long getMilliseconds() {
		return super.getMilliseconds() - this.matchStartTime;
	}

	/**
	 * Waits until the match starts.
	 */
	public void waitForStart() {
		while (!this.gameIsRunning());
	}

	/**
	 * Return {@code true} if the game is running (time is positive)
	 * @return {@code true} if the game is running (time is positive); or
	 * {@code false} otherwise
	 */
	public boolean gameIsRunning() {
		return this.getMilliseconds() > 0
				&& this.getMilliseconds() < this.gameLength * 1000;
	}

	@Deprecated
	public static void main(String[] args) {
		System.out.println("T-minus 5 seconds");
		GameTimeManager t = new GameTimeManager(5000, 3);
		t.waitForStart();
		System.out.println("Start!");
		while(t.gameIsRunning());
		System.out.println("Game is over!");
	}
}
