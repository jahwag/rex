package com.github.jahwag.rex.exceptions;

import com.github.jahwag.rex.command.Command;
import com.github.jahwag.rex.reaction.Reaction;
import lombok.Value;
import lombok.experimental.Accessors;

@Value
@Accessors(fluent = true)
public final class DuplicateReaction extends IllegalArgumentException {

    private final Class<? extends Command> commandType;

    private final Class<? extends Reaction> reactionType;

    @Override
    public String getMessage() {
        return "Reaction " + reactionType + " is a duplicate reaction for command " + commandType + " (duplicates not " +
                "allowed";
    }
}
