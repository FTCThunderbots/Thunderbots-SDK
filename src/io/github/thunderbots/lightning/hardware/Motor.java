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

package io.github.thunderbots.lightning.hardware;

import io.github.thunderbots.lightning.functionality.Correctable;
import io.github.thunderbots.lightning.functionality.PID;
import io.github.thunderbots.lightning.utility.Telemetry;
import io.github.thunderbots.lightning.utility.Util;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * A {@code Motor} represents any physical DC motor that is connected to the robot.
 *
 * @author Zach Ohara
 * @author Pranav Mathur
 */
public class Motor implements Runnable, Correctable{

	/**
	 * The {@code DcMotor} that this object is based on.
	 */
	private DcMotor basemotor;

	/**
	 * The encoder that is attached to this motor. This will always be a defined object,
	 * even if there is no encoder attached to this motor.
	 */
	private Encoder encoder;
	
	/**
	 * The Proportional Integral Derivative Controller
	 */
	private PID pid;
	
	/**
	 * The target speed of the motor
	 */
	private double goalSpeed;
	
	/**
	 * The current speed of the motor
	 */
	private double speed;
	
	/**
	 * Toggle for the speed control (PID) system control.
	 */
	private boolean speedControl = false;

	/**
	 * The current power of the motor.
	 */
//	private double power;

	/**
	 * The maximum power of the motor.
	 */
	public static final double MAX_POWER = 1;

	/**
	 * The power of the motor when the motor is at rest.
	 */
	public static final double REST_POWER = 0;

	/**
	 * The maximum power of the motor when the motor is spinning backwards.
	 */
	public static final double MIN_POWER = -1;

	/**
	 * Constructs a new motor that uses the given {@code DcMotor} as a base.
	 *
	 * @param basemotor the base {@code Motor}
	 */
	public Motor(DcMotor basemotor) {
		this.basemotor = basemotor;
		this.encoder = new Encoder();
		this.pid = new PID(this);
		Thread t = new Thread(this);
		t.start();
	}

	/**
	 * Gets the name of the device as it is defined in the configuration file.
	 *
	 * @return the name of the motor.
	 */
	public String getName() {
		return this.basemotor.getDeviceName();
	}

	/**
	 * Gets the encoder attached to this motor. This will return an {@code Encoder} even if
	 * there is no physical encoder attached to the physical motor.
	 *
	 * @return the encoder on this motor.
	 */
	public Encoder getEncoder() {
		return this.encoder;
	}

	/**
	 * Gets the raw encoder position value of this motor.
	 *
	 * @return the raw encoder value of this motor.
	 */
	public int getRawPosition() {
		return this.basemotor.getCurrentPosition();
	}

	/**
	 * Determines if the motor's output direction is reversed from its inputs.
	 *
	 * @return {@code true} if the motor's output is reversed from its inputs, or
	 * {@code false} if the motor's ouput has the same polarity as its input.
	 */
	public boolean isReversed() {
		return this.basemotor.getDirection() == DcMotor.Direction.REVERSE;
	}

	/**
	 * Sets if the motor's output direction is reversed from its inputs.
	 *
	 * @param reversed {@code true} if the motor's output is reversed from its inputs, or
	 * {@code false} if the motor's ouput has the same polarity as its input.
	 */
	public void setReversed(boolean reversed) {
		if (reversed) {
			this.basemotor.setDirection(DcMotor.Direction.REVERSE);
		} else {
			this.basemotor.setDirection(DcMotor.Direction.FORWARD);
		}
	}

	/**
	 * Gets the current movement power of this motor.
	 *
	 * @return the current movement power; between -1 and 1.
	 */
	public double getPower() {
		//try {
			return this.basemotor.getPower();
		//} catch (Exception e) {
		//	return this.power;
		//}
	}

	/**
	 * Sets the movement power of this motor.
	 *
	 * @param power the movement power; between -1 and 1.
	 */
	public void setPower(double power) {
		this.basemotor.setPower(power);
//		this.power = power;
	}
	
	public void toggleSpeedControl() {
		this.speedControl = !(this.speedControl);
	}
	
	public synchronized void setSpeed(double goalSpeed) {
		this.goalSpeed = goalSpeed;
	}
	
	public synchronized void setCurrentSpeed(int lastPosition, long lastTime) {
		this.speed = (double)(this.getRawPosition()-lastPosition)/(double)(System.currentTimeMillis()-lastTime);
	}
	
	private synchronized double getGoalSpeed() {
		return this.goalSpeed;
	}
	
	private synchronized double getCurrentSpeed() {
		return this.speed;
	}

	/**
	 * Stops the motor. If the motor is not currently moving, this method has no effect.
	 */
	public void stop() {
		this.setPower(Motor.REST_POWER);
	}

	@Override
	public String toString() {
		return this.basemotor.toString();
	}

	/**
	 * An {@code Encoder} represents a physical encoder that is attached to a specific
	 * motor.
	 *
	 * @author Zach Ohara
	 * @author Pranav Mathur
	 */
	public class Encoder {

		/**
		 * The value that should be considered 'zero' on the encoder.
		 */
		private int zeroPoint;

		/**
		 * The number of encoder ticks that measure exactly one full rotation of the motor.
		 */
		private double ticksPerRevolution;

		/**
		 * The number of encoder ticks that measure one inch on the circumference of the
		 * wheel.
		 */
		private double ticksPerInch;

		/**
		 * The default number of encoder ticks that measure exactly one full rotation of
		 * the motor.
		 */
		private static final double DEFAULT_TICKS_PER_REVOLUTION = 0d;

		/**
		 * The default number of encoder ticks that measure one inch on the circumference
		 * of the wheel.
		 */
		private static final double DEFAULT_TICKS_PER_INCH = 0d;

		/**
		 * Constructs a new encoder that is bound to the motor in which it is constructed.
		 */
		public Encoder() {
			this.ticksPerRevolution = Encoder.DEFAULT_TICKS_PER_REVOLUTION;
			this.ticksPerInch = Encoder.DEFAULT_TICKS_PER_INCH;
			this.reset();
		}

		/**
		 * Resets this encoder. If {@link #getPosition()} is called immediately after this
		 * method, it will return zero.
		 */
		public void reset() {
			this.zeroPoint = Motor.this.getRawPosition();
		}

		/**
		 * Gets the current position of the encoder. More formally, this returns the
		 * difference between the current position and the position when the encoder was
		 * last reset.
		 *
		 * @return the current position of the encoder.
		 */
		public int getPosition() {
			return Motor.this.getRawPosition() - this.zeroPoint;
		}

		/**
		 * Gets the current position of the encoder with respect to the zero point,
		 * converted to revolutions of the motor.
		 *
		 * @return the current position of the encoder, in revolutions.
		 */
		public double getRevolutions() {
			return this.getPosition() / this.ticksPerRevolution;
		}

		/**
		 * Gets the current position of the encoder, converted to inches on the wheel
		 * circumference.
		 *
		 * @return the current position of the encoder, in inches.
		 */
		public double getInches() {
			return this.getPosition() / this.ticksPerInch;
		}

		/**
		 * Sets the number of encoder ticks that measure exactly one full rotation of the
		 * motor.
		 *
		 * @param ticks the encoder ticks that measure exactly one full rotation of the
		 * motor.
		 * @see #ticksPerRevolution
		 */
		public void setTicksPerRevolution(double ticks) {
			this.ticksPerRevolution = ticks;
		}

		/**
		 * Sets the number of encoder ticks that measure one inch on the circumference of
		 * the wheel.
		 *
		 * @param ticks the encoder ticks that measure one inch on the circumference of the
		 * wheel.
		 * @see #ticksPerInch
		 */
		public void setTicksPerInch(double ticks) {
			this.ticksPerInch = ticks;
		}

		@Override
		public String toString() {
			return "Encoder[" + Motor.this + "]";
		}

	}

	@Override
	public double getError() {
		return this.getGoalSpeed() - this.getCurrentSpeed();
	}

	@Override
	public void run() {
		try {
			int lastPosition = this.getRawPosition();
			long lastTime = System.currentTimeMillis();
			Util.sleep(1);
			while (true) {
				this.setCurrentSpeed(lastPosition, lastTime);
				lastPosition = this.getRawPosition();
				lastTime = System.currentTimeMillis();
				if (this.speedControl)
					this.setPower(this.pid.getCorrection());
				Util.sleep(1);
			}
		} catch (Exception e) { 
			Telemetry.sendData("PID ERROR!!!", this.pid.toString()+" HAS STOPPED!");
		}
		
	}

}
