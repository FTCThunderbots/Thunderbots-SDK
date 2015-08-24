package io.github.thunderbots.sdk.hardware;

import com.qualcomm.robotcore.hardware.Servo;

/**
 * 
 *
 * @author Zach Ohara
 */
public class TServo {
	
	private Servo baseServo;
	
	public static final double MIN_POSITION = Servo.MIN_POSITION;
	public static final double MAX_POSITION = Servo.MAX_POSITION;
	
	public TServo(Servo baseServo) {
		this.baseServo = baseServo;
	}
	
	public double getPosition() {
		return this.baseServo.getPosition();
	}
	
	public void moveTo(double position) {
		this.baseServo.setPosition(position);
	}
	
	public String toString() {
		return baseServo.toString();
	}

}
