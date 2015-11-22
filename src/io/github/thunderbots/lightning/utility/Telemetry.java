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

package io.github.thunderbots.lightning.utility;

import io.github.thunderbots.lightning.hardware.Motor;
import io.github.thunderbots.lightning.hardware.Servo;

/**
 * The {@code Telemetry} class acts as a gateway to all telemetric exchange between the robot
 * controller and the driver station. The static methods in this class can be used to send
 * data from the robot controller to the driver station for purposes such as debugging or
 * providing extra information about the state of the robot to the driver station.
 * 
 * @author Pranav Mathur
 */
public final class Telemetry {
	
	/**
	 * The telemetry link between the robot controller and the driver station. It is used,
	 * among other things, to send debug information from the robot controller to the driver
	 * station.
	 *
	 * @see com.qualcomm.robotcore.robocol.Telemetry
	 */
	private static com.qualcomm.robotcore.robocol.Telemetry robotTelemetry;
	
	/**
	 * {@code Telemetry} should not be instantiable.
	 */
	private Telemetry() {
		
	}
	
	/**
	 * Sets the telemetry base-object to the given object.
	 * <p>
	 * For security and encapsulation purposes, this method can only be called once, during
	 * the setup period of any op mode. It is presumed that the object representing the
	 * telemetric connection will not change over the duration of an op mode.
	 *
	 * @param telemetry the base telemetry object.
	 * @throws IllegalStateException This method can only be called once, and subsequent calls
	 * will result in an exception.
	 * @see #robotTelemetry
	 */
	public static void setTelemetry(com.qualcomm.robotcore.robocol.Telemetry telemetry) {
		if (Telemetry.robotTelemetry != null) {
			throw new IllegalStateException();
		}
		Telemetry.robotTelemetry = telemetry;
	}

	/**
	 * Sends given data from the robot controller to the driver station. Any object can be
	 * sent, but the object's {@code toString()} method will be called and the string
	 * representation of the object is what will actually be sent. The data will be
	 * displayed in the bottom portion of the driver station's screen.
	 *
	 * @param tag a very short description of the data that is being sent. Ideally this
	 * string should be around 1-8 characters long, but an upper limit on characters is
	 * currently not known.
	 * @param data the object to be sent.
	 */
	public static void sendTelemetryData(String tag, Object data) {
		Telemetry.robotTelemetry.addData(tag, data);
	}

	/**
	 * Sends given data from the robot controller to the driver station. This method acts as
	 * a delegate to {@link io.github.thunderbots.lightning.utility.Telemetry#sendTelemetryData(String, Object)}, but replaces the tag
	 * argument with an empty string.
	 * 
	 * @deprecated
	 * Since this method is a delegate that substitutes an empty string for the tag, all
	 * objects that are sent with this method will have an identical tag. The telemetry
	 * system assumes that multiple objects sent with the same tag are just different
	 * versions of the same information, and that only the most recent version of the
	 * object is the 'correct' version. If multiple objects are sent with the same tag,
	 * only the most recently sent object will be displayed, and all others will be
	 * discarded. This makes it very impractical to send data with an empty tag, because
	 * the telemetry system will discard potentially important data.
	 *
	 * @param data the object to be sent.
	 * @see io.github.thunderbots.lightning.utility.Telemetry#sendTelemetryData(String, Object)
	 */
	public static void sendTelemetryData(Object data) {
		Telemetry.sendTelemetryData("", data);
	}

	/**
	 * Sends motor data from the robot controller to the driver station. The name of the
	 * motor is used as the tag for the data, and the power of the motor is sent as the
	 * data.
	 *
	 * @param m the motor to be sent.
	 * @see io.github.thunderbots.lightning.utility.Telemetry#sendTelemetryData(String, Object)
	 */
	public static void sendTelemetryData(Motor m) {
		Telemetry.sendTelemetryData(m.getName(), m.getPower());
	}

	/**
	 * Sends servo data from the robot controller to the driver station. The name of the
	 * servo is used as the tag for the data, and the position of the servo is sent as the
	 * data.
	 *
	 * @param s the servo to be sent.
	 * @see io.github.thunderbots.lightning.utility.Telemetry#sendTelemetryData(String, Object)
	 */
	public static void sendTelemetryData(Servo s) {
		Telemetry.sendTelemetryData(s.getName(), s.getPosition());
	}

}
