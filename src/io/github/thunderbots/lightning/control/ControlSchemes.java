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
package io.github.thunderbots.lightning.control;
import io.github.thunderbots.lightning.control.Joystick;
import io.github.thunderbots.lightning.drive.DriveSystem;
import io.github.thunderbots.lightning.drive.MecanumDrive;
import io.github.thunderbots.lightning.drive.TankDrive;
import io.github.thunderbots.lightning.opmode.TeleOp;
import io.github.thunderbots.lightning.opmode.LightningOpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import io.github.thunderbots.lightning.opmode.SimpleOpMode;

/*
 * @author Jake Ohara and Sean Knight
 */

/*Overview of the code:
Tank Control:
If the rightstickY and leftstickY is above 0 then it's moving foward
If rightstickY and leftstickY is below then it moves backwards
If rightstickY > leftstickY then it moves left
If rightstickY < leftstickY then it moves right
Then calculate the magnitude

Mecanum Control:
Describe here

Add joystick control from Teleop, or just extend Teleop

 */

public abstract class ControlSchemes /*extends TeleOp*/{
    /*
    What we're trying to accomplish with ControlSchemes:
    We want a control system that works in ControlScheme instead of making the control scheme in TeleOp
     */
}
