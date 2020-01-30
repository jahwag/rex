package com.github.jahwag.rex.reaction.repository.cdi;

import com.github.jahwag.rex.command.Command;
import com.github.jahwag.rex.reaction.Reaction;
import com.github.jahwag.rex.reaction.repository.InMemoryReactions;
import com.github.jahwag.rex.reaction.repository.Reactions;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

/**
 * Repository for instances of {@link Reaction} found through CDI context.
 */
@ApplicationScoped
final class CDIReactions implements Reactions {

    private final Reactions delegate;

    @Inject
    CDIReactions(@Any Instance<Reaction<?, ?>> anyReaction) {
        delegate = InMemoryReactions.of(anyReaction);
    }

    @Override
    public <C extends Command<?>> void subscribe(Reaction<C, ?> reaction, Class<C> commandClass) {
        delegate.subscribe(reaction, commandClass);
    }

    @Override
    public <T> Reaction<Command<T>, T> findBy(Command<T> command) {
        return delegate.findBy(command);
    }

    @Override
    public void unsubscribe(Reaction<?, ?> reaction) {
        delegate.unsubscribe(reaction);
    }
}