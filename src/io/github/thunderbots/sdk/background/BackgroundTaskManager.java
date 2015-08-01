/* 
 * BackgroundTaskManager.java
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

package io.github.thunderbots.sdk.background;

import java.util.ArrayList;
import java.util.List;

public class BackgroundTaskManager {

	private List<BackgroundUpdatable> updateList;
	private UpdateTaskThread updateThread;

	/**
	 * Constructs a task manager containing with no objects registered
	 */
	public BackgroundTaskManager() {
		this.updateList = new ArrayList<BackgroundUpdatable>();
		this.updateThread = new UpdateTaskThread(this);
	}

	/**
	 * Add an updatable object to this task manager.
	 * @param update
	 */
	public synchronized void register(BackgroundUpdatable update) {
		this.updateList.add(update);
	}

	/**
	 * Remove an updatable object from this task manager.
	 * @param update the object to be removed
	 * @return {@code true} if the object was successfully removed;
	 * {@code false} otherwise
	 */
	public synchronized boolean remove(BackgroundUpdatable update) {
		return this.updateList.remove(update);
	}

	/**
	 * Starts the execution of the task manager. This method will also create a
	 * new thread for the task manager to run on.
	 */
	public void start() {
		this.updateThread.start();
	}

	/**
	 * Stops the execution of the task manager. This method will also stop the
	 * dedicated task, but will not delete any references to the task.
	 */
	public void stop() {
		this.updateThread.stopExecution();
	}

	/**
	 * Loops through all registered objects, and calls their
	 * {@code doBackgroundUpdate()} method.
	 */
	private synchronized void updateAll() {
		for (BackgroundUpdatable update : this.updateList)
			update.doBackGroundUpdate();
	}

	/**
	 * An {@code UpdateTaskThread} is a {@code Thread} that keeps a reference
	 * to a {@code BackgroundTaskManager} and, when running, repeatedly calls
	 * its {@code updateAll()} method.
	 */
	private static class UpdateTaskThread extends Thread {

		private BackgroundTaskManager manager;
		private boolean isRunning;

		/**
		 * Constructs a new thread with a reference to the given task manager
		 * @param manager the {@code BackgroundTaskManager} that should be
		 * attached to this thread
		 */
		public UpdateTaskThread(BackgroundTaskManager manager) {
			super();
			this.manager = manager;
			this.isRunning = false;
		}

		@Override
		public void run() {
			this.isRunning = true;
			while (this.isRunning) {
				this.manager.updateAll();
			}
		}

		/**
		 * Safely stops the execution of the theat, but does not delete it.
		 */
		public void stopExecution() {
			this.isRunning = false;
		}

	}

	/* 
	 * 
	 * +++----------------------------------------------------------------------+++
	 * +++----------------------------------------------------------------------+++
	 * |||                                                                      |||
	 * |||                                                                      |||
	 * |||                    Class content ends after this point               |||
	 * |||                                                                      |||
	 * |||      Everything below here is only for testing or demonstration      |||
	 * |||                                                                      |||
	 * |||                                                                      |||
	 * +++----------------------------------------------------------------------+++
	 * +++----------------------------------------------------------------------+++
	 * 
	 * 
	 */

	@Deprecated
	public static void main(String[] args) {
		BackgroundTaskManager taskManager = new BackgroundTaskManager();
		new SomeClassA(taskManager);
		new SomeClassB(taskManager);
		taskManager.start();
	}

	@Deprecated
	private static class SomeClassA implements BackgroundUpdatable {

		public SomeClassA(BackgroundTaskManager taskManager) {
			taskManager.register(this);
		}

		@Override
		public void doBackGroundUpdate() {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ignore) {}
			System.out.println("SomeClassA is updated");
		}

	}

	@Deprecated
	private static class SomeClassB implements BackgroundUpdatable {

		public SomeClassB(BackgroundTaskManager taskManager) {
			taskManager.register(this);
		}

		@Override
		public void doBackGroundUpdate() {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ignore) {}
			System.out.println("SomeClassB is updated");
		}

	}


}
