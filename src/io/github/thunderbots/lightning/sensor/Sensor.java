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
 * The {@code Sensor} interface acts as a supertype for all sensor objects.
 * 
 * @author Zach Ohara
 */
public interface Sensor {
	
	/**
	 * Gets the current reading of the sensor. This is an {@code Object} because the actual return
	 * type is specific to the type of sensor.
	 * 
	 * @return the current reading of the sensor.
	 */
	public Object getValue();
	
	/**
	 * Gets the type of the sensor.
	 * 
	 * @return the type of the sensor.
	 * @see SensorType
	 */
	public SensorType getType();

}
