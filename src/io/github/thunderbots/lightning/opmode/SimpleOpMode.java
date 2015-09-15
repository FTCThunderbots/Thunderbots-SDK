package io.github.thunderbots.lightning.opmode;

import io.github.thunderbots.lightning.drive.DriveSystem;
import io.github.thunderbots.lightning.drive.TankDrive;

/**
 * @author Zach Ohara
 */
public abstract class SimpleOpMode extends LightningOpMode {

	private DriveSystem drive;

	/**
	 * Gets an array of Strings representing the names of the motors used for driving.
	 * <p>
	 * If the robot has four motors, this array should be in the format of:
	 *
	 * <pre>
	 *  [front left, front right, back left, back right]
	 * </pre>
	 *
	 * If the robot has only two motors, this array should be in the format of:
	 *
	 * <pre>
	 *  [left, right]
	 * </pre>
	 *
	 * @return the names of the driving motors.
	 */
	protected abstract String[] getDriveMotorNames();

	/**
	 * Constructs a DriveSystem that the robot should use. TankDrive is assumed by default,
	 * but this can be changed on an individual basis by overriding this method.
	 *
	 * @return
	 */
	protected DriveSystem createDriveSystem() {
		return new TankDrive(this.getDriveMotorNames());
	}

	/**
	 * Gets a reference to the {@code DriveSystem} being used to control the robot.
	 *
	 * @return
	 */
	protected DriveSystem getDrive() {
		return this.drive;
	}

	@Override
	protected void initializeRobot() {
		this.drive = this.createDriveSystem();
	}

}
