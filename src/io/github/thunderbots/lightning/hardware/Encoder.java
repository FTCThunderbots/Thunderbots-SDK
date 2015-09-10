package io.github.thunderbots.lightning.hardware;

/**
 * An {@code Encoder} represents a physical encoder that is attached to a specific motor.
 * 
 * @author Zach Ohara
 */
public class Encoder {

	/**
	 * The motor that this encoder is attached to.
	 */
	private Motor baseMotor;
	
	/**
	 * The value that should be considered 'zero' on the encoder.
	 */
	private int zeroPoint;
	
	/**
	 * The number of encoder ticks that measure exactly one full rotation of the motor.
	 */
	private double ticksPerRevolution;
	
	/**
	 * The number of encoder ticks that measure one inch on the circumference of the wheel.
	 */
	private double ticksPerInch;
	
	/**
	 * The default number of encoder ticks that measure exactly one full rotation of the motor.
	 */
	private static final double DEFAULT_TICKS_PER_REVOLUTION = 0d;
	
	/**
	 * The default number of encoder ticks that measure one inch on the circumference of the wheel.
	 */
	private static final double DEFAULT_TICKS_PER_INCH = 0d;

	/**
	 * Constructs a new encoder that is bound to the given motor.
	 *
	 * @param motor the motor to measure with this encoder.
	 */
	public Encoder(Motor motor) {
		this.baseMotor = motor;
		this.ticksPerRevolution = Encoder.DEFAULT_TICKS_PER_REVOLUTION;
		this.ticksPerInch = Encoder.DEFAULT_TICKS_PER_INCH;
		this.reset();
	}

	/**
	 * Resets this encoder. If {@link #getPosition()} is called immediately after this method,
	 * it will return zero.
	 */
	public void reset() {
		this.zeroPoint = this.baseMotor.getRawPosition();
	}

	/**
	 * Gets the current position of the encoder. More formally, this returns the difference between
	 * the current position and the position when the encoder was last reset.
	 *
	 * @return the current position of the encoder.
	 */
	public int getPosition() {
		return this.baseMotor.getRawPosition() - this.zeroPoint;
	}

	/**
	 * Gets the current position of the encoder, converted to revolutions of the wheel.
	 * 
	 * @return the current position of the encoder, in revolutions.
	 */
	public double getRevolutions() {
		return this.getPosition() / this.ticksPerRevolution;
	}

	/**
	 * Gets the current position of the encoder, converted to inches on the wheel circumfrence.
	 * 
	 * @return the current position of the encoder, in inches.
	 */
	public double getInches() {
		return this.getPosition() / this.ticksPerInch;
	}

	/**
	 * Sets the number of encoder ticks that measure exactly one full rotation of the motor.
	 *
	 * @param ticks the encoder ticks that measure exactly one full rotation of the motor.
	 * @see #ticksPerRevolution
	 */
	public void setTicksPerRevolution(double ticks) {
		this.ticksPerRevolution = ticks;
	}

	/**
	 * Sets the number of encoder ticks that measure one inch on the circumference of the wheel.
	 *
	 * @param ticks the encoder ticks that measure one inch on the circumference of the wheel.
	 * @see #ticksPerInch
	 */
	public void setTicksPerInch(double ticks) {
		this.ticksPerInch = ticks;
	}

	@Override
	public String toString() {
		return "Encoder[" + this.baseMotor + "]";
	}

}
