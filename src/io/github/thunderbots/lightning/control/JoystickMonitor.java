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

import io.github.thunderbots.lightning.Lightning;
import io.github.thunderbots.lightning.control.ButtonHandler.PressType;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

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
	 * If at all possible in the future, this should be a reference to a Joystick object, not just
	 * an ID number.
	 */
	int joystick;
	
	/**
	 * The {@code JoystickListeners} that are listening to the joystick.
	 */
	private List<JoystickListener> listeners;
	
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
		this.listeners = new ArrayList<JoystickListener>();
		Lightning.getTaskScheduler().registerTask(new MonitorUpdateRunnable());
		this.lastButtons = Lightning.getJoystick(this.joystick).toButtonList();
	}
	
	public void registerJoystickListener(JoystickListener listener) {
		this.listeners.add(listener);
	}
	
	public void updateJoysticks() {
		List<JoystickButton> newButtons = Lightning.getJoystick(this.joystick).toButtonList();
		for (JoystickButton button : JoystickButton.values()) {
			for (JoystickListener listener : this.listeners) {
				for (Method m : listener.getClass().getMethods()) {
					for (Annotation a : m.getAnnotations()) {
						tryButtonHandlers(newButtons, button, listener, m, a);
					}
				}
			}
		}
		this.lastButtons = newButtons;
	}
	
	private void tryButtonHandlers(List<JoystickButton> newButtons, JoystickButton button,
			JoystickListener listener, Method m, Annotation a) {
		if (a instanceof ButtonHandler) {
			ButtonHandler handler = (ButtonHandler) a;
			if (handler.button() == button && handler.joystick() == this.joystick) {
				if (handler.type() == PressType.PRESS) {
					if (!this.lastButtons.contains(button) && newButtons.contains(button)) {
						this.invokeListenerMethod(m, listener);
					}
				} else {
					if (this.lastButtons.contains(button) && !newButtons.contains(button)) {
						this.invokeListenerMethod(m, listener);
					}									
				}
			}
		}
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
	
	private class MonitorUpdateRunnable implements Runnable {

		@Override
		public void run() {
			JoystickMonitor.this.updateJoysticks();
		}
		
	}

}
