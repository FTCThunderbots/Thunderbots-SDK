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

package io.github.thunderbots.lightning.control;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code ButtonHandler} is an annotation that should be applied to all methods that handle
 * joystick button presses. It specifies the button that should be responded to, the
 * joystick that the button should come from, and if the method should be called on the
 * press or release of the button.
 *
 * @author Zach Ohara
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
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
