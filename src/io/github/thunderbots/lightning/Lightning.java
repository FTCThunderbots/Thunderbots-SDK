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

package io.github.thunderbots.lightning;

import io.github.thunderbots.lightning.control.Joystick;
import io.github.thunderbots.lightning.hardware.CRServo;
import io.github.thunderbots.lightning.hardware.Motor;
import io.github.thunderbots.lightning.hardware.Servo;
import io.github.thunderbots.lightning.opmode.LightningOpMode;
import io.github.thunderbots.lightning.scheduler.TaskScheduler;

import java.util.ArrayList;
import java.util.List;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.HardwareMap.DeviceMapping;
import com.qualcomm.robotcore.robocol.Telemetry;

/**
 * The {@code Lightning} class exposes methods for general interfacing with the hardware on
 * the physical robot. The static methods in the class can be used for things such as
 * accessing the joysticks, and getting hardware objects.
 *
 * @author Zach Ohara
 * @author Pranav Mathur
 */
public final class Lightning {

	/**
	 * The op mode to get joystick information from.
	 */
	private static LightningOpMode opmode;

	/**
	 * The {@code HardwareMap} that is used as a portal to all hardware on the robot.
	 *
	 * @see com.qualcomm.robotcore.hardware.HardwareMap
	 */
	private static HardwareMap robotHardware;

	/**
	 * A list of all the {@code DeviceMapping}s exposed by the hardware map that could
	 * point to sensors.
	 *
	 * @see #robotHardware
	 */
	private static List<DeviceMapping<?>> sensorMaps;

	/**
	 * The telemetry link between the robot controller and the driver station.
	 *
	 * @see com.qualcomm.robotcore.robocol.Telemetry
	 */
	private static Telemetry robotTelemetry;

	/**
	 * The master task scheduler that is used to execute all background tasks in the SDK
	 * and in client code of the SDK.
	 */
	private static TaskScheduler taskScheduler;

	static {
		Lightning.taskScheduler = new TaskScheduler();
	}

	private Lightning() {

	}

	/**
	 * Initializes the static information in {@code Lightning} from the given
	 * {@code LightningOpMode}.
	 *
	 * @param opmode the op mode to initialize this robot from.
	 */
	public static void initialize(LightningOpMode opmode) {
		Lightning.opmode = opmode;
		Lightning.robotHardware = opmode.hardwareMap;
		Lightning.robotTelemetry = opmode.telemetry;
		Lightning.sensorMaps = Lightning.getSensorMaps(Lightning.robotHardware);
	}

	/**
	 * Gets a reference to the master task scheduler.
	 *
	 * @return a reference to the master task scheduler.
	 * @see #taskScheduler
	 */
	public static TaskScheduler getTaskScheduler() {
		return Lightning.taskScheduler;
	}

	/**
	 * Gets a reference to the given joystick. Currently, only {@code gamepad1} and
	 * {@code gamepad2} are supported.
	 *
	 * @param gamepad the joystick to return; can only be 1 or 2.
	 * @return the specified joystick.
	 */
	public static Joystick getJoystick(int gamepad) {
		switch (gamepad) {
			case 1:
				return new Joystick(Lightning.opmode.gamepad1);
			case 2:
				return new Joystick(Lightning.opmode.gamepad2);
			default:
				return null;
		}
	}

	/**
	 * Gets a reference to the motor with the given name. If there is no motor with the
	 * given name, but there is a servo with the given name, the servo is assumed to be a
	 * continuous-rotation servo, and a {@link io.github.thunderbots.hardware.CRServo
	 * CRServo} object representing that servo is returned.
	 *
	 * @param name the name of the motor.
	 * @return the motor with the given name.
	 */
	public static Motor getMotor(String name) {
		try {
			return new Motor(Lightning.robotHardware.dcMotor.get(name));
		} catch (Exception e) {
			// TODO: find out which specific type of exception we should expect here.
			return new CRServo(new Servo(Lightning.robotHardware.servo.get(name)));
		}
	}

	/**
	 * Gets a reference to the motor with the given name.
	 *
	 * @param name the name of the motor.
	 * @return the motor with the given name.
	 */
	public static Servo getServo(String name) {
		return new Servo(Lightning.robotHardware.servo.get(name));
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
		Lightning.robotTelemetry.addData(tag, data);
	}

	/**
	 * Sends given data from the robot controller to the driver station. Any object can be
	 * sent, but the object's {@code toString()} method will be called and the string
	 * representation of the object is what will actually be sent. The data will be
	 * displayed in the bottom portion of the driver station's screen. <br>
	 * If this method is used rather than {@link #sendTelemetryData(String, Object)}, the
	 * tag for the data will be an empty string.
	 *
	 * @param data the object to be sent.
	 * @see #sendTelemetryData(String, Object)
	 */
	public static void sendTelemetryData(Object data) {
		Lightning.sendTelemetryData("", data);
	}

	/**
	 * Sends motor data from the robot controller to the driver station
	 *
	 * @param m the motor to be sent
	 * @see #sendTelemetryData(String, Object)
	 */
	public static void sendTelemetryData(Motor m) {
		Lightning.sendTelemetryData(m.getName() + ": ", m.getPower());
	}

	/**
	 * Sends servo data from the robot controller to the driver station
	 *
	 * @param s the servo to be sent
	 * @see #sendTelemetryData(String, Object)
	 */
	public static void sendTelemetryData(Servo s) {
		Lightning.sendTelemetryData(s.getName() + ": ", s.getPosition());
	}

	/**
	 * Given a {@code HardwareMap}, return a list of {@code DeviceMapping}s that could lead
	 * to sensors.
	 *
	 * @param map the hardware map to search for maps in.
	 * @return a list of the sensor maps.
	 */
	private static List<DeviceMapping<?>> getSensorMaps(HardwareMap map) {
		List<DeviceMapping<?>> sensorMaps = new ArrayList<DeviceMapping<?>>();
		sensorMaps.add(map.accelerationSensor);
		sensorMaps.add(map.analogInput);
		sensorMaps.add(map.analogOutput);
		sensorMaps.add(map.colorSensor);
		sensorMaps.add(map.compassSensor);
		sensorMaps.add(map.digitalChannel);
		sensorMaps.add(map.gyroSensor);
		sensorMaps.add(map.i2cDevice);
		sensorMaps.add(map.irSeekerSensor);
		sensorMaps.add(map.lightSensor);
		sensorMaps.add(map.opticalDistanceSensor);
		sensorMaps.add(map.touchSensor);
		sensorMaps.add(map.ultrasonicSensor);
		sensorMaps.add(map.voltageSensor);
		return sensorMaps;
	}

	/**
	 * Gets a reference to any sensor on the robot with the given name.
	 *
	 * @param name the name of the sensor.
	 * @return the sensor with the given name.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getSensor(String name) {
		for (DeviceMapping<?> m : Lightning.sensorMaps) {
			if (m.entrySet().contains(name)) {
				return (T) m.get(name);
			}
		}
		return null;
	}

}
