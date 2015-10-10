package io.github.thunderbots.lightning.utility;

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
