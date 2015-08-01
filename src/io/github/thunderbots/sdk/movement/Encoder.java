/*
 * Encoder.java
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

package io.github.thunderbots.sdk.movement;

/**
 * Encoder class
 */
public class Encoder {

	private long zeroPoint;
	private double ticksPerRevolution;
	private double ticksPerInch;

	private static final double DEFAULT_TICKS_PER_REVOLUTION = 0;
	// TODO: fix this number;

	/**
	 * Constructs a new encoder using all default settings 
	 */
	public Encoder() {
		this.reset();
		this.setTicksPerRevolution(DEFAULT_TICKS_PER_REVOLUTION);
		this.setTicksPerInch(0L);
	}

	/**
	 * Constructs a new encoder with a given tick-to-revolution conversion
	 * @param ticks the tick-to-inch conversion for this encoder
	 */
	public Encoder(double ticksPerRevolution) {
		this();
		this.setTicksPerRevolution(ticksPerRevolution);
	}

	/**
	 * Sets the emount of ticks of this encoder per full revolution
	 * @param ticks the amount of ticks in one full revolution
	 */
	public void setTicksPerRevolution(double ticks) {
		this.ticksPerRevolution = ticks;
	}

	/**
	 * Sets the amount of ticks of this encoder per inch of circumferential
	 * rotation
	 * @param ticks the tick-to-inch conversion for this encoder
	 */
	public void setTicksPerInch(double ticks) {
		this.ticksPerInch = ticks;
	}

	/**
	 * Sets the tick-to-inch conversion from the wheel circumference
	 * @param circumference the circumference, in inches, of the wheel that
	 * this encoder is attached to 
	 */
	public void setWheelCircumference(double circumference) {
		this.setTicksPerInch(ticksPerRevolution / circumference);
	}

	/**
	 * Resets the encoder to zero. An immediate call to {@code getPosition()}
	 * should return zero.
	 */
	public void reset() {
		this.zeroPoint = this.currentReading();
	}

	/**
	 * Gets the current position (in ticks) of this encoder.
	 * @return the current position of this encoder;
	 */
	public long getPosition() {
		return this.currentReading() - this.zeroPoint;
	}

	/**
	 * Gets the current position, in a decimal of full rotations, of this
	 * encoder.
	 * @return the number of full revolutions this encoder has turned
	 */
	public double getRevolutions() {
		return this.getPosition() / this.ticksPerRevolution;
	}

	/**
	 * Gets the current circumferential rotation of the wheel this encoder is
	 * attached to.
	 * @return the number of inches the wheel's circumference has turned
	 */
	public double getInches() {
		return this.getPosition() / this.ticksPerInch;
	}

	/**
	 * Gets the current reading of this encoder, without any filtering
	 * @return the encoder's 'raw' position
	 */
	private long currentReading() {
		throw new UnsupportedOperationException();
		// TODO: Implement from FTC's API
	}

}
