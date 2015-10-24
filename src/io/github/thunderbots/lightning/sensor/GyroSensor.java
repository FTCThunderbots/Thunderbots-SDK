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

public class GyroSensor extends com.qualcomm.robotcore.hardware.GyroSensor implements Sensor {

	private com.qualcomm.robotcore.hardware.GyroSensor baseSensor;

	public GyroSensor(com.qualcomm.robotcore.hardware.GyroSensor baseSensor) {
		this.baseSensor = baseSensor;
	}

	@Override
	public Object getReadValue() {
		return this.baseSensor.getRotation();
	}

	@Override
	public String getStringValue() {
		return this.getReadValue().toString();
	}

	@Override
	public SensorType getType() {
		return SensorType.GYRO;
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
	public String getConnectionInfo() {
		return this.baseSensor.getConnectionInfo();
	}

	@Override
	public String getDeviceName() {
		return this.baseSensor.getDeviceName();
	}

	@Override
	public double getRotation() {
		return this.baseSensor.getRotation();
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
	public String status() {
		return this.baseSensor.status();
	}

	@Override
	public String toString() {
		return this.baseSensor.toString();
	}

}
