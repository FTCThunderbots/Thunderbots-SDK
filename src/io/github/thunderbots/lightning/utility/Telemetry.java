package io.github.thunderbots.lightning.utility;

import io.github.thunderbots.lightning.hardware.Motor;
import io.github.thunderbots.lightning.hardware.Servo;

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
	 * Telemetry should not be instantiable.
	 */
	private Telemetry() {
		
	}
	
	public static void setTelemetry(com.qualcomm.robotcore.robocol.Telemetry telemetry) {
		if (Telemetry.robotTelemetry != null)
			throw new IllegalStateException("Telemetry can only be initialized once");
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
	 * Sends given data from the robot controller to the driver station. This method acts as
	 * a delegate to {@link io.github.thunderbots.lightning.utility.Telemetry#sendData(String, Object)}, but replaces the tag
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
	 * @see io.github.thunderbots.lightning.utility.Telemetry#sendData(String, Object)
	 */
	public static void sendData(Object data) {
		Telemetry.sendData("", data);
	}

	/**
	 * Sends motor data from the robot controller to the driver station. The name of the
	 * motor is used as the tag for the data, and the power of the motor is sent as the
	 * data.
	 *
	 * @param m the motor to be sent.
	 * @see io.github.thunderbots.lightning.utility.Telemetry#sendData(String, Object)
	 */
	public static void sendMotor(Motor m) {
		Telemetry.sendData(m.getName(), m.getPower());
	}

	/**
	 * Sends servo data from the robot controller to the driver station. The name of the
	 * servo is used as the tag for the data, and the position of the servo is sent as the
	 * data.
	 *
	 * @param s the servo to be sent.
	 * @see io.github.thunderbots.lightning.utility.Telemetry#sendData(String, Object)
	 */
	public static void sendServo(Servo s) {
		Telemetry.sendData(s.getName(), s.getPosition());
	}

}
