package com.github.jahwag.rex.reaction;

import com.github.jahwag.rex.annotations.AutoSubscribe;
import com.github.jahwag.rex.command.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class InMemoryReactions implements Reactions {

    private final Map<Class<? extends Command>, Reaction> reactionMap;

    public InMemoryReactions() {
        reactionMap = new HashMap<>();
    }

    public InMemoryReactions(Map<Class<? extends Command>, Reaction> reactionMap) {
        this.reactionMap = reactionMap;
    }

    public static InMemoryReactions of(Iterable<? extends Reaction> reactions) {
        Map<Class<? extends Command>, Reaction> map = new HashMap<>();

        for (Reaction reaction : reactions) {
            Class<? extends Reaction> reactionClass = reaction.getClass();
            Optional<AutoSubscribe> autoSubscribe = Optional.ofNullable(reactionClass
                    .getAnnotation(AutoSubscribe.class));
            autoSubscribe.ifPresent(annotation -> map.put(annotation.value(), reaction));
        }

        return new InMemoryReactions(map);
    }

    @Override
    public <C extends Command<?>> void subscribe(Reaction<C, ?> reaction, Class<C> commandClass) {
        reactionMap.put(commandClass, reaction);
    }

    @Override
    public Reaction findBy(Command command) {
        return reactionMap.getOrDefault(command.getClass(), Reaction.none());
    }

    @Override
    public void unsubscribe(Reaction<?, ?> reaction) {
        reactionMap.remove(reaction);
    }

}