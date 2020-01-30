package com.github.jahwag.rex.reactor.service;

import com.github.jahwag.rex.command.Command;
import com.github.jahwag.rex.reaction.repository.InMemoryReactions;
import com.github.jahwag.rex.service.Rex;
import reactor.core.publisher.Flux;

/**
 * Rex implementation for Reactor.
 *
 * @see Rex
 */
public interface ReactorRex extends Rex {

    /**
     * Returns a new instance of ReactorRex.
     *
     * @return new instance.
     */
    static ReactorRex create() {
        return new ReactorRexImpl(new InMemoryReactions());
    }

    @Override
    <R> Flux<R> publish(Command<R> command);

}
