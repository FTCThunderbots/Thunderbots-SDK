package io.github.thunderbots.lightning.drive;

/**
 * @author Zach Ohara
 */
public class TankDrive extends MecanumDrive {

	public TankDrive(DriveMotorSet wheels) {
		super(wheels);
	}

	public TankDrive(String[] wheelnames) {
		super(wheelnames);
	}

	@Override
	public boolean setMovement(double forward, double right, double clockwise) {
		return super.setMovement(forward, 0, clockwise);
	}

	@Override
	public boolean setMovement(double forward, double clockwise) {
		return super.setMovement(forward, clockwise);
	}

}
