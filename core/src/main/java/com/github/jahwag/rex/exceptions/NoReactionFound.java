package com.github.jahwag.rex.exceptions;

import com.github.jahwag.rex.command.Command;
import lombok.Value;
import lombok.experimental.Accessors;

@Value
@Accessors(fluent = true)
public final class NoReactionFound extends RuntimeException {

    private final Class<? extends Command> commandType;

    @Override
    public String getMessage() {
        return "No reaction was found for command " + commandType;
    }
}
