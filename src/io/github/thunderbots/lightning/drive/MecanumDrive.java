package io.github.thunderbots.lightning.drive;

import io.github.thunderbots.lightning.hardware.Motor;

/**
 * A {@code MecanumDrive} is a {@code DriveSystem} that represents a system using mecanum
 * wheels.
 *
 * @author Zach Ohara
 */
public class MecanumDrive extends DriveSystem {

	/**
	 * Constructs a new {@code MecanumDrive} with the given {@code DriveMotorSet} as a
	 * base.
	 *
	 * @param wheels the {@code DriveMotorSet} of this drive system.
	 * @see DriveSystem#DriveSystem(DriveMotorSet)
	 */
	public MecanumDrive(DriveMotorSet wheels) {
		super(wheels);
	}

	/**
	 * Constructs a enw {@code MecanumDrive} that uses the motors with the given names.
	 *
	 * @param motornames the names of the motors to use with this drive system.
	 * @see DriveSystem#DriveSystem(String[])
	 */
	public MecanumDrive(String[] wheelnames) {
		super(wheelnames);
	}

	/**
	 * The multiplier for all motor powers. Decreasing this number can be used to impose
	 * speed limits. Increasing this number will yield strange results, so please don't
	 * increase this number beyond 1.
	 */
	public static final double MOVE_POWER_SCALE = 1.0;

	/**
	 * The relative weight of the forward vector over other vectors. Increasing this number
	 * will make the forward/backward movement more responsive, but at the expense of
	 * making the other vectors less responsive.
	 */
	public static final double DRIVE_POWER_WEIGHT = 1.0;

	/**
	 * The relative weight of the right vector over other vectors. Increasing this number
	 * will make the left/right movement more responsive, but at the expense of making the
	 * other vectors less responsive.
	 */
	public static final double STRAFE_POWER_WEIGHT = 1.0;

	/**
	 * The relative weight of the clockwise vector over other vectors. Increasing this
	 * number will make spinning responsive, but at the expense of making the other vectors
	 * less responsive.
	 */
	public static final double ROTATE_POWER_WEIGHT = 1.0;

	// This code is on standby. We don't know if we need it yet.
	// public static final double[] INPUT_RANGE = {0, 1};
	// public static final double[] DRIVE_POWER_RANGE = {0, 1};
	// public static final double[] STRAFE_POWER_RANGE = {0, 1};
	// public static final double[] ROTATE_POWER_RANGE = {0, 1};

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean setMovement(double forward, double clockwise) {
		return this.setMovement(forward, 0, clockwise);
	}

	public boolean strafe(int power) {
		return this.setMovement(0, power, 0);
	}

	public boolean traverse(boolean right, int power) {
		int directionMultiplier = right ? 1 : -1;
		return this.setMovement(power, Math.abs(power) * directionMultiplier, 0);
	}

	public boolean strafeSeconds(int power, float seconds) {
		return this.strafe(power) && this.waitAndStop(seconds);
	}

	public boolean traverseSeconds(boolean right, int power, float seconds) {
		return this.traverse(right, power) && this.waitAndStop(seconds);
	}

	/**
	 * Sets the power of the motors on the robot so that the robot moves as described by
	 * the three vectors.
	 *
	 * @param forward the forward-driving vector; between -1 and 1.
	 * @param right the right-strafing vector; between -1 and 1.
	 * @param clockwise the clockwise-spinning vector; between -1 and 1.
	 * @return
	 */
	public boolean setMovement(double forward, double right, double clockwise) {

		// This code is on standby
		// forward = MathUtil.scaleToRange(forward, INPUT_RANGE, DRIVE_POWER_RANGE);
		// right = MathUtil.scaleToRange(right, INPUT_RANGE, STRAFE_POWER_RANGE);
		// clockwise = MathUtil.scaleToRange(clockwise, INPUT_RANGE, ROTATE_POWER_RANGE);

		forward *= MecanumDrive.MOVE_POWER_SCALE * MecanumDrive.DRIVE_POWER_WEIGHT;
		right *= MecanumDrive.MOVE_POWER_SCALE * MecanumDrive.STRAFE_POWER_WEIGHT;
		clockwise *= MecanumDrive.MOVE_POWER_SCALE * MecanumDrive.ROTATE_POWER_WEIGHT;

		double frontLeft = forward + right + clockwise;
		double frontRight = -forward + right + clockwise;
		double backLeft = forward - right + clockwise;
		double backRight = -forward - right + clockwise;

		double max = Math.max(Math.max(Math.abs(frontLeft), Math.abs(frontRight)),
				Math.max(Math.abs(backLeft), Math.abs(backRight)));

		if (max > Motor.MAX_POWER) {
			double scale = max / Motor.MAX_POWER;
			frontLeft /= scale;
			frontRight /= scale;
			backLeft /= scale;
			backRight /= scale;
		}

		this.getWheelSet().setMotorPowers(new double[] {frontLeft, frontRight, backLeft, backRight});

		return true;
	}

}
