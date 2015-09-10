package io.github.thunderbots.lightning;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.robocol.Telemetry;

import io.github.thunderbots.lightning.control.Joystick;
import io.github.thunderbots.lightning.hardware.TMotor;
import io.github.thunderbots.lightning.hardware.TServo;
import io.github.thunderbots.lightning.scheduler.TaskScheduler;

/**
 * The {@code Lightning} class exposes methods for general interfacing with the hardware
 * on the physical robot. The static methods in the class can be used for things such as
 * accessing the joysticks, and getting hardware objects.
 * 
 * @author Zach Ohara
 */
public class Lightning {

	/**
	 * The {@code HardwareMap} that is used as a portal to all hardware on the robot.
	 * 
	 * @see com.qualcomm.robotcore.hardware.HardwareMap
	 */
	private static HardwareMap robotHardware;
	
	/**
	 * The telemetry link between the robot controller and the driver station.
	 * 
	 * @see com.qualcomm.robotcore.robocol.Telemetry
	 */
	private static Telemetry robotTelemetry;
	
	/**
	 * The joysticks connected to the driver station.
	 */
	private static Joystick gamepad1;
	
	/**
	 * The joysticks connected to the driver station.
	 */
	private static Joystick gamepad2;

	/**
	 * The master task scheduler that is used to execute all background tasks in the SDK
	 * and in client code of the SDK.
	 */
	private static TaskScheduler taskScheduler;

	static {
		Lightning.taskScheduler = new TaskScheduler();
	}
	
	public static void initializeRobot(HardwareMap hardware, Telemetry telemetry, Gamepad pad1,
			Gamepad pad2) {
		Lightning.robotHardware = hardware;
		Lightning.robotTelemetry = telemetry;
		Lightning.gamepad1 = new Joystick(pad1);
		Lightning.gamepad2 = new Joystick(pad2);
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
	 * Gets a reference to the primary gamepad.
	 * 
	 * This method is deprecated. Instead, please use {@link #getGamepad(int)} with an
	 * argument of 1.
	 * 
	 * @return a reference to the primary gamepad.
	 */
	@Deprecated
	public static Joystick getGamepad1() {
		return Lightning.gamepad1;
	}

	/**
	 * Gets a reference to the secondary gamepad.
	 * 
	 * This method is deprecated. Instead, please use {@link #getGamepad(int)} with an
	 * argument of 2.
	 * 
	 * @return a reference to the secondary gamepad.
	 */
	@Deprecated
	public static Joystick getGamepad2() {
		return Lightning.gamepad2;
	}
	
	public static Joystick getGamepad(int gamepad) {
		switch (gamepad) {
			case 1:
				return Lightning.getGamepad1();
			case 2:
				return Lightning.getGamepad2();
			default:
				return null;
		}
	}

	/**
	 * Gets a reference to the motor with the given name.
	 *
	 * @param name the name of the motor.
	 * @return the motor with the given name.
	 */
	public static TMotor getMotor(String name) {
		return new TMotor(Lightning.robotHardware.dcMotor.get(name));
	}

	/**
	 * Gets a reference to the motor with the given name.
	 *
	 * @param name the name of the motor.
	 * @return the motor with the given name.
	 */
	public static TServo getServo(String name) {
		return new TServo(Lightning.robotHardware.servo.get(name));
	}
	
	@Deprecated
	public static TouchSensor getTouchSensor(String name) {
		return Lightning.robotHardware.touchSensor.get(name);
	}

	/**
	 * Sends given data from the robot controller to the driver station. Any object can be sent,
	 * but the object's {@code toString()} method will be called and the string representation of
	 * the object is what will actually be sent. The data will be displayed in the bottom portion
	 * of the driver station's screen.
	 *
	 * @param tag a very short description of the data that is being sent. Ideally this string
	 * should be around 1-8 characters long, but an upper limit on characters is currently not
	 * known.
	 * @param data the object to be sent.
	 */
	public static void sendTelemetryData(String tag, Object data) {
		Lightning.robotTelemetry.addData(tag, data);
	}

	/**
	 * Sends given data from the robot controller to the driver station. Any object can be sent,
	 * but the object's {@code toString()} method will be called and the string representation of
	 * the object is what will actually be sent. The data will be displayed in the bottom portion
	 * of the driver station's screen.
	 * <br>
	 * If this method is used rather than {@link #sendTelemetryData(String, Object)}, the tag for
	 * the data will be an empty string.
	 *
	 * @param data the object to be sent.
	 * @see #sendTelemetryData(String, Object)
	 */
	public static void sendTelemetryData(Object data) {
		Lightning.sendTelemetryData("", data);
	}

}
