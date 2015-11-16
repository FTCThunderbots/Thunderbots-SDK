/* Copyright (C) 2015 Thunderbots Robotics
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

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import io.github.thunderbots.lightning.Lightning;
import io.github.thunderbots.lightning.control.ButtonHandler.PressType;

/**
 * 
 *
 * @author Zach Ohara
 */
public class JoystickMonitor {
	
	/**
	 * The joystick to monitor.
	 */
	/* (non-Javadoc)
	 * If at all possible in the future, this should be a reference to a
	 * Joystick object, not just an ID number.
	 */
	private int joystick;
	
	/**
	 * The {@code Methods} that are called when buttons specified in their
	 * @ButtonHandler annotation are pressed.
	 */
	private Map<JoystickButton, List<Method>> handlers;
	
	/**
	 * Contains a Method and its instance.
	 */
	private Map<Method, JoystickListener> instances;
	
	/**
	 * The last 'snapshot' of the buttons on the gamepad.
	 */
	private List<JoystickButton> lastButtons;
	
	/**
	 * Constructs a new JoystickMonitor that monitors the given joystick.
	 *
	 * @param joystick the joystick to monitor.
	 */
	public JoystickMonitor(int joystick) {
		this.joystick = joystick;
		this.handlers = new HashMap<JoystickButton, List<Method>>();
		this.fillHandlerMap();
		Lightning.getTaskScheduler().registerTask(new MonitorUpdateRunnable());
		this.lastButtons = Lightning.getJoystick(this.joystick).toButtonList();
	}
	
	private void fillHandlerMap() {
		for (JoystickButton button : JoystickButton.values()) {
			this.handlers.put(button, new LinkedList<Method>());
		}
	}
	
	public void registerJoystickListener(JoystickListener listener) {
		Class<?> c = listener.getClass();
		for (Method m : c.getMethods()) {
			if (m.isAnnotationPresent(ButtonHandler.class) && m.getAnnotation(
					ButtonHandler.class).joystick() == this.joystick) {
				this.handlers.get(m.getAnnotation(ButtonHandler.class).button()).add(m);
				this.instances.put(m, listener);
			}
		}
	}
	
	private void runHandlers() {
		List<JoystickButton> newButtons = Lightning.getJoystick(
				this.joystick).toButtonList();
		for (JoystickButton button : JoystickButton.values()) {
			for (Method m : this.handlers.get(button)) {
				ButtonHandler a = m.getAnnotation(ButtonHandler.class);
				if (a.type() == PressType.PRESS) {
					if (!this.lastButtons.contains(button) && newButtons.contains(button)) {
						this.invokeListenerMethod(m, this.instances.get(m));
					}
				}
				if (a.type() == PressType.RELEASE) {
					if (this.lastButtons.contains(button) && !newButtons.contains(button)) {
						this.invokeListenerMethod(m, this.instances.get(m));
					}
				}
			}
		}
		this.lastButtons = newButtons;
	}
	
	/**
	 * Invokes the given method on the given instance, and catches all
	 * exceptions.
	 *
	 * @param method the method to call.
	 * @param instance the instance to call the method on.
	 */
	private void invokeListenerMethod(Method method, Object instance) {
		try {
			method.invoke(instance, new Object[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private class MonitorUpdateRunnable implements Runnable {

		@Override
		public void run() {
			JoystickMonitor.this.runHandlers();
		}
		
	}

}
