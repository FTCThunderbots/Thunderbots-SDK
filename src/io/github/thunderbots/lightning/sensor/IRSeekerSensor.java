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

public class IRSeekerSensor extends com.qualcomm.robotcore.hardware.IrSeekerSensor implements Sensor {

	private com.qualcomm.robotcore.hardware.IrSeekerSensor baseSensor;

	public IRSeekerSensor(com.qualcomm.robotcore.hardware.IrSeekerSensor baseSensor) {
		this.baseSensor = baseSensor;
	}

	@Override
	public Object getReadValue() {
		return this.baseSensor.getStrength();
	}

	@Override
	public String getStringValue() {
		return this.getReadValue().toString();
	}

	@Override
	public SensorType getType() {
		return SensorType.IR_SEEKER;
	}

	/*
	 * Delegate methods for the base sensor
	 */

	@Override
	public void close() {
		this.baseSensor.close();
	}

	@Override
	public boolean equals(Object arg0) {
		return this.baseSensor.equals(arg0);
	}

	@Override
	public double getAngle() {
		return this.baseSensor.getAngle();
	}

	@Override
	public String getConnectionInfo() {
		return this.baseSensor.getConnectionInfo();
	}

	@Override
	public String getDeviceName() {
		return this.baseSensor.getDeviceName();
	}

	@Override
	public IrSeekerIndividualSensor[] getIndividualSensors() {
		return this.baseSensor.getIndividualSensors();
	}

	@Override
	public Mode getMode() {
		return this.baseSensor.getMode();
	}

	@Override
	public double getStrength() {
		return this.baseSensor.getStrength();
	}

	@Override
	public int getVersion() {
		return this.baseSensor.getVersion();
	}

	@Override
	public int hashCode() {
		return this.baseSensor.hashCode();
	}

	@Override
	public void setMode(Mode arg0) {
		this.baseSensor.setMode(arg0);
	}

	@Override
	public boolean signalDetected() {
		return this.baseSensor.signalDetected();
	}

	@Override
	public String toString() {
		return this.baseSensor.toString();
	}

	@Override
	public int getI2cAddress() {
		return this.baseSensor.getI2cAddress();
	}

	@Override
	public double getSignalDetectedThreshold() {
		return this.baseSensor.getSignalDetectedThreshold();
	}

	@Override
	public void setI2cAddress(int arg0) {
		this.baseSensor.setI2cAddress(arg0);
	}

	@Override
	public void setSignalDetectedThreshold(double arg0) {
		this.baseSensor.setSignalDetectedThreshold(arg0);
	}

}
