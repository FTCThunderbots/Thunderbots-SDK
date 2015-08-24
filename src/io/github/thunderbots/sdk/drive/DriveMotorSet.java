/* Copyright (C) 2015 Zach Ohara
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

package io.github.thunderbots.sdk.drive;

import io.github.thunderbots.sdk.Robot;
import io.github.thunderbots.sdk.hardware.TMotor;

/**
 * 
 *
 * @author Zach Ohara
 */
public class DriveMotorSet {
	
	private TMotor[] motors;
	
	public DriveMotorSet(String[] names) {
		this.motors = new TMotor[names.length];
		for (int i = 0; i < names.length; i++) {
			motors[i] = Robot.getMotor(names[i]);
		}
	}
	
	public void setMotorPowers(double[] powers) {
		if (motors.length != powers.length) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < powers.length; i++) {
			this.motors[i].setPower(powers[i]);
		}
	}

}
