package com.github.jahwag.rex.reactor.command;

import com.github.jahwag.rex.command.Command;
import com.github.jahwag.rex.service.Rex;
import reactor.core.publisher.Flux;

/**
 * Command implementation for Reactor.
 *
 * @see Command
 */
public interface ReactorCommand<R> extends Command<R> {

    @Override
    default Flux<R> publish(Rex rex) {
        return Flux.from(rex
                .prepare(this));
    }

}
