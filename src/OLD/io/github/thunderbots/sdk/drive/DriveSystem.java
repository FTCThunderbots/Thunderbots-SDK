/* 
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

public abstract class DriveSystem {

	/**
	 * Sets the movement power of the robot's motors with forward,
	 * right, and clockwise vectors. A proper implementation of this
	 * method will scale down all vectors evenly until all vectors
	 * are within a valid range.
	 * @param forward the magnitude of the forward vector
	 * @param right the magnitude of the right vector
	 * @param clockwise the magnitude of the clockwise spin vector
	 * @return {@code true} if the operation was successful;
	 * {@code false} otherwise
	 */
	public abstract boolean setMovement(int forward, int right, int clockwise);

	/**
	 * Sets the movement power of the robot's motors with forward and
	 * clockwise vectors. A proper implementation of this method will
	 * scale down both vectors evenly until both vectors are within a
	 * valid range.
	 * @param forward the magnitude of the forward vector
	 * @param clockwise the magnitude of the clockwise spin vector
	 * @return {@code true} if the operation was successful;
	 * {@code false} otherwise
	 */
	public boolean setMovement(int forward, int clockwise) {
		return this.setMovement(forward, 0, clockwise);
	}

	/**
	 * Stops the robot. The robot will remain stopped until told to
	 * do otherwise. If the robot is not moving when this method is
	 * called, there will be no noticable effect. If this is the
	 * case, this method will return {@code true} if the operation
	 * would otherwise succeed.
	 * @return {@code true} if the operation was successful;
	 * {@code false} otherwise
	 */
	public boolean halt() {
		return this.setMovement(0, 0);
	}

	/**
	 * Sets the forward movement power of the robot. The robot will
	 * drive forward at the given power until told to do otherwise.
	 * @param power the forward movement power
	 * @return {@code true} if the operation was successful;
	 * {@code false} otherwise
	 */
	public boolean drive(int power) {
		return this.setMovement(power, 0);
	}

	/**
	 * Sets the sideways (right) movement power of the robot. The
	 * robot will continue to strafe at the given power until told
	 * to do otherwise. This method may throw an exception if the
	 * drive system on the robot is not capable of strafing.
	 * @param power the sideways (right) movement power
	 * @return {@code true} if the operation was successful;
	 * {@code false} otherwise
	 */
	public boolean strafe(int power) {
		return this.setMovement(0, power, 0);
	}

	/**
	 * Sets the rotational power of the robot. The robot will keep
	 * rotating at the given power until told to do otherwise.
	 * @param power the rotational movement power
	 * @return {@code true} if the operation was successful;
	 * {@code false} otherwise
	 */
	public boolean rotate(int power) {
		return this.setMovement(0, power);
	}

	/**
	 * Sets the 'swing' power of the robot. The robot is 'swinging'
	 * if its forward and clockwise vectors are equal in magnitude,
	 * but not necessarily in polarity. The robot will continue to
	 * swing in the given direction and at the given power until told
	 * to do otherwise.
	 * @param clockwise {@code true} if the robot should swing
	 * clockwise, or {@code false} if the robot should swing
	 * counter-clockwise
	 * @param power the forward movement power
	 * @return {@code true} if the operation was successful;
	 * {@code false} otherwise
	 */
	public boolean swing(boolean clockwise, int power) {
		int directionMultiplier = clockwise ? 1 : -1;
		return this.setMovement(power, Math.abs(power) * directionMultiplier);
	}

	/**
	 * Sets the 'traverse' power of the robot. The robot is
	 * 'traversing' if its forward and sideways vectors are equal in
	 * magnitude, but not necessarily in polarity. The robot will
	 * continue to traverse in the given direction and at the given
	 * power until told to do otherwise.
	 * @param right {@code true} if the robot should traverse right,
	 * or {@code false} if the robot traverse left
	 * @param power the forward movement power
	 * @return {@code true} if the operation was successful;
	 * {@code false} otherwise
	 */
	public boolean traverse(boolean right, int power) {
		int directionMultiplier = right ? 1 : -1;
		return this.setMovement(power, Math.abs(power) * directionMultiplier, 0);
	}

	/**
	 * Drives the robot forward at the given power and for the given
	 * amount of time. When the time is up, the robot will stop, and
	 * will remain stopped until told to do otherwise. The method may
	 * fail if the drive operation fails, or if the thread is
	 * interrupted while the robot is driving. If the thread is
	 * interrupted, the exception will be caught, and the robot will
	 * stop.
	 * @param power the forward movement power
	 * @param seconds the time, in seconds, that the robot should
	 * drive for
	 * @return {@code true} if the operation was successful;
	 * {@code false} otherwise
	 */
	public boolean driveSeconds(int power, float seconds) {
		return this.drive(power)
				&& this.waitAndStop(seconds);
	}

	/**
	 * Strafes the robot right at the given power and for the given
	 * amount of time. When the time is up, the robot will stop, and
	 * will remain stopped until told to do otherwise. The method may
	 * fail if the strafe operation fails, or if the thread is
	 * interrupted while the robot is strafing. If the thread is
	 * interrupted, the exception will be caught, and the robot will
	 * stop.
	 * @param power the sideways (right) movement power
	 * @param seconds the time, in seconds, that the robot should
	 * strafe for
	 * @return {@code true} if the operation was successful;
	 * {@code false} otherwise
	 */
	public boolean strafeSeconds(int power, float seconds) {
		return this.strafe(power)
				&& this.waitAndStop(seconds);
	}

	/**
	 * Rotates the robot clockwise at the given power and for the given
	 * amount of time. When the time is up, the robot will stop, and
	 * will remain stopped until told to do otherwise. The method may
	 * fail if the rotation operation fails, or if the thread is
	 * interrupted while the robot is rotating. If the thread is
	 * interrupted, the exception will be caught, and the robot will
	 * stop.
	 * @param power the clockwise movement power
	 * @param seconds the time, in seconds, that the robot should
	 * rotate for
	 * @return {@code true} if the operation was successful;
	 * {@code false} otherwise
	 */
	public boolean rotateSeconds(int power, float seconds) {
		return this.rotate(power)
				&& this.waitAndStop(seconds);
	}

	/**
	 * Rotates the robot in the given direction and at the given
	 * power, and for the given amount of time. When the time is up,
	 * the robot will stop, and will remain stopped until told to do
	 * otherwise. The method may fail if the rotation operation
	 * fails, or if the thread is interrupted while the robot is
	 * driving. If the thread is interrupted, the exception will be
	 * caught, and the robot will stop.
	 * @param clockwise {@code true} if the robot should swing
	 * clockwise, or {@code false} if the robot should swing
	 * counter-clockwise
	 * @param power the rotational movement power
	 * @param seconds the time, in seconds, that the robot should
	 * rotate for
	 * @return {@code true} if the operation was successful;
	 * {@code false} otherwise
	 */
	public boolean swingSeconds(boolean clockwise, int power, float seconds) {
		return this.swing(clockwise, power)
				&& this.waitAndStop(seconds);
	}

	/**
	 * Traverses the robot in the given direction and at the given
	 * power, and for the given amount of time. When the time is up,
	 * the robot will stop, and will remain stopped until told to do
	 * otherwise. The method may fail if the traverse operation
	 * fails, or if the thread is interrupted while the robot is
	 * traversing. If the thread is interrupted, the exception will be
	 * caught, and the robot will stop.
	 * @param right {@code true} if the robot should traverse right,
	 * or {@code false} if the robot traverse left
	 * @param power the forward movement power
	 * @param seconds the time, in seconds, that the robot should
	 * rotate for
	 * @return {@code true} if the operation was successful;
	 * {@code false} otherwise
	 */
	public boolean traverseSeconds(boolean right, int power, float seconds) {
		return this.traverse(right, power)
				&& this.waitAndStop(seconds);
	}

	/**
	 * Wait the given amount of time, and then stop the robot. This
	 * method will also absorb any exceptions from Thread.sleep().
	 * @param seconds the time, in seconds, to wait for.
	 * @return {@code true} if the operation was successful;
	 * {@code false} otherwise
	 */
	private boolean waitAndStop(float seconds) {
		boolean uninterrupted = true;
		try {
			Thread.sleep((long) (seconds * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
			uninterrupted = false;
		}
		this.halt();
		return uninterrupted;
	}

}
