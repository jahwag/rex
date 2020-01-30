package com.github.jahwag.rex.reaction.repository;

import com.github.jahwag.rex.command.Command;
import com.github.jahwag.rex.reaction.Reaction;

/**
 * Repository for instances of {@link Reaction}.
 */
public interface Reactions {

    /**
     * Subscribes to the specified {@link Reaction}.
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
    <T> Reaction<Command<T>, T> findBy(Command<T> command);

    /**
     * Unregisters the specified {@link Reaction}.
     *
     * @param reaction reaction to unsubscribe
     */
    void unsubscribe(Reaction<?, ?> reaction);


}
