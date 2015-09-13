package io.github.thunderbots.lightning.opmode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * @author Zach Ohara
 */
public abstract class TLinearOpMode extends LinearOpMode {

	public TLinearOpMode() {
		if (this.isHidden()) {
			throw new UnsupportedOperationException();
			// This will be handled by the class loader
		}
	}

	protected abstract void initializeRobot();

	protected abstract void main();

	@Override
	public void runOpMode() throws InterruptedException {
		new OpModeVariableMonitor(this).run();
		this.initializeRobot();
		this.waitForStart();
		this.main();
	}

	protected boolean isHidden() {
		return false;
	}

}
