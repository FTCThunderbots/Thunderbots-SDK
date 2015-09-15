package io.github.thunderbots.lightning.opmode;

/**
 * @author Zach Ohara
 */
public class TCompoundOpMode extends LightningOpMode {

	private LightningOpMode[] opmodes;

	public TCompoundOpMode(LightningOpMode[] opmodes) {
		this.opmodes = opmodes;
	}

	@Override
	protected void initializeRobot() {
		for (LightningOpMode opmode : this.opmodes) {
			opmode.initializeRobot();
		}
	}

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
