package com.company.framework.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Human-readable description of what a test verifies, read via reflection by TestListener and
 * rendered in the PDF report - kept separate from TestNG's own @Test(description=...) so the
 * report's narrative isn't tied to TestNG's annotation.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TestDescription {
    String value();
}
