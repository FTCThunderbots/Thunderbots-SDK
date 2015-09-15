package io.github.thunderbots.annotation;

import java.lang.annotation.Documented;

@Documented
public @interface OpMode {
	String type();
	String name();
}
