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
 * A {@code JoystickMonitor} is an object that attaches to a specific joystick object,
 * and monitors any changes in the joystick inputs. Any detected changes are forwarded
 * to the objects that are listening to the specific joystick.
 * <p>
 * Objects that should listen to the joysticks should register themselves with the
 * appropriate {@code JoystickMonitor}.
 *
 * @author Zach Ohara
 * @author Pranav Mathur
 */
public class JoystickMonitor {
	
	/**
	 * The joystick to monitor.
	 */
	/* (non-Javadoc)
	 * If at all possible in the future, this should be a reference to a Joystick object, not just
	 * an ID number.
	 */
	private int joystick;
	
	/**
	 * The {@code Methods} that are called when buttons specified in the
	 * {@code ButtonHandler} annotation are updated.
	 */
	private Map<JoystickButton, List<Method>> handlers;
	
	/**
	 * The map of all the appropriate methods to the specific objects that
	 * contain them.
	 */
	private Map<Method, JoystickListener> instances;
	
	/**
	 * The latest 'snapshot' of the buttons on the gamepad. The list contains every
	 * button on the joystick that is pressed, in no specific order.
	 */
	private List<JoystickButton> lastButtons;
	
	/**
	 * Constructs a new JoystickMonitor that should monitor the given joystick.
	 *
	 * @param joystick the joystick to monitor.
	 */
	public JoystickMonitor(int joystick) {
		this.joystick = joystick;
		this.handlers = new HashMap<JoystickButton, List<Method>>();
		this.instances = new HashMap<Method, JoystickListener>();
		this.fillHandlerMap();
		Lightning.getTaskScheduler().registerTask(new MonitorUpdateRunnable());
		this.lastButtons = Lightning.getJoystick(this.joystick).toButtonList();
	}
	
	/**
	 * Registers the given joystick listener so that joystick updates can be sent
	 * to it.
	 */
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
	
	/**
	 * Checks for updates in the joystick, and forwards those updates to all the
	 * registered joystick listeners.
	 */
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
	 * Invokes the given method on the given instance, and catches all exceptions.
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
	
	/**
	 * Populates the maip of button handlers with an entry for every joystick button.
	 */
	private void fillHandlerMap() {
		for (JoystickButton button : JoystickButton.values()) {
			this.handlers.put(button, new LinkedList<Method>());
		}
	}
	
	/**
	 * The {@code MonitorUpdateRunnable} class is run through the task scheduler, and
	 * is used to continuously check the joystick for any changes.
	 */
	private class MonitorUpdateRunnable implements Runnable {

		@Override
		public void run() {
			JoystickMonitor.this.runHandlers();
		}
		
	}

}
