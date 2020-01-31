package com.github.jahwag.rex.command;

import com.github.jahwag.rex.service.Rex;
import org.reactivestreams.Publisher;

/**
 * Value object that is used to pass contextual information to a
 * typically stateless {@link com.github.jahwag.rex.reaction.Reaction}.
 *
 * @param <R> the result type
 */
public interface Command<R> {

    /**
     * @see Rex#prepare(Command)
     */
    default Publisher<R> publish(Rex rex) {
        return rex
                .prepare(this);
    }
}
