/* Copyright (C) 2015-2016 Thunderbots Robotics
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

package io.github.thunderbots.lightning.sensor;

import com.qualcomm.robotcore.hardware.IrSeekerSensor.IrSeekerIndividualSensor;
import com.qualcomm.robotcore.hardware.IrSeekerSensor.Mode;

public class IRSeekerSensor implements Sensor {
	
	private com.qualcomm.robotcore.hardware.IrSeekerSensor baseSensor;
	
	public IRSeekerSensor(com.qualcomm.robotcore.hardware.IrSeekerSensor baseSensor) {
		this.baseSensor = baseSensor;
	}

	@Override
	public Object getValue() {
		return this.baseSensor.getStrength();
	}
	
	@Override
	public String getStringValue() {
		return this.getValue().toString();
	}

	@Override
	public SensorType getType() {
		return SensorType.IR_SEEKER;
	}

	/*
	 * Delegate methods for the base sensor
	 */

	public void close() {
		baseSensor.close();
	}

	public boolean equals(Object arg0) {
		return baseSensor.equals(arg0);
	}

	public double getAngle() {
		return baseSensor.getAngle();
	}

	public String getConnectionInfo() {
		return baseSensor.getConnectionInfo();
	}

	public String getDeviceName() {
		return baseSensor.getDeviceName();
	}

	public IrSeekerIndividualSensor[] getIndividualSensors() {
		return baseSensor.getIndividualSensors();
	}

	public Mode getMode() {
		return baseSensor.getMode();
	}

	public double getStrength() {
		return baseSensor.getStrength();
	}

	public int getVersion() {
		return baseSensor.getVersion();
	}

	public int hashCode() {
		return baseSensor.hashCode();
	}

	public void setMode(Mode arg0) {
		baseSensor.setMode(arg0);
	}

	public boolean signalDetected() {
		return baseSensor.signalDetected();
	}

	public String toString() {
		return baseSensor.toString();
	}

}
