package io.github.thunderbots.annotation;

import java.lang.annotation.Documented;

/**
 * The {@code OpMode} annotation should be added to all op modes that appear in the
 * op mode list. It details information about the type of op mode, such as
 * "teleop" or "autonomous", or anything else that the client class may define. This
 * information is not currently used. It also includes the name of the op mode, and
 * this name will eventually be used to present the op mode in the list.
 * 
 * @author Pranav Mathur
 */
@Documented
public @interface OpMode {
	
	String type();
	String name();
	
}
