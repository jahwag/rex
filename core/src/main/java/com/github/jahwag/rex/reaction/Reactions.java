package com.github.jahwag.rex.reaction;

import com.github.jahwag.rex.command.Command;

/**
 * Repository for instances of {@link Reaction}.
 */
public interface Reactions {

    /**
     * Registers the specified {@link Reaction}.
     *
     * @param reaction     reaction to subscribe
     * @param commandClass command to subscribe to
     * @param <C>          component type to which the {@link Reaction} will be registered
     */
    <C extends Command<?>> void subscribe(Reaction<C, ?> reaction, Class<C> commandClass);

    /**
     * Returns the {@link Reaction} for this particular {@link Command}.
     *
     * @param command command to use
     * @return {@link Reaction} found, or {@link Reaction#none()} otherwise
     */
    Reaction findBy(Command<?> command);

    /**
     * Unregisters the specified {@link Reaction}.
     *
     * @param reaction reaction to unsubscribe
     */
    void unsubscribe(Reaction<?, ?> reaction);


}
