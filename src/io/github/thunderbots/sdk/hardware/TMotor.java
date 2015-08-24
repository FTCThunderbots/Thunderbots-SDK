package io.github.thunderbots.sdk.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * 
 *
 * @author Zach Ohara
 */
public class TMotor {
	
	private DcMotor basemotor;
	private TEncoder encoder;
	
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
			basemotor.setDirection(DcMotor.Direction.REVERSE);
		} else {
			basemotor.setDirection(DcMotor.Direction.FORWARD);
		}
	}
	
	public double getPower() {
		return basemotor.getPower();
	}
	
	public void setPower(double power) {
		basemotor.setPower(power);
	}
	
	public String toString() {
		return basemotor.toString();
	}

}
