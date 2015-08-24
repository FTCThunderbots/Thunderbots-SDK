/* 
 * TankDrive.java
 * Copyright (C) 2015 Thunderbots-5604
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package OLD.io.github.thunderbots.sdk.drive;

public class TankDrive extends MecanumDrive {

	/**
	 * Constructs a new tank drive system with all default settings
	 */
	public TankDrive() {
		super();
	}

	@Override
	public boolean setMovement(int forward, int right, int clockwise) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean setMovement(int forward, int clockwise) {
		return super.setMovement(forward, 0, clockwise);
	}

	/*
	private Motor [] wheels;
	private Encoder[] encoders;
	private PIDManager pid;
	private long target;

	public TankDrive() {
		this(0.2, 0.01, 1.0);
	}

	public TankDrive(double Kp, double Ki, double Kd) {
		this(Kp, Ki, Kd, 10.0);
	}

	public TankDrive(double Kp, double Ki, double Kd, double integral_cap) {
		pid = new PID(this, Kp, Ki, Kd, integral_cap);
		wheels = new Motor [4];
		encoders = new RotationalEncoder [2];

		// I am too lazy to write a for loop with just two iterations
		encoders[0] = new RotationalEncoder();
		encoders[1] = new RotationalEncoder();

		resetEncoders();

		wheels[0] = new Motor(encoders[0]);
		wheels[1] = new Motor(encoders[0]);
		wheels[2] = new Motor(encoders[1]);
		wheels[3] = new Motor(encoders[1]);

		Thread t = new Thread(this);
		t.start();
	}

	public TankDrive(double [] constants, double integral_cap) throws IllegalArgumentException {
		this(constants[0], constants[1], constants[2], integral_cap);
		if (constants.length != 3)
			throw new IllegalArgumentException("PID constants array is the wrong size!");
	}

	public TankDrive(double [] constantsWithCap) {
		this(constantsWithCap[0], constantsWithCap[1], constantsWithCap[2], constantsWithCap[3]);
		if (constantsWithCap.length != 4)
			throw new IllegalArgumentException("PID constants array is the wrong size!");
	}

	public long getError() {
		return wheels[0].getEncoder().getPos() - wheels[2].getEncoder().getPos();
	}

	public void set(long value) {
		// Sets position, not speed (minor problem with drivers,
		// Microsoft requests that it be called a "feature" :) )
		this.target = value;
	}

	public void run() {
		while (true) {
			wheels[0].set(target);
			wheels[1].set(target);
			wheels[2].set(-target);
			wheels[3].set(-target);

			double correction = pid.getCorrection();
			wheels[0].setAlignmentCorrection(correction);
			wheels[1].setAlignmentCorrection(correction);
			wheels[2].setAlignmentCorrection(-correction);
			wheels[3].setAlignmentCorrection(-correction);
		}
	}

	private void resetEncoders() {
		encoders[0].reset();
		encoders[0].reset();
	}
	 */
}

