package io.github.thunderbots.sdk.drive;

/**
 * 
 *
 * @author Zach Ohara
 */
public class TankDrive extends MecanumDrive {
	
	public TankDrive(DriveMotorSet wheels) {
		super(wheels);
	}

	@Override
	public boolean setMovement(double forward, double right, double clockwise) {
		return super.setMovement(forward, clockwise);
	}
	
	@Override
	public boolean setMovement(double forward, double clockwise) {
		return super.setMovement(forward, clockwise);
	}

}
