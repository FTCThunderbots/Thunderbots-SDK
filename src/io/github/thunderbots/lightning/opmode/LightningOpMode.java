package io.github.thunderbots.lightning.opmode;

import io.github.thunderbots.lightning.Lightning;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * The {@code LightningOpMode} is a customized version of {@code LinearOpMode} that should
 * be the superclass for all op modes written using the SDK.
 * 
 * @author Zach Ohara
 */
public abstract class LightningOpMode extends LinearOpMode {

	/**
	 * Constructs a new LightningOpMode.
	 */
	public LightningOpMode() {
		if (this.isHidden()) {
			throw new UnsupportedOperationException();
			// This will be handled by the class loader
		}
	}

	/**
	 * Initializes the robot. This can be defined however it is seen fit by the specific
	 * overriding op mode.
	 */
	protected abstract void initializeRobot();

	/**
	 * Executes the 'main procedure' of the op mode. This method will run either until
	 * interrupted by the system, or until the method returns.
	 */
	protected abstract void main();

	@Override
	public final void runOpMode() throws InterruptedException {
		Lightning.initialize(this);
		this.initializeRobot();
		this.waitForStart();
		this.main();
	}

	/**
	 * Gets if this op mode should be hidden from the list in the app. If this method returns
	 * {@code true}, then an exception will be raised during the construction of this object,
	 * and the op mode will never show up in the drop-down menu of valid op modes.
	 *
	 * @return {@code true} if the op mode should be hidden from view; {@code false} otherwise.
	 */
	protected boolean isHidden() {
		return false;
	}

}
