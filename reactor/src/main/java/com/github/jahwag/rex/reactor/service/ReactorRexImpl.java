package com.github.jahwag.rex.reactor.service;

import com.github.jahwag.rex.command.Command;
import com.github.jahwag.rex.reaction.Reaction;
import com.github.jahwag.rex.reaction.repository.Reactions;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.reactivestreams.Publisher;
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
    public <R> Flux<R> prepare(Command<R> command) {
        return Flux.just(command)
                   .flatMap(this::reactionFor);
    }

    private <R> Publisher<? extends R> reactionFor(Command<R> command) {
        Reaction<Command<R>, R> by = reactions.findBy(command);
        return by.apply(command);
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