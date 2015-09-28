package io.github.thunderbots.lightning.control;

/**
 * The {@code JoystickButton} enumeration contanis all buttons that can be pressed on the joysticks.
 * It also contains controls on the joystick which are non-binary, but that can be treated as
 * buttons. For example, the position of the thumb sticks are given as a decimal value between -1
 * and 1, with 0 being the neutral position. The thumb stick is considered 'pressed' if its value
 * is either -1 or 1.
 *
 * @author Zach Ohara
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
	LEFT_TRIGGER,
	
	RIGHT_BUMPER,
	RIGHT_TRIGGER,
	
	LEFT_X_NEG,
	LEFT_X_POS,
	LEFT_Y_NEG,
	LEFT_Y_POS,
	
	RIGHT_X_NEG,
	RIGHT_X_POS,
	RIGHT_Y_NEG,
	RIGHT_Y_POS,

}
