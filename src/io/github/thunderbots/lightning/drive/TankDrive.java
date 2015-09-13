package io.github.thunderbots.lightning.drive;

/**
 * A {@code TankDrive} is a {@code DriveSystem} that represents a standard tank drive
 * system, with standard parallel wheels on both sides of the robot.
 * <p>
 * The {@code TankDrive} class is a subclass of {@code MecanumDrive} for technical reasons.
 * The code that controlls a tank drive system is exactly the same as the code that
 * controlls a mecanum drive system, with the one difference being that the strafing vector
 * must be zero if the physical robot does not use mecanum drive.
 *
 * @author Zach Ohara
 */
public class TankDrive extends MecanumDrive {

	/**
	 * Constructs a new {@code TankDrive} with the given {@code DriveMotorSet} as a base.
	 *
	 * @param wheels the {@code DriveMotorSet} of this drive system.
	 * @see DriveSystem#DriveSystem(DriveMotorSet)
	 */
	public TankDrive(DriveMotorSet wheels) {
		super(wheels);
	}

	/**
	 * Constructs a enw {@code TankDrive} that uses the motors with the given names.
	 *
	 * @param motornames the names of the motors to use with this drive system.
	 * @see DriveSystem#DriveSystem(String[])
	 */
	public TankDrive(String[] wheelnames) {
		super(wheelnames);
	}

	/**
	 * @deprecated A tank drive system cannot accept a right-facing vector as an argument
	 * for movement. The purpose of this method is only to filter out any possible
	 * right-facing, non-zero vector argument to prevent damage to the pysical motors.
	 * <p>
	 * Instead of this method, use the two-argument version, which accepts only forward
	 * and clockwise vectors. This method is declared in {@code DriveSystem} and implemented
	 * in {@code MecanumDrive}.
	 */
	@Override
	@Deprecated
	public boolean setMovement(double forward, double right, double clockwise) {
		return super.setMovement(forward, 0, clockwise);
	}

}
