package io.github.thunderbots.sdk.hardware;

/**
 * 
 *
 * @author Zach Ohara
 */
public class TEncoder {
	
	private TMotor baseMotor;
	private int zeroPoint;
	private double ticksPerRevolution;
	private double ticksPerInch;
	
	private static final double DEFAULT_TICKS_PER_REVOLUTION = 0d;
	private static final double DEFAULT_TICKS_PER_INCH = 0d;
	
	public TEncoder(TMotor motor) {
		this.baseMotor = motor;
		this.ticksPerRevolution = TEncoder.DEFAULT_TICKS_PER_REVOLUTION;
		this.ticksPerInch = TEncoder.DEFAULT_TICKS_PER_INCH;
		this.reset();
	}
	
	public void reset() {
		this.zeroPoint = this.baseMotor.getRawPosition();
	}
	
	public int getPosition() {
		return this.baseMotor.getRawPosition() - this.zeroPoint;
	}
	
	public double getRevolutions() {
		return this.getPosition() / this.ticksPerRevolution;
	}
	
	public double getInches() {
		return this.getPosition() / this.ticksPerInch;
	}
	
	public void setTicksPerRevolution(double ticks) {
		this.ticksPerRevolution = ticks;
	}
	
	public void setTicksPerInch(double ticks) {
		this.ticksPerInch = ticks;
	}
	
	public String toString() {
		return "TEncoder[" + this.baseMotor + "]";
	}

}
