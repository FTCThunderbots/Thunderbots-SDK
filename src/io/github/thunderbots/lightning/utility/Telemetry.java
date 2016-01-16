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
 * The {@code Telemetry} class acts as a gateway to all telemetric exchange between the
 * robot controller and the driver station. The static methods in this class can be used to
 * send data from the robot controller to the driver station for purposes such as debugging
 * or providing extra information about the state of the robot to the driver station.
 *
 * @author Pranav Mathur
 */
public final class Telemetry {

	/**
	 * The telemetry link between the robot controller and the driver station. It is used,
	 * among other things, to send debug information from the robot controller to the
	 * driver station.
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
	 *
	 * @param telemetry the base telemetry object.
	 * @see #robotTelemetry
	 */
	public static void setTelemetry(com.qualcomm.robotcore.robocol.Telemetry telemetry) {
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
	public static void sendData(String tag, Object data) {
		Telemetry.robotTelemetry.addData(tag, data);
	}

	/**
	 * Sends given data from the robot controller to the driver station. This method acts
	 * as a delegate to
	 * {@link io.github.thunderbots.lightning.utility.Telemetry#sendData(String, Object)},
	 * but replaces the tag argument with an empty string.
	 *
	 * @deprecated Since this method is a delegate that substitutes an empty string for the
	 * tag, all objects that are sent with this method will have an identical tag. The
	 * telemetry system assumes that multiple objects sent with the same tag are just
	 * different versions of the same information, and that only the most recent version of
	 * the object is the 'correct' version. If multiple objects are sent with the same tag,
	 * only the most recently sent object will be displayed, and all others will be
	 * discarded. This makes it very impractical to send data with an empty tag, because
	 * the telemetry system will discard potentially important data.
	 * @param data the object to be sent.
	 * @see io.github.thunderbots.lightning.utility.Telemetry#sendData(String, Object)
	 */
	@Deprecated
	public static void sendData(Object data) {
		Telemetry.sendData("", data);
	}

	/**
	 * Sends motor data from the robot controller to the driver station. Two entries are
	 * sent for the data, with the first being the power, and the second being the current
	 * encoder reading.
	 * <p>
	 * This method works by calling {@link #sendMotorPower(Motor)} followed by
	 * {@link #sendMotorPosition(Motor)}. Refer to the documentation for both of these
	 * methods for more information.
	 *
	 * @param m the motor to be sent.
	 * @see io.github.thunderbots.lightning.utility.Telemetry#sendMotorPower(Motor)
	 * @see io.github.thunderbots.lightning.utility.Telemetry#sendMotorPosition(Motor)
	 */
	public static void sendMotor(Motor m) {
		Telemetry.sendMotorPower(m);
		Telemetry.sendMotorPosition(m);
	}

	/**
	 * Sends motor data from the robot controller to the driver station. The name of the
	 * motor is used as the tag for the data, and the power of the motor is sent as the
	 * data.
	 *
	 * @param m the motor to be sent.
	 * @see io.github.thunderbots.lightning.utility.Telemetry#sendData(String, Object)
	 */
	public static void sendMotorPower(Motor m) {
		Telemetry.sendData(m.getName() + " pow", m.getPower());
	}

	/**
	 * Sends motor data from the robot controller to the driver station. The name of the
	 * motor is used as the tag for the data, and the reading of the encoder is sent as the
	 * data.
	 *
	 * @param m the motor to be sent.
	 * @see io.github.thunderbots.lightning.utility.Telemetry#sendData(String, Object)
	 */
	public static void sendMotorPosition(Motor m) {
		Telemetry.sendData(m.getName() + " pos", m.getEncoder().getPosition());
	}

	/**
	 * Sends servo data from the robot controller to the driver station. The name of the
	 * servo is used as the tag for the data, and the position of the servo is sent as the
	 * data.
	 *
	 * @param s the servo to be sent.
	 * @see io.github.thunderbots.lightning.utility.Telemetry#sendData(String, Object)
	 */
	public static void sendServoPosition(Servo s) {
		Telemetry.sendData(s.getName() + " pos", s.getPosition());
	}
	
	public static void sendThrowable(Throwable t) {
		Telemetry.sendData("Throwable: ", t.getMessage());
		t.printStackTrace();
	}

}
