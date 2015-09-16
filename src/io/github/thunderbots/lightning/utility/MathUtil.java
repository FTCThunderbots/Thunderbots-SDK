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

package io.github.thunderbots.lightning.utility;

/**
 * The {@code MathUtil} class contains static methods for common calculations and
 * mathematical utilities.
 *
 * @author Zach Ohara
 */
public class MathUtil {

	/**
	 * Scales a number within some range to a corresponding position relative to another
	 * range. If the absolute value of the number is below the input range, 0 will be
	 * returned. If the absolute value of the number is above the input range, the maximum
	 * value of the output range, but with the same polarity as the number, will be
	 * returned.
	 *
	 * @param num the number to be scaled
	 * @param inputRange a two-element array representing the lowest and and highest
	 * values, respectively, of the range to be scaled from
	 * @param outputRange a two-element array representing the lowest and and highest
	 * values, respectively, of the range to be scaled to
	 * @return the number, scaled to the same relative position within the output range
	 */
	public static double scaleToRange(double num, double[] inputRange, double[] outputRange) {
		double signum = Math.signum(num);
		num = Math.abs(num);
		if (num < inputRange[0]) {
			return 0;
		}
		if (num > inputRange[1]) {
			return outputRange[1] * signum;
		}
		double inputDifference = inputRange[1] - inputRange[0];
		double outputDifference = outputRange[1] - outputRange[0];
		double posInRange = (num - inputRange[0]) / inputDifference;
		double posOutRange = outputRange[0] + (posInRange * outputDifference);
		return posOutRange * signum;
	}

}
