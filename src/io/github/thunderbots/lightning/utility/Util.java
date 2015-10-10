package io.github.thunderbots.lightning.utility;

/**
 * The {@code Util} class contains static methods for common utilities.
 *
 * @author Pranav Mathur
 */
public final class Util {
	
	private Util() {
		
	}
	
	public static boolean sleep(long milliseconds) {
		try {
			Thread.sleep(milliseconds);;
		} catch(InterruptedException ex) {
			return false;
		}
		return true;
	}

}
