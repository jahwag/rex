package com.github.jahwag.rex.reaction;

import com.github.jahwag.rex.command.Command;
import org.reactivestreams.Publisher;

import java.util.function.Function;

/**
 * A reaction in response to a specific {@link Command}.
 *
 * @param <C> type of command that is associated with this Reaction.
 * @param <R> type of result that this Reaction will return.
 */
public interface Reaction<C extends Command<R>, R> extends Function<C, Publisher<R>> {

    static Reaction<Command<Void>, Void> none() {
        return None.INSTANCE;
    }

    @Override
    Publisher<R> apply(C command);

    /**
     * Null object.
     */
    final class None implements Reaction<Command<Void>, Void> {

        private static final None INSTANCE = new None();

        private None() {
        }

        @Override
        public Publisher<Void> apply(Command<Void> command) {
            throw new NoReactionFound(command.getClass());
        }
    }

}