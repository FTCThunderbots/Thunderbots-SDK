package io.github.thunderbots.lightning.hardware;

/**
 * @author Zach Ohara
 */
public class Servo {

	private RobotcoreServo baseServo;

	public static final double MIN_POSITION = com.qualcomm.robotcore.hardware.Servo.MIN_POSITION;
	public static final double MAX_POSITION = com.qualcomm.robotcore.hardware.Servo.MAX_POSITION;

	public Servo(com.qualcomm.robotcore.hardware.Servo baseServo) {
		this.baseServo = new RobotcoreServo(baseServo);
	}

	public double getPosition() {
		return this.baseServo.getPosition();
	}

	public void moveTo(double position) {
		this.baseServo.setPosition(position);
	}

	@Override
	public String toString() {
		return this.baseServo.toString();
	}

	private static class RobotcoreServo {

		private com.qualcomm.robotcore.hardware.Servo baseServo;

		public RobotcoreServo(com.qualcomm.robotcore.hardware.Servo base) {
			this.baseServo = base;
		}

		@Override
		public boolean equals(Object arg0) {
			return this.baseServo.equals(arg0);
		}

		public double getPosition() {
			return this.baseServo.getPosition();
		}

		@Override
		public int hashCode() {
			return this.baseServo.hashCode();
		}

		public void setPosition(double position) {
			this.baseServo.setPosition(position);
		}

		@Override
		public String toString() {
			return this.baseServo.toString();
		}

	}

}
