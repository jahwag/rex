package com.github.jahwag.rex.reactor;

import com.github.jahwag.rex.Rex;
import com.github.jahwag.rex.command.Command;
import com.github.jahwag.rex.reaction.InMemoryReactions;
import reactor.core.publisher.Flux;

/**
 * A service for publishing and subscribing to commands using the reactive streams implementation Project Reactor 3 .
 *
 * @see Rex
 * @see <a href="https://projectreactor.io">Project Reactor</a>
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
