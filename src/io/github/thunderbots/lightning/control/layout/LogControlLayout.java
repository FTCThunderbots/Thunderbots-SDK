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
