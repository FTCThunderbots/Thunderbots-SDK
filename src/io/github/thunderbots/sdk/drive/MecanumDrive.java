/* 
 * MecanumDrive.java
 * Copyright (C) 2015 Thunderbots-5604
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.github.thunderbots.sdk.drive;

import io.github.thunderbots.sdk.movement.Motor;
import io.github.thunderbots.sdk.utility.MathUtil;

public class MecanumDrive extends DriveSystem {

	private Motor frontLeftMotor;
	private Motor frontRightMotor;
	private Motor backLeftMotor;
	private Motor backRightMotor;

	// TODO: Add PID functionality
	// TODO: Add encoder functionality

	public static final double MOVE_POWER_SCALE = 1.0; // used for speed limits
	public static final double DRIVE_POWER_WEIGHT = 1.0;
	public static final double STRAFE_POWER_WEIGHT = 1.0;
	public static final double ROTATE_POWER_WEIGHT = 1.0;

	public static final int[] INPUT_RANGE = {0, 100};
	public static final int[] DRIVE_POWER_RANGE = {10, 100};
	public static final int[] STRAFE_POWER_RANGE = {10, 100};
	public static final int[] ROTATE_POWER_RANGE = {10, 100};

	/**
	 * Constructs a new mecanum drive system with all default settings
	 */
	public MecanumDrive() {
		super();
		this.frontLeftMotor = new Motor();
		this.frontRightMotor = new Motor();
		this.backLeftMotor = new Motor();
		this.backRightMotor = new Motor();
	}

	@Override
	public boolean setMovement(int forwardPower, int rightPower, int clockwisePower) {
		double forward = forwardPower;
		double right = rightPower;
		double clockwise = clockwisePower;

		forward = MathUtil.scaleToRange(forward, INPUT_RANGE, DRIVE_POWER_RANGE);
		right = MathUtil.scaleToRange(right, INPUT_RANGE, STRAFE_POWER_RANGE);
		clockwise = MathUtil.scaleToRange(clockwise, INPUT_RANGE, ROTATE_POWER_RANGE);

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

		if (max > Motor.MAX_POWER) {
			double scale = max / Motor.MAX_POWER;
			frontLeft /= scale;
			frontRight /= scale;
			backLeft /= scale;
			backRight /= scale;
		}

		frontLeftMotor.set((long) frontLeft);
		frontRightMotor.set((long) frontRight);
		backLeftMotor.set((long) backLeft);
		backRightMotor.set((long) backRight);

		return true;
	}

}
