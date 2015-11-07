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
	 * imports the Java package for colors
	 */

import java.awt.Color;

	/**
	 * The {@code ColorSensor} gets the information from the colorsensor in the phone.
	 */

public class ColorSensor extends com.qualcomm.robotcore.hardware.ColorSensor implements Sensor {

	/**
	 * Sets up the baseSensor to get the information from the ColorSensor.
	 */
	
	private com.qualcomm.robotcore.hardware.ColorSensor baseSensor;
	
	/**
	 * @param baseSensor is the sensor used as ColorSensor
	 * @return baseSensor exclusive to this class.
	 */	

	public ColorSensor(com.qualcomm.robotcore.hardware.ColorSensor baseSensor) {
		this.baseSensor = baseSensor;
	}
	
	/**
	 * Gets the current color of the sensor. This is an {@code Object} because the actual
	 * return type is specific to the type of sensor.
	 *
	 * @return the current acceleration of the sensor.
	 */

	@Override
	public Object getReadValue() {
		return this.color();
	}
	
	/**
	 * Gets a string representation of the current reading of this sensor. In most cases,
	 * this should simply be the result of {@code .toString()} on the object returned by
	 * {@link #getReadValue()}.
	 * 
	 * @return the string value of the current color
	 * 
	 * Calls on toString method
	 */

	@Override
	public String getStringValue() {
		return this.getReadValue().toString();
	}
	
	/**
	 * Gets the type of the sensor, which is Color
	 * 
	 * @return the type of sensor
	 * @see SensorType
	 */

	@Override
	public SensorType getType() {
		return SensorType.COLOR;
	}
	
	/**
	 * Creates method that defines what color the baseSensor can output
	 * 
	 * @return Color(rgb)
	 */

	public Color color() {
		return new Color(this.red(), this.green(), this.blue());
	}

	/*
	 * Delegate methods for the base sensor
	 */
	
	/**
	 * @return the alpha as inputed by the baseSensor as an integer
	 */

	@Override
	public int alpha() {
		return this.baseSensor.alpha();
	}
	
	/**
	 * @return the alpha, red, green, blue as inputed by the baseSensor as an integer
	 */

	@Override
	public int argb() {
		return this.baseSensor.argb();
	}
	
	/**
	 * @returns the amount of blue in a substance as an integer
	 */

	@Override
	public int blue() {
		return this.baseSensor.blue();
	}

	/*
	 * Delegate methods for the base sensor
	 */
	
	@Override
	public void close() {
		this.baseSensor.close();
	}
	
	/**
	 * Calibrates the LED 
	 * 
	 * @return as 0
	 */

	@Override
	public void enableLed(boolean arg0) {
		this.baseSensor.enableLed(arg0);
	}

	/**
	 * 
	 */
	
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
	public int getVersion() {
		return this.baseSensor.getVersion();
	}

	@Override
	public int green() {
		return this.baseSensor.green();
	}

	@Override
	public int hashCode() {
		return this.baseSensor.hashCode();
	}

	@Override
	public int red() {
		return this.baseSensor.red();
	}

	@Override
	public String toString() {
		return this.baseSensor.toString();
	}

}
