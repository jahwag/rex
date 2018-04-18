package com.github.jahwag.rex.reactor;

import com.github.jahwag.rex.command.Command;
import com.github.jahwag.rex.reaction.Reaction;
import com.github.jahwag.rex.reaction.Reactions;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
@RequiredArgsConstructor(onConstructor = @__(@Inject))
final class ReactorRexImpl implements ReactorRex {

    private final Reactions reactions;

    @Override
    public <R> Flux<R> publish(Command<R> command) {
        Reaction reaction = reactions.findBy(command);
        return Flux.from(reaction.apply(command));
    }

    @Override
    public <C extends Command<?>> void subscribe(Reaction<C, ?> reaction, Class<C> commandClass) {
        reactions.subscribe(reaction, commandClass);
    }

    @Override
    public void unsubscribe(Reaction<?, ?> reaction) {
        reactions.unsubscribe(reaction);
    }
}