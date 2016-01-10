package io.github.thunderbots.lightning.control.layout;

import io.github.thunderbots.lightning.control.Joystick;

public class LogControlLayout implements ControlLayout {

	@Override
	public double getForwardPower(Joystick joy) {
		return 0;
	}

	@Override
	public double getClockwisePower(Joystick joy) {
		return 0;
	}

}
