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

package io.github.thunderbots.lightning.scheduler;

import java.util.ArrayList;
import java.util.List;

/**
 * A {@code TaskScheduler} keeps a collection of {@code Runnable} objects, and calls all of
 * their {@code run()} methods sequentially and continuously.
 *
 * @author Zach Ohara
 */
public class TaskScheduler {

	/**
	 * The list of {@code Runnable} objects that should be executed.
	 */
	private volatile List<Runnable> tasks;

	/**
	 * The {@code Thread} to use for executing the registered tasks.
	 */
	private Thread taskThread;

	/**
	 * Constructs a new {@code TaskScheduler}.
	 */
	public TaskScheduler() {
		this.tasks = new ArrayList<Runnable>();
		this.taskThread = new TaskSchedulerThread();
		this.taskThread.start();
	}

	/**
	 * Adds a given {@code Runnable} to be executed in the cycle.
	 *
	 * @param task the task to be executed in the cycle.
	 */
	public void registerTask(Runnable task) {
		this.tasks.add(task);
	}

	/**
	 * Calls the {@code run()} method of all {@code Runnable} objects that have been
	 * registered with this task scheduler.
	 */
	private void runAllTasks() {
		for (Runnable r : this.tasks) {
			r.run();
		}
	}

	/**
	 * A {@code TaskSchedulerThread} is a {@code Thread} that is used to run all of the
	 * registered tasks.
	 */
	private class TaskSchedulerThread extends Thread {

		@Override
		public void run() {
			while (!this.isInterrupted()) {
				TaskScheduler.this.runAllTasks();
			}
		}

	}

}
