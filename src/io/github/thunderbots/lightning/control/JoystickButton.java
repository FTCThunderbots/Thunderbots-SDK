package io.github.thunderbots.lightning.control;

/**
 * The {@code JoystickButton} enumeration contanis all buttons that can be pressed on the joysticks.
 *
 * @author Zach Ohara
 * @author Pranav Mathur
 */
public enum JoystickButton {
	
	A,
	B,
	X,
	Y,
	
	DPAD_UP,
	DPAD_DOWN,
	DPAD_LEFT,
	DPAD_RIGHT,
	
	LEFT_STICK,
	RIGHT_STICK,
	
	LEFT_BUMPER,
	RIGHT_BUMPER;
	
	@Override
	public String toString() {
		return this.name();
	}

}
