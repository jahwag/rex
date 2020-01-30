package com.github.jahwag.rex.reactor.service;

import com.github.jahwag.rex.command.Command;
import com.github.jahwag.rex.reaction.Reaction;
import com.github.jahwag.rex.reaction.repository.Reactions;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import reactor.core.publisher.Flux;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Log
@Named
@ApplicationScoped
@RequiredArgsConstructor(onConstructor = @__(@Inject))
final class ReactorRexImpl implements ReactorRex {

    private final Reactions reactions;

    @Override
    public <R> Flux<R> publish(Command<R> command) {
        Reaction<Command<R>, R> reaction = reactions.findBy(command);
        log.info(String.format("Got %s, applying %s", command.getClass()
                                                             .getSimpleName(), reaction.getClass()
                                                                                       .getSimpleName()));
        return Flux.from(reaction.apply(command));
    }

    @Override
    public <C extends Command<?>> void register(Reaction<C, ?> reaction, Class<C> commandClass) {
        reactions.subscribe(reaction, commandClass);
    }

    @Override
    public void unregister(Reaction<?, ?> reaction) {
        reactions.unsubscribe(reaction);
    }
}