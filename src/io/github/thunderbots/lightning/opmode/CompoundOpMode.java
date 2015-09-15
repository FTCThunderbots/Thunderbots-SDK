package io.github.thunderbots.lightning.opmode;

/**
 * A {@code CompoundOpMode} is an op mode that includes the functionality of several
 * different op modes.
 * 
 * @author Zach Ohara
 */
public class CompoundOpMode extends LightningOpMode {

	/**
	 * The list of op modes that this compound op mode combines the functionality of.
	 */
	private LightningOpMode[] opmodes;

	/**
	 * Constructs a new compound op mode with the given list of op modes.
	 *
	 * @param opmodes the list of op modes to be combined.
	 * @see #opmodes
	 */
	public CompoundOpMode(LightningOpMode[] opmodes) {
		this.opmodes = opmodes;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void initializeRobot() {
		for (LightningOpMode opmode : this.opmodes) {
			opmode.initializeRobot();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void main() {
		for (LightningOpMode opmode : this.opmodes) {
			opmode.start();
		}
		while (this.opModeIsActive()) {
			for (LightningOpMode opmode : this.opmodes) {
				opmode.loop();
			}
		}
		for (LightningOpMode opmode : this.opmodes) {
			opmode.stop();
		}
	}

}
