package io.github.thunderbots.sdk.opmode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * 
 *
 * @author Zach Ohara
 */
public abstract class TLinearOpMode extends LinearOpMode {
	
	protected abstract void initializeRobot();
	
	protected abstract void main();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void runOpMode() throws InterruptedException {
		this.initializeRobot();
		this.waitForStart();
		this.main();
	}
	
	public Gamepad gamepad1() {
		return this.gamepad1;
	}
	
	public Gamepad gamepad2() {
		return this.gamepad2;
	}
	
	public Gamepad getGamepad(int gamepad) {
		switch (gamepad) {
			case 1:
				return this.gamepad1;
			case 2:
				return this.gamepad2;
			default:
				return null;
		}
	}

}
