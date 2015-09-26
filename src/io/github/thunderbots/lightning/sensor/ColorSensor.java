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

import java.awt.Color;

public class ColorSensor extends com.qualcomm.robotcore.hardware.ColorSensor implements Sensor {
	
	private com.qualcomm.robotcore.hardware.ColorSensor baseSensor;
	
	public ColorSensor(com.qualcomm.robotcore.hardware.ColorSensor baseSensor) {
		this.baseSensor = baseSensor;
	}

	@Override
	public Object getReadValue() {
		return this.color();
	}
	
	@Override
	public String getStringValue() {
		return this.getReadValue().toString();
	}

	@Override
	public SensorType getType() {
		return SensorType.COLOR;
	}
	
	public Color color() {
		return new Color(this.red(), this.green(), this.blue());
	}

	/*
	 * Delegate methods for the base sensor
	 */

	public int alpha() {
		return baseSensor.alpha();
	}

	public int argb() {
		return baseSensor.argb();
	}

	public int blue() {
		return baseSensor.blue();
	}

	public void close() {
		baseSensor.close();
	}

	public void enableLed(boolean arg0) {
		baseSensor.enableLed(arg0);
	}

	public boolean equals(Object arg0) {
		return baseSensor.equals(arg0);
	}

	public String getConnectionInfo() {
		return baseSensor.getConnectionInfo();
	}

	public String getDeviceName() {
		return baseSensor.getDeviceName();
	}

	public int getVersion() {
		return baseSensor.getVersion();
	}

	public int green() {
		return baseSensor.green();
	}

	public int hashCode() {
		return baseSensor.hashCode();
	}

	public int red() {
		return baseSensor.red();
	}

	public String toString() {
		return baseSensor.toString();
	}

}
