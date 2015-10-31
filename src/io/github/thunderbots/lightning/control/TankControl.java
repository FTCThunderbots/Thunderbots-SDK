package io.github.thunderbots.lightning.control;

import io.github.thunderbots.lightning.Lightning;
import io.github.thunderbots.lightning.control.Joystick;
import io.github.thunderbots.lightning.utility.MathUtil;



/*
 * Tank Control:
 * If the rightstickY and leftstickY is above 0 then it's moving foward
 * If rightstickY and leftstickY is below then it moves backwards
 * If rightstickY > leftstickY then it moves left
 * If rightstickY < leftstickY then it moves right
 * Then calculate the magnitude
 */


/**
 *
 * TankControl will input controls from the Joystick class and if the right stick and left stick 
 * are pointed foward, then the robot will move foward.
 * 
 * If both are pointed backwards, then it moves backwards.
 * 
 * If they are pointed in different directions then it will turn
 * 
 * @author Sean Knight
 * @author Jake Ohara
 */


public class TankControl {
	
	/**
	 * Returns the motion of the robot when in Tank mode
	*/
	
	protected void tankMovement() {
		
		/**
		 * Gets drivingGamepad from Joystick
		 */
		
		Joystick drivingGamepad = Lightning.getJoystick(1);
		
		/**
		 * Returns the motion and magnitude of that motion when rightStickY and leftStickY is greater than 0
		 * 
		 * MathUtil.average finds the average of the Y values of the sticks.
		 * This will be used as the magnitude of 
		 */
		
		if (drivingGamepad.rightStickY() > 0 && drivingGamepad.leftStickY() > 0) {
			//forward power is average is both of them 
			double average = MathUtil.average(drivingGamepad.rightStickY(), drivingGamepad.leftStickY());
			return;
			
		}
	}
}
