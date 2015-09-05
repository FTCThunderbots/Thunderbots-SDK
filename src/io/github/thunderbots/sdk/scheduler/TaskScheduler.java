package io.github.thunderbots.sdk.scheduler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zach Ohara
 */
public class TaskScheduler {

	private volatile List<Runnable> tasks;

	private Thread taskThread;

	public TaskScheduler() {
		this.tasks = new ArrayList<Runnable>();
		this.taskThread = new TaskSchedulerThread();
		this.taskThread.start();
	}

	public void registerTask(Runnable task) {
		this.tasks.add(task);
	}

	private void runAllTasks() {
		for (Runnable r : this.tasks) {
			r.run();
		}
	}

	private class TaskSchedulerThread extends Thread {

		@Override
		public void run() {
			while (!this.isInterrupted()) {
				TaskScheduler.this.runAllTasks();
			}
		}

	}

}
