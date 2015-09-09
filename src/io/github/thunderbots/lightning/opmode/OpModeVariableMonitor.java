/* Copyright (C) 2015 Zach Ohara
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

package io.github.thunderbots.lightning.opmode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.robocol.Telemetry;

import io.github.thunderbots.lightning.Lightning;

/**
 * @author Zach Ohara
 */
public class OpModeVariableMonitor implements Runnable {

	private OpMode monitor;

	private Gamepad gamepad1;
	private Gamepad gamepad2;
	private HardwareMap hardware;
	private Telemetry telemetry;

	public OpModeVariableMonitor(OpMode monitor) {
		this.monitor = monitor;
		Lightning.getTaskScheduler().registerTask(this);
	}

	@Override
	public void run() {
		boolean refreshNeeded = false;
		if (!OpModeVariableMonitor.isSameObject(this.gamepad1, this.monitor.gamepad1)) {
			this.gamepad1 = this.monitor.gamepad1;
			refreshNeeded = true;
		}
		if (!OpModeVariableMonitor.isSameObject(this.gamepad2, this.monitor.gamepad2)) {
			this.gamepad2 = this.monitor.gamepad2;
			refreshNeeded = true;
		}
		if (!OpModeVariableMonitor.isSameObject(this.hardware, this.monitor.hardwareMap)) {
			this.hardware = this.monitor.hardwareMap;
			refreshNeeded = true;
		}
		if (!OpModeVariableMonitor.isSameObject(this.telemetry, this.monitor.telemetry)) {
			this.telemetry = this.monitor.telemetry;
			refreshNeeded = true;
		}

		if (refreshNeeded) {
			Lightning.initializeRobot(this.hardware, this.telemetry, this.gamepad1, this.gamepad2);
		}
	}

	private static boolean isSameObject(Object a, Object b) {
		if (a == null || b == null) {
			return false;
		}
		synchronized (a) {
			synchronized (b) {
				return a == b;
			}
		}
	}

}
