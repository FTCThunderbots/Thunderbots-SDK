package io.github.thunderbots.sdk.drive;

import io.github.thunderbots.sdk.hardware.TMotor;

/**
 * 
 *
 * @author Zach Ohara
 */
public class MecanumDrive extends DriveSystem {

	public MecanumDrive(DriveMotorSet wheels) {
		super(wheels);
	}
	
	public MecanumDrive(String[] wheelnames) {
		super(wheelnames);
	}

	public static final double MOVE_POWER_SCALE = 1.0; // used for speed limits
	public static final double DRIVE_POWER_WEIGHT = 1.0;
	public static final double STRAFE_POWER_WEIGHT = 1.0;
	public static final double ROTATE_POWER_WEIGHT = 1.0;

	// This code is on standby. We don't know if we need it yet.
//	public static final double[] INPUT_RANGE = {0, 1};
//	public static final double[] DRIVE_POWER_RANGE = {0, 1};
//	public static final double[] STRAFE_POWER_RANGE = {0, 1};
//	public static final double[] ROTATE_POWER_RANGE = {0, 1};

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
		return this.traverse(right, power)
				&& this.waitAndStop(seconds);
	}
	
	public boolean setMovement(double forward, double right, double clockwise) {

		// This code is on standby
//		forward = MathUtil.scaleToRange(forward, INPUT_RANGE, DRIVE_POWER_RANGE);
//		right = MathUtil.scaleToRange(right, INPUT_RANGE, STRAFE_POWER_RANGE);
//		clockwise = MathUtil.scaleToRange(clockwise, INPUT_RANGE, ROTATE_POWER_RANGE);

		forward *= MOVE_POWER_SCALE * DRIVE_POWER_WEIGHT;
		right *= MOVE_POWER_SCALE * STRAFE_POWER_WEIGHT;
		clockwise *= MOVE_POWER_SCALE * ROTATE_POWER_WEIGHT;

		double frontLeft = forward + right + clockwise;
		double frontRight = -forward + right + clockwise;
		double backLeft = forward - right + clockwise;
		double backRight = -forward - right + clockwise;

		double max = Math.max(
				Math.max(Math.abs(frontLeft), Math.abs(frontRight)),
				Math.max(Math.abs(backLeft), Math.abs(backRight)));

		if (max > TMotor.MAX_POWER) {
			double scale = max / TMotor.MAX_POWER;
			frontLeft /= scale;
			frontRight /= scale;
			backLeft /= scale;
			backRight /= scale;
		}

		this.getWheelSet().setMotorPowers(new double[]
				{frontLeft, frontRight, backLeft, backRight});

		return true;
	}

}
