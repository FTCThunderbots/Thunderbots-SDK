package io.github.thunderbots.lightning.control;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * {@code ButtonHandler} is an annotation that should be applied to all methods that handle
 * joystick button presses. It specifies the button that should be responded to, the joystick
 * that the button should come from, and if the method should be called on the press or release
 * of the button.
 *
 * @author Zach Ohara
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ButtonHandler {
	
	/**
	 * The {@code PressType} enumeration is used to specify if a method should respond to a
	 * button being pressed or released.
	 */
	public enum PressType {
		PRESS,
		RELEASE,
	}
	
	/**
	 * Gets the button that should be responded to.
	 *
	 * @return the button that should be responded to.
	 */
	JoystickButton button();
	
	/**
	 * Gets the joystick that the button should come from.
	 *
	 * @return the joystick that the button should come from.
	 */
	int joystick() default 1;
	
	/**
	 * Gets the type of press that the method should be called in response to.
	 *
	 * @return the type of press that the method should be called in response to.
	 */
	PressType type() default PressType.PRESS;

}
