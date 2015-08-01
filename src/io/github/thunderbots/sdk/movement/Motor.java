/* 
 * Motor.java
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

import io.github.thunderbots.sdk.movement.Encoder;
import io.github.thunderbots.sdk.pid.PIDManager;

public class Motor {

	private Encoder encoder;
	private PIDManager pid;
	private int target; 
	private int align;
	private int speed;

	public final static int MAX_POWER = 100;
	public final static int MIN_POWER = 5;

	public Motor() {
		this.encoder = new Encoder();
		this.pid = new PIDManager(); // use default PID settings
		this.align = this.speed = 0;
	}

	public void move(int power) {
		// Depends on ftc sdk
		this.speed = power;
	}

	synchronized public void set(long value) {
		this.target = (int)value;
	}

	public void setAlignmentCorrection(double correction) {
		this.align = (int)(Math.round(correction));
	}

	synchronized public long getError() {
		return target - (encoder.getPosition());
	}

	public Encoder getEncoder() {
		return this.encoder;
	}

	public void run() {
		while (true) {
			if (encoder == null)
				this.move(target);	// We set motor power, not motor position (no encoders)
			else {	// There are encoders
				int power = bound((int)(Math.round(pid.getCorrection())));
				this.move(power+this.align);
			}

			if (this.isStopped())
				encoder.reset();
		}
	}

	private boolean isStopped() {
		return this.speed == 0;
	}

	private int bound(int power) {
		return power > Motor.MAX_POWER ? Motor.MAX_POWER : (power < -(Motor.MAX_POWER) ? -(Motor.MAX_POWER) : power);
	}

}

