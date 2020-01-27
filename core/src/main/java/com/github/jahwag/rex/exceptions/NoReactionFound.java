package com.github.jahwag.rex.exceptions;

import com.github.jahwag.rex.command.Command;

public final class NoReactionFound extends RuntimeException {

    public NoReactionFound(Class<? extends Command> commandType) {
        super("No reaction was found for command " + commandType);
    }

}
