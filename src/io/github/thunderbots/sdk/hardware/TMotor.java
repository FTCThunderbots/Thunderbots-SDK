package io.github.thunderbots.sdk.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * @author Zach Ohara
 */
public class TMotor {

	private DcMotor basemotor;
	private TEncoder encoder;

	public static final double MAX_POWER = 1;
	public static final double REST_POWER = 0;
	public static final double MIN_POWER = -1;

	public TMotor(DcMotor basemotor) {
		this.basemotor = basemotor;
	}

	public TEncoder getEncoder() {
		return this.encoder;
	}

	public int getRawPosition() {
		return this.basemotor.getCurrentPosition();
	}

	public boolean isReversed() {
		return this.basemotor.getDirection() == DcMotor.Direction.REVERSE;
	}

	public void setReversed(boolean reversed) {
		if (reversed) {
			this.basemotor.setDirection(DcMotor.Direction.REVERSE);
		} else {
			this.basemotor.setDirection(DcMotor.Direction.FORWARD);
		}
	}

	public double getPower() {
		return this.basemotor.getPower();
	}

	public void setPower(double power) {
		this.basemotor.setPower(power);
	}

	@Override
	public String toString() {
		return this.basemotor.toString();
	}

}
