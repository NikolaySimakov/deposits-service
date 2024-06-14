package ru.mts.starter.annotation;


import ru.mts.starter.enums.LoggingLevel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Logging {

    String value() default "";
    boolean enter() default false;
    boolean exit() default false;
    LoggingLevel level() default LoggingLevel.INFO;
    boolean includeParams() default false;
    boolean includeResult() default false;

}
