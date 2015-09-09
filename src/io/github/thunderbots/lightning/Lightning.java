package io.github.thunderbots.lightning;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.robocol.Telemetry;

import io.github.thunderbots.lightning.control.TGamepad;
import io.github.thunderbots.lightning.hardware.TMotor;
import io.github.thunderbots.lightning.hardware.TServo;
import io.github.thunderbots.lightning.scheduler.TaskScheduler;

/**
 * @author Zach Ohara
 */
public class Lightning {

	private static HardwareMap robotHardware;
	private static Telemetry robotTelemetry;
	private static TGamepad gamepad1;
	private static TGamepad gamepad2;

	private static TaskScheduler taskScheduler;

	static {
		Lightning.taskScheduler = new TaskScheduler();
	}

	public static void initializeRobot(HardwareMap hardware, Telemetry telemetry, Gamepad pad1,
			Gamepad pad2) {
		Lightning.robotHardware = hardware;
		Lightning.robotTelemetry = telemetry;
		Lightning.gamepad1 = new TGamepad(pad1);
		Lightning.gamepad2 = new TGamepad(pad2);
	}

	public static TaskScheduler getTaskScheduler() {
		return Lightning.taskScheduler;
	}

	public static TGamepad getGamepad1() {
		return Lightning.gamepad1;
	}

	public static TGamepad getGamepad2() {
		return Lightning.gamepad2;
	}

	public static TGamepad getGamepad(int gamepad) {
		switch (gamepad) {
			case 1:
				return Lightning.getGamepad1();
			case 2:
				return Lightning.getGamepad2();
			default:
				return null;
		}
	}

	public static TMotor getMotor(String name) {
		return new TMotor(Lightning.robotHardware.dcMotor.get(name));
	}

	public static TServo getServo(String name) {
		return new TServo(Lightning.robotHardware.servo.get(name));
	}

	@Deprecated
	public static TouchSensor getTouchSensor(String name) {
		return Lightning.robotHardware.touchSensor.get(name);
	}

	public static void sendTelemetryData(String tag, Object data) {
		Lightning.robotTelemetry.addData(tag, data);
	}

}
