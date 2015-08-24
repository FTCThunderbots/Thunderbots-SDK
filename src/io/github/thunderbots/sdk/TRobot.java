package io.github.thunderbots.sdk;

import com.qualcomm.robotcore.hardware.HardwareMap;

import io.github.thunderbots.sdk.hardware.TMotor;
import io.github.thunderbots.sdk.hardware.TServo;

/**
 * 
 *
 * @author Zach Ohara
 */
public class TRobot {
	
	private HardwareMap robotHardware;
	
	public TMotor getMotor(String name) {
		return new TMotor(robotHardware.dcMotor.get(name));
	}
	
	public TServo getServo(String name) {
		return new TServo(robotHardware.servo.get(name));
	}

}
