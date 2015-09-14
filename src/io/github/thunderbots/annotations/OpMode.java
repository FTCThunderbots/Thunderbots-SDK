package io.github.thunderbots.annotations;

import java.lang.annotation.Documented;

@Documented
public @interface OpMode {
	String type();
	String name();
}
