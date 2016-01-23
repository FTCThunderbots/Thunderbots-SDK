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

import io.github.thunderbots.lightning.control.Joystick;
import io.github.thunderbots.lightning.hardware.Motor;

public class LogControlLayout extends DriveSpinControlLayout {
	
	private final static double JOYSTICK_LINEAR_WEIGHT = 2.5;

	@Override
	public double getForwardPower(Joystick joy) {
		return correctJoystickComposite(super.getForwardPower(joy));
	}

	@Override
	public double getClockwisePower(Joystick joy) {
		return correctJoystickComposite(super.getClockwisePower(joy));
	}
	
	/**
	 * Scales the input using an exponential equation.
	 * The exponential equation can be seen
	 * <a href="https://raw.githubusercontent.com/Thunderbots5604/2014-Code/master/Notebook/eqn%20raw.png">here</a>
	 * @param in
	 * @return
	 */
	private static double correctJoystickExponential(double in) {
		double correctValue = Math.abs(in);
		correctValue = Math.pow(Motor.MAX_POWER + 1, correctValue/Motor.MAX_POWER);
		correctValue -= 1;
		return Math.signum(in) * correctValue;
	}
	
	/**
	 * Scales the input using a composite exponential/linear equation.
	 * The weight of the linear equation in the final output is given by
	 * {@link #JOYSTICK_LINEAR_WEIGHT}.
	 * 
	 * @param in
	 * @return
	 */
	private static double correctJoystickComposite(double in) {
		double linear = in;
		double exponential = correctJoystickExponential(in);
		return (exponential + (JOYSTICK_LINEAR_WEIGHT * linear)) / (JOYSTICK_LINEAR_WEIGHT + 1);
	}

}
