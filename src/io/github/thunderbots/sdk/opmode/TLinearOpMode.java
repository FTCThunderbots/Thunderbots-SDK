package io.github.thunderbots.sdk.opmode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
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
		new OpModeVariableMonitor(this).run();
		this.initializeRobot();
		this.waitForStart();
		this.main();
	}

}
