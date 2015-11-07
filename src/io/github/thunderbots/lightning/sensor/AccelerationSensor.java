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

	/**
	 * The {@code AccelerationSensor} gets the information from the accelerometer in the phone.
	 */

public class AccelerationSensor extends com.qualcomm.robotcore.hardware.AccelerationSensor implements Sensor {
	
	/**
	 * Sets up the baseSensor to get the information from the AccelerationSensor.
	 */
	
	private com.qualcomm.robotcore.hardware.AccelerationSensor baseSensor;
	
	/**
	 * @param baseSensor is the sensor used as AccelerationSensor
	 * @return baseSensor exclusive to this class.
	 */

	public AccelerationSensor(com.qualcomm.robotcore.hardware.AccelerationSensor baseSensor) {
		this.baseSensor = baseSensor;
	}
	
	/**
	 * Gets the current acceleration of the sensor. This is an {@code Object} because the actual
	 * return type is specific to the type of sensor.
	 *
	 * @return the current acceleration of the sensor.
	 */

	@Override
	public Object getReadValue() {
		return this.baseSensor.getAcceleration();
	}
	
	/**
	 * Gets a string representation of the current reading of this sensor. In most cases,
	 * this should simply be the result of {@code .toString()} on the object returned by
	 * {@link #getReadValue()}.
	 * 
	 * @return the string value of the current acceleration
	 * 
	 * Calls on toString method
	 */

	@Override
	public String getStringValue() {
		return this.getReadValue().toString();
	}
	
	/**
	 * Gets the type of the sensor, which is Acceleration
	 * 
	 * @return the type of sensor
	 * @see SensorType
	 */

	@Override
	public SensorType getType() {
		return SensorType.ACCELERATION;
	}

	/*
	 * Delegate methods for the base sensor
	 */

	@Override
	public void close() {
		this.baseSensor.close();
	}

	/**
	 * Gets the current Acceleration as provided by the baseSensor
	 * 
	 * @return baseSensor acceleration as 0
	 */
	
	@Override
	public boolean equals(Object arg0) {
		return this.baseSensor.equals(arg0);
	}
	
	/**
	 * Gets the current Acceleration as provided by the baseSensor
	 * 
	 * @return the current Acceleration
	 */

	@Override
	public Acceleration getAcceleration() {
		return this.baseSensor.getAcceleration();
	}
	
	/**
	 * Gets the current ConnectionInformation as provided by the baseSensor
	 * 
	 * @return the current ConnectionInformation in a string
	 */

	@Override
	public String getConnectionInfo() {
		return this.baseSensor.getConnectionInfo();
	}

	/**
	 * Gets the current DeviceName as provided by the baseSensor
	 * 
	 * @return the current DeviceName in a string
	 */
	
	@Override
	public String getDeviceName() {
		return this.baseSensor.getDeviceName();
	}
	
	/**
	 * Gets the current Version as provided by the baseSensor
	 * 
	 * @return the current Version in an integer
	 */

	@Override
	public int getVersion() {
		return this.baseSensor.getVersion();
	}
	
	/**
	 * Gets the current HashCode as provided by the baseSensor
	 * 
	 * @return the current HashCode in an integer
	 */

	@Override
	public int hashCode() {
		return this.baseSensor.hashCode();
	}
	
	/**
	 * Gets the current Status as provided by the baseSensor
	 * 
	 * @return the current connection information in a string
	 */

	@Override
	public String status() {
		return this.baseSensor.status();
	}
	
	/**
	 * This method returns something in a string
	 * 
	 * @return string version of something
	 */

	@Override
	public String toString() {
		return this.baseSensor.toString();
	}
}
