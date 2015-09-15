package io.github.thunderbots.lightning.opmode;

/**
 * {@code Robot} is an interface that should be implemented by any class representing a
 * physical robot. In the future, this interface will be used to provide more seamless
 * operation between op modes objects and robot objects.
 */
public interface Robot {

	/**
	 * Initializes the robot. The implementation of this method should be used in place
	 * of a constructor. Instance variables for a robot object should initialize from
	 * the available hardware maps in this method.
	 */
	public void initializeRobot();

}
