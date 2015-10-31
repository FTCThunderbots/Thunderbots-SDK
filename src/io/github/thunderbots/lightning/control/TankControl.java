package io.github.thunderbots.lightning.control;

import io.github.thunderbots.lightning.Lightning;
import io.github.thunderbots.lightning.control.Joystick;




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
	protected void tankMovement() {
		Joystick drivingGamepad = Lightning.getJoystick(1);
		if ()
	}
}
