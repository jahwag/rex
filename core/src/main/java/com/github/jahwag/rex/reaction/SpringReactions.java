package com.github.jahwag.rex.reaction;

import com.github.jahwag.rex.command.Command;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Repository for instances of {@link Reaction} found through Spring context.
 */
@Named
final class SpringReactions implements Reactions {

    private final Reactions delegate;

    @Inject
    SpringReactions(List<Reaction<?, ?>> reactions) {
        delegate = InMemoryReactions.of(reactions);
    }

    @Override
    public <C extends Command<?>> void subscribe(Reaction<C, ?> reaction, Class<C> commandClass) {
        delegate.subscribe(reaction, commandClass);
    }

    @Override
    public Reaction findBy(Command<?> command) {
        return delegate.findBy(command);
    }

    @Override
    public void unsubscribe(Reaction<?, ?> reaction) {
        delegate.unsubscribe(reaction);
    }
}
