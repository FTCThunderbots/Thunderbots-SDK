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

import io.github.thunderbots.lightning.functionality.Correctable;
import io.github.thunderbots.lightning.hardware.Motor;

/**
 * A {@code PID} is a class used to automatically calculate the PID values given
 * an error value and a change in time
 *
 * @author Daniel Grimshaw
 */
public class PID implements Runnable {

	/**
	 * The default windup guard.
	 */
	public static final double DEFAULT_WINDUP_GUARD = 10.0;

	/**
	 * The default proportional gain.
	 */
	public static final double DEFAULT_PROPORTIONAL_GAIN = 0.2;

	/**
	 * The default integral gain.
	 */
	public static final double DEFAULT_INTEGRAL_GAIN = 0.01;

	/**
	 * The default derivative gain.
	 */
	public static final double DEFAULT_DERIVATIVE_GAIN = 0.1;

	/**
	 * The guard against having too much gain from the integral correction.
	 * This will act as the limit the amount of of correction the PID can
	 * actually provide.
	 */
	private double windupGuard;

	/**
	 * The constant to scale the proportional error to.
	 */
	private double proportionalGain;

	/**
	 * The constant to scale the integral (cumulative) error to.
	 */
	private double integralGain;

	/**
	 * The constant to scale the derivative (predicted) error to.
	 */
	private double derivativeGain;

	/**
	 * A variable to store the previous error calculated by the PID
	 */
	private double prevError;
	
	/**
	 * A variable to store the previous time that update was called
	 */
	private double lastTime;

	/**
	 * The cumulative integral (past) error.
	 */
	private double intError;

	/**
	 * The correcting value
	 */
	private double correction;

	/**
	 * The thread that the PID is running on.
	 *
	 * TODO: Check that you can keep making Threads when a processor runs out of cores to run them on
	 */
	private Thread updater;

	/**
	 * The device the PID acts on.
	 */
	private Correctable device;

	/**
	 * {@code PID} default constructor.
	 *
	 * @param device A {@code Correctable} device for the PID to act on.
	 *
	 * @return A fancy new PID specialized for your device!
	 *
	 * @see io.github.thunderbots.lightning.functionality.Correctable
	 */
	public PID(Correctable device) {
		this(device, PID.DEFAULT_WINDUP_GUARD, PID.DEFAULT_PROPORTIONAL_GAIN, PID.DEFAULT_INTEGRAL_GAIN, PID.DEFAULT_DERIVATIVE_GAIN);
	}

	/**
	 * {@code PID} default constructor.
	 *
	 * @param device A {@code Correctable} device for the PID to act on.
	 * @param windupGuard The integral cap
	 *
	 * @return A fancy new PID specialized for your device!
	 *
	 * @see io.github.thunderbots.lightning.functionality.Correctable
	 */
	public PID(Correctable device, double windup_guard) {
		this(device, windup_guard, PID.DEFAULT_PROPORTIONAL_GAIN, PID.DEFAULT_INTEGRAL_GAIN, PID.DEFAULT_DERIVATIVE_GAIN);
	}

	/**
	 * {@code PID} default constructor.
	 *
	 * @param device A {@code Correctable} device for the PID to act on.
	 * @param windupGuard The integral cap
	 * @param Kp The proportional gain
	 *
	 * @return A fancy new PID specialized for your device!
	 *
	 * @see io.github.thunderbots.lightning.functionality.Correctable
	 */
	public PID(Correctable device, double windup_guard, double Kp) {
		this(device, windup_guard, Kp, PID.DEFAULT_INTEGRAL_GAIN, PID.DEFAULT_DERIVATIVE_GAIN);
	}

	/**
	 * {@code PID} default constructor.
	 *
	 * @param device A {@code Correctable} device for the PID to act on.
	 * @param windupGuard The integral cap
	 * @param Kp The proportional gain
	 * @param Ki The integral gain
	 *
	 * @return A fancy new PID specialized for your device!
	 *
	 * @see io.github.thunderbots.lightning.functionality.Correctable
	 */
	public PID(Correctable device, double windup_guard, double Kp, double Ki) {
		this(device, windup_guard, Kp, Ki, PID.DEFAULT_DERIVATIVE_GAIN);
	}

	/**
	 * {@code PID} default constructor.
	 *
	 * @param device A {@code Correctable} device for the PID to act on.
	 * @param windupGuard The integral cap
	 * @param Kp The proportional gain
	 * @param Ki The integral gain
	 * @param Kd The derivative gain
	 *
	 * @return A fancy new PID specialized for your device!
	 *
	 * @see io.github.thunderbots.lightning.functionality.Correctable
	 */
	public PID(Correctable device, double windup_guard, double Kp, double Ki, double Kd) {
		this.device = device;
		this.reset(windup_guard, Kp, Ki, Kd);
		updater = new Thread(this);
		updater.start();
	}

	/**
	 * Resets the PID controller.
	 *
	 * @return True if success, otherwise false
	 */
	public boolean reset() {
		this.intError = 0.0;
		this.prevError = 0.0;
		return (this.intError == 0.0) && (this.prevError == 0.0);
	}

	/**
	 * Resets the PID controller with new parameters
	 *
	 * @param integral_cap The cap for the integral calculation
	 *
	 * @return True if success, otherwise false
	 */
	public boolean reset(double integral_cap) {
		this.setConstants(integral_cap, this.proportionalGain, this.integralGain, this.derivativeGain);
		return this.reset();
	}

	/**
	 * Resets the PID controller with new parameters
	 *
	 * @param integral_cap The cap for the integral calculation
	 * @param Kp The new proportional scaling factor
	 *
	 * @return True if success, otherwise false
	 */
	public boolean reset(double integral_cap, double Kp) {
		this.setConstants(integral_cap, Kp, this.integralGain, this.derivativeGain);
		return this.reset();
	}

	/**
	 * Resets the PID controller with new parameters
	 *
	 * @param integral_cap The cap for the integral calculation
	 * @param Kp The new proportional scaling factor
	 * @param Ki The new integral scaling factor
	 *
	 * @return True if success, otherwise false
	 */
	public boolean reset(double integral_cap, double Kp, double Ki) {
		this.setConstants(integral_cap, Kp, Ki, this.proportionalGain);
		return this.reset();
	}

	/**
	 * Resets the PID controller with new parameters
	 *
	 * @param integral_cap The cap for the integral calculation
	 * @param Kp The new proportional scaling factor
	 * @param Ki The new integral scaling factor
	 * @param Kd The new derivative scaling factor
	 *
	 * @return True if success, otherwise false
	 */
	public boolean reset(double integral_cap, double Kp, double Ki, double Kd) {
		this.setConstants(integral_cap, Kp, Ki, Kd);
		return this.reset();
	}

	/**
	 * Set the constants for the PID.
	 * Should only be called from constructor and reset functions
	 *
	 * @param integral_cap The cap for the integral calculation
	 * @param Kp The proportional gain
	 * @param Ki The integral gain
	 * @param Kd The derivative gain
	 *
	 */
	private void setConstants(double integral_cap, double Kp, double Ki, double Kd) {
		this.windupGuard = integral_cap;
		this.proportionalGain = Kp;
		this.integralGain = Ki;
		this.derivativeGain = Kd;
	}

	/**
	 * The thread that runs the actual PID calculations.
	 *
	 * Stores the PID correction value in this.correction,
	 * which is available by calling this.getCorrection()
	 */
	public void run() {
		try {
			while (true) {
				double delta_t = (double)System.currentTimeMillis() - this.lastTime;
				this.updateCorrection(this.device.get_error(), delta_t);
				this.lastTime = (double)System.currentTimeMillis();
				Thread.sleep(1);
			}
		}
		catch (Exception e) { //TODO: Implement this
			
		}
	}
	
	/**
	 * This is the actual PID algorithm
	 * 
	 * @param error The error in the device
	 * @param delta_t The change in time from the last check
	 */
	private synchronized void updateCorrection(double error, double delta_t) {
		double diff;
		double p_term;
		double i_term;
		double d_term;
		double correction;
		
		//integration with windup guarding
		this.intError += error*delta_t;
		if (this.intError < -(this.windupGuard)) {
			this.intError = -(this.windupGuard);
		} else if (this.intError > this.windupGuard) {
			this.intError = this.windupGuard;
		}
		
		//differentiation
		diff = ((error - this.prevError) / delta_t);
		
		//scaling
		p_term = (this.proportionalGain * error);
	    i_term = (this.integralGain     * this.intError);
	    d_term = (this.derivativeGain   * diff);
	    
	    correction = p_term + i_term + d_term;
	    
	    if (correction < Motor.MIN_POWER) {
	    	correction = Motor.MIN_POWER;
	    } else if (correction > Motor.MAX_POWER) {
	    	correction = Motor.MAX_POWER;
	    }
	    
	    this.prevError = error;
	    
	    this.correction = correction;
	}
	
	/**
	 * Get the wind-up guard (integral cap).
	 * 
	 * @return The wind-up guard (integral cap).
	 */
	public double getWindupGuard() {
		return this.windupGuard;
	}
	
	/**
	 * Get the device being corrected.
	 * 
	 * @return The device being corrected.
	 */
	public Correctable getDevice() {
		return this.device;
	}
	
	/**
	 * Get the proportional gain.
	 * 
	 * @return The current proportional gain.
	 */
	public double getProportionalGain() {
		return this.proportionalGain;
	}
	
	/**
	 * Get the integral gain.
	 * 
	 * @return The current integral gain.
	 */
	public double getIntegralGain() {
		return this.integralGain;
	}
	
	/**
	 * Get the derivative gain.
	 * 
	 * @return The current derivative gain.
	 */
	public double getDerivativeGain() {
		return this.derivativeGain;
	}

	/**
	 * The method to handle communication with the device.
	 * 
	 * @return The value (correction) to modify the device by.
	 */
	public synchronized double getCorrection() {
		return this.correction;
	}
	
	@Override
	public String toString() {
		return "PID for " + this.getDevice() +
				": Kp: " + this.getProportionalGain() +
				"; Ki: " + this.getIntegralGain() +
				"; Kd: " + this.getDerivativeGain() + ";";
	}
}
