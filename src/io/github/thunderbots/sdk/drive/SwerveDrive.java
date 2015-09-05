package io.github.thunderbots.sdk.drive;

/**
 * @author Zach Ohara
 */
public class SwerveDrive extends DriveSystem {

	// TODO: implement a swerve drive

	public SwerveDrive(DriveMotorSet wheels) {
		super(wheels);
	}

	public SwerveDrive(String[] wheelnames) {
		super(wheelnames);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean setMovement(double forward, double clockwise) {
		throw new UnsupportedOperationException();
	}

}
