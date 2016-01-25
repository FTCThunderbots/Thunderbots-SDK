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
import io.github.thunderbots.lightning.control.JoystickMonitor;
import io.github.thunderbots.lightning.hardware.CRServo;
import io.github.thunderbots.lightning.hardware.Motor;
import io.github.thunderbots.lightning.hardware.Servo;
import io.github.thunderbots.lightning.opmode.LightningOpMode;
import io.github.thunderbots.lightning.scheduler.TaskScheduler;
import io.github.thunderbots.lightning.utility.Telemetry;

import java.util.ArrayList;
import java.util.List;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.HardwareMap.DeviceMapping;

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
	 * The op mode to get hardware and objects from. All information that is accessible
	 * through {@code Lightning} is provided by this op mode.
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
	 * The master task scheduler that is used to execute all background tasks in the SDK
	 * and in client code of the SDK.
	 */
	private static TaskScheduler taskScheduler;

	/**
	 * The joystick monitor for joystick 1.
	 *
	 * @see io.github.thunderbots.lightning.control.JoystickMonitor
	 */
	private static JoystickMonitor monitor1;

	/**
	 * The joystick monitor for joystick 1.
	 *
	 * @see io.github.thunderbots.lightning.control.JoystickMonitor
	 */
	private static JoystickMonitor monitor2;

	/**
	 * {@code Lightning} should not be instantiable.
	 */
	private Lightning() {

	}

	/**
	 * Initializes the static members in {@code Lightning} from the given
	 * {@code LightningOpMode}.
	 *
	 * @param opmode the op mode to get information from.
	 * @see #opmode
	 */
	public static void initializeLightning(LightningOpMode opmode) {
		Lightning.opmode = opmode;
		Lightning.robotHardware = opmode.hardwareMap;
		Lightning.sensorMaps = Lightning.getSensorMaps(Lightning.robotHardware);
		Lightning.taskScheduler = new TaskScheduler();
		Lightning.monitor1 = new JoystickMonitor(1);
		Lightning.monitor2 = new JoystickMonitor(2);
		Telemetry.setTelemetry(opmode.telemetry);
	}

	/**
	 * Gets a reference to the main task scheduler.
	 *
	 * @return a reference to the main task scheduler.
	 * @see #taskScheduler
	 */
	public static TaskScheduler getTaskScheduler() {
		return Lightning.taskScheduler;
	}

	/**
	 * Gets a reference to the given joystick. Currently, only {@code joystick1} and
	 * {@code joystick2} are supported.
	 *
	 * @param joystick the ID of the joystick to return; can only be 1 or 2.
	 * @return the specified joystick.
	 * @throws IllegalArgumentException if the given joystick ID is not 1 or 2.
	 */
	public static Joystick getJoystick(int joystick) {
		switch (joystick) {
			case 1:
				return new Joystick(Lightning.opmode.gamepad1);
			case 2:
				return new Joystick(Lightning.opmode.gamepad2);
			default:
				throw new IllegalArgumentException();
		}
	}

	/**
	 * Gets a reference to the joystick monitor for the given joystick. Currently, only
	 * {@code joystick1} and {@code joystick2} are supported.
	 *
	 * @param joystick the ID of the joystick to get the monitor for; can only be 1 or 2.
	 * @return the joystick monitor for the given joystick.
	 * @see #monitor1
	 * @see #monitor2
	 */
	public static JoystickMonitor getJoystickMonitor(int joystick) {
		switch (joystick) {
			case 1:
				return Lightning.monitor1;
			case 2:
				return Lightning.monitor2;
			default:
				return null;
		}
	}

	/**
	 * Gets a reference to the hardware map supplied by
	 * {@code Lightning.opmode}. The map is supplied by the RobotCore SDK.
	 * 
	 * @return the map of the opmode with which Lightning was initialized.
	 */
	public static HardwareMap getRobotHardware() {
		return robotHardware;
	}

	/**
	 * Gets a reference to the motor with the given name. If there is no motor with the
	 * given name, but there is a servo with the given name, the servo is assumed to be a
	 * continuous-rotation servo, and a {@link io.github.thunderbots.lightning.hardware.CRServo
	 * CRServo} object representing that servo is returned.
	 *
	 * @param name the name of the motor.
	 * @return the motor with the given name.
	 * @see io.github.thunderbots.lightning.hardware.CRServo
	 */
	public static Motor getMotor(String name) {
		try {
			return new Motor(Lightning.robotHardware.dcMotor.get(name));
		} catch (IllegalArgumentException e) {
			// TODO: find out which specific type of exception we should expect here.
			return new CRServo(new Servo(Lightning.robotHardware.servo.get(name)));
		}
	}

	/**
	 * Gets a reference to the servo with the given name.
	 *
	 * @param name the name of the servo.
	 * @return the servo with the given name.
	 */
	public static Servo getServo(String name) {
		return new Servo(Lightning.robotHardware.servo.get(name));
	}

	/**
	 * Gets a reference to any sensor on the robot with the given name.
	 *
	 * @param <T> the type of the desired sensor.
	 * @param name the name of the sensor.
	 * @return the sensor with the given name.
	 * @throws IllegalArgumentException if no sensors exist with the given name.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getSensor(String name) {
		for (DeviceMapping<?> m : Lightning.sensorMaps) {
			if (m.entrySet().contains(name)) {
				return (T) m.get(name);
			}
		}
		throw new IllegalArgumentException();
	}

	/**
	 * Returns a list of {@code DeviceMapping}s in the given {@code HardwareMap} that could
	 * contain sensors.
	 * <p>
	 * This method essentially works by enumerating the known device mappings that could
	 * contain sensors. If more device mappings are added to FTC's built-in SDK, this
	 * method will need to be updated before any new sensors can be accessed through
	 * Lightning.
	 *
	 * @param map the hardware map to search for sensor maps in.
	 * @return a list of the device maps containing sensors.
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

}
