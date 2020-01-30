package com.github.jahwag.rex.reaction;

import com.github.jahwag.rex.command.Command;

public final class NoReactionFound extends RuntimeException {

    public <T extends Command<?>> NoReactionFound(Class<T> commandType) {
        super("No reaction was found for command " + commandType);
    }

}
