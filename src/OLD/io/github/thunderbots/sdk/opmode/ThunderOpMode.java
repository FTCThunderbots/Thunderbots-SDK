/* Copyright (C) 2015 Thunderbots-5604
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

package OLD.io.github.thunderbots.sdk.opmode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

public abstract class ThunderOpMode extends LinearOpMode {
		
	protected abstract void initializeRobot();
	
	protected abstract void main();

	@Override
	public final void runOpMode() throws InterruptedException {
		this.initializeRobot();
		this.waitForStart();
		this.main();
	}
	
	public Gamepad gamepad1() {
		return this.gamepad1;
	}
	
	public Gamepad gamepad2() {
		return this.gamepad2;
	}
	
	public Gamepad getGamepad(int gamepad) {
		switch (gamepad) {
			case 1:
				return this.gamepad1;
			case 2:
				return this.gamepad2;
			default:
				return null;
		}
	}

}
