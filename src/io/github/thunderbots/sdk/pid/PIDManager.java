/* 
 * PIDManager.java
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

package io.github.thunderbots.sdk.pid;

import io.github.thunderbots.sdk.utility.Timer;

public class PIDManager {

	@SuppressWarnings("unused")
	private Timer timer; // PID is based on clocks

	private final double windup_guard; //Integral cap
	private final double p_gain; // Kp
	private final double i_gain; // Ki
	private final double d_gain; // Kd
	private double prev_error; // Used for derivative
	private double integral_error; // Integral error is cumulative
	private double control;

	// format: {Kp, Ki, Kd, integral cap}
	private static final double[] DEFAULT_PID_VALUES = {0.2, 0.01, 1.0, 10.0};

	public PIDManager() {
		this(DEFAULT_PID_VALUES);
	}

	public PIDManager(double kp, double ki, double kd) {
		this(kp, ki, kd, DEFAULT_PID_VALUES[3]);
	}

	public PIDManager(double kp, double ki, double kd, double integral_cap) {
		this.timer = new Timer();
		this.p_gain = kp;
		this.i_gain = ki;
		this.d_gain = kd;
		this.windup_guard = integral_cap;
		this.zeroize();
	}

	public PIDManager(double [] pidConstants, double integral_cap) {
		this(pidConstants[0], pidConstants[1], pidConstants[2], integral_cap);
	}

	public PIDManager(double[] pidConstants) {
		this(pidConstants , pidConstants[3]);
	}

	public void zeroize() {
		this.prev_error = 0;
		this.integral_error = 0;
		this.control = 0;
	}

	@SuppressWarnings("unused")
	synchronized private double correct(long error, long dt) {
		double diff;
		double p;
		double i;
		double d;

		// integration with windup guarding
		this.integral_error += (error * dt);
		if (this.integral_error < -(this.windup_guard))
			this.integral_error = -(this.windup_guard);
		else if (this.integral_error > this.windup_guard)
			this.integral_error = this.windup_guard;

		// differentiation
		diff = ((double)(error - this.prev_error) / (double)dt);

		// scaling
		p = (this.p_gain * error);
		i = (this.i_gain * this.integral_error);
		d = (this.d_gain * diff);

		// Save the error for future reference
		this.prev_error = error;

		// The sum is the correction value
		return p+i+d;
	}

	synchronized public double getCorrection() {
		return this.control;
	}

	/*
	 * This method needs to be redone to fit the background task architecture
	 * 
	public void run() {
		timer.waitForStart();
		long lastTime = timer.getTime();
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {}

		while (true) {
			if (timer.gameIsRunning()) {
				this.control = this.correct(device.getError(), timer.deltaT(lastTime));
				lastTime = timer.getTime();
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {}
			}
		}
	}
	 */
}
