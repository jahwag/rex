package com.github.jahwag.rex.annotations;

import com.github.jahwag.rex.command.Command;

import javax.enterprise.context.Dependent;
import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Specifies that a {@link com.github.jahwag.rex.reaction.Reaction} should be automatically subscribed to the given
 * command.
 */
@Dependent
@Retention(RUNTIME)
@Target({TYPE, PARAMETER})
@Documented
@Inherited
public @interface AutoSubscribe {

    Class<? extends Command<?>> value();
}