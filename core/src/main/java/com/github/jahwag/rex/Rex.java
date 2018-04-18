package com.github.jahwag.rex;

import com.github.jahwag.rex.command.Command;
import com.github.jahwag.rex.reaction.Reaction;
import org.reactivestreams.Publisher;

/**
 * A service for publishing and subscribing to commands using reactive streams.
 */
public interface Rex {

    /**
     * Publishes command to subscriber(s).
     *
     * @param command command to publish
     * @param <R>     result type
     * @return Publisher of result
     */
    <R> Publisher<R> publish(Command<R> command);

    /**
     * Subscribes reaction to the command class it observes. The reaction will be notified when instances of
     * the command type are published.
     *
     * @param reaction     reaction instance
     * @param commandClass command class to subscribe to
     * @param <C>          command type
     */
    <C extends Command<?>> void subscribe(Reaction<C, ?> reaction, Class<C> commandClass);

    /**
     * Unsubscribe reaction to the command type it observes. The reaction will no longer be notified when instances of
     * the command type are published.
     *
     * @param reaction reaction instance
     */
    void unsubscribe(Reaction<?, ?> reaction);

}
