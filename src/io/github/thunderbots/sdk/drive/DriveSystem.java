package io.github.thunderbots.sdk.drive;

/**
 * 
 *
 * @author Zach Ohara
 */
public abstract class DriveSystem {
	
	private DriveMotorSet motors;
	
	public DriveSystem(DriveMotorSet wheels) {
		this.motors = wheels;
	}
	
	public abstract boolean setMovement(double forward, double clockwise);
	
	protected DriveMotorSet getWheelSet() {
		return this.motors;
	}
	
	public boolean halt() {
		return this.setMovement(0, 0);
	}
	
	public boolean drive(int power) {
		return this.setMovement(power, 0);
	}
	
	public boolean rotate(int power) {
		return this.setMovement(0, power);
	}

	public boolean swing(boolean clockwise, int power) {
		int directionMultiplier = clockwise ? 1 : -1;
		return this.setMovement(power, Math.abs(power) * directionMultiplier);
	}

	public boolean driveSeconds(int power, float seconds) {
		return this.drive(power) && this.waitAndStop(seconds);
	}

	public boolean rotateSeconds(int power, float seconds) {
		return this.rotate(power) && this.waitAndStop(seconds);
	}

	public boolean swingSeconds(boolean clockwise, int power, float seconds) {
		return this.swing(clockwise, power) && this.waitAndStop(seconds);
	}

	public boolean waitAndStop(float seconds) {
		boolean uninterrupted = true;
		try {
			Thread.sleep((long) (seconds * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
			uninterrupted = false;
		}
		this.halt();
		return uninterrupted;
	}

}
