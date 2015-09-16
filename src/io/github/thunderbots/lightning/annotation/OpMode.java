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

package io.github.thunderbots.lightning.annotation;

import java.lang.annotation.Documented;

/**
 * The {@code OpMode} annotation should be added to all op modes that appear in the op mode
 * list. It details information about the type of op mode, such as "teleop" or
 * "autonomous", or anything else that the client class may define. This information is not
 * currently used. It also includes the name of the op mode, and this name will eventually
 * be used to present the op mode in the list.
 *
 * @author Pranav Mathur
 */
@Documented
public @interface OpMode {

	/**
	 * Gets the 'type' of the op mode. This can be "teleop" or "autonomous", or even any
	 * custom category defined by the client code. This is currently not used by the
	 * backend in any way, but a use for this may be implemented later.
	 *
	 * @return the type of the op mode.
	 */
	String type();

	/**
	 * Gets the name of the op mode. This is not necessarily just the name of the class
	 * that defines the op mode. Ideally, the name of the op mode is the name that the op
	 * mode should be listed as in a drop-down list of op modes.
	 *
	 * @return the name of the op mode.
	 */
	String name();

}
