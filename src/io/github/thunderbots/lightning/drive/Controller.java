package io.github.thunderbots.lightning.drive;

/**
 * Provides methods for all PID controller classes.
 * @author Pranav Mathur
 */
public interface Controller {
	double[] correctedOutput(double forward, double strafe, double clockwise);
}
