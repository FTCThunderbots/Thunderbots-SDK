package io.github.thunderbots.sdk.opmode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import io.github.thunderbots.sdk.Robot;

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
		this.initializeOpMode();
		this.initializeRobot();
		this.waitForStart();
		this.main();
	}
	
	public void initializeOpMode() {
		Robot.initializeRobot(this.hardwareMap, this.telemetry, this.gamepad1, this.gamepad2);
	}

}
