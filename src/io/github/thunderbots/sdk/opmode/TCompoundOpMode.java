package io.github.thunderbots.sdk.opmode;

/**
 * 
 *
 * @author Zach Ohara
 */
public class TCompoundOpMode extends TLinearOpMode {
	
	private TLinearOpMode[] opmodes;
	
	public TCompoundOpMode(TLinearOpMode[] opmodes) {
		this.opmodes = opmodes;
	}

	@Override
	protected void initializeRobot() {
		for (TLinearOpMode opmode : this.opmodes) {
			opmode.initializeRobot();
		}
	}

	@Override
	protected void main() {
		for (TLinearOpMode opmode : this.opmodes) {
			opmode.start();
		}
		while (this.opModeIsActive()) {
			for (TLinearOpMode opmode : this.opmodes) {
				opmode.loop();
			}
		}
		for (TLinearOpMode opmode : this.opmodes) {
			opmode.stop();
		}
	}

}
