package com.github.jahwag.rex.reactor.command;

import com.github.jahwag.rex.Rex;
import com.github.jahwag.rex.command.Command;
import reactor.core.publisher.Flux;

/**
 * AutoSubscribe that uses Project Reactor 3.
 *
 * @see Command
 */
public interface ReactorCommand<R> extends Command<R> {

    @Override
    default Flux<R> publish(Rex rex) {
        return Flux.from(rex
                .publish(this));
    }

}
