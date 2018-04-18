package com.github.jahwag.rex.cdi.command;

import com.github.jahwag.rex.reactor.command.ReactorCommand;
import lombok.Value;
import lombok.experimental.Accessors;

@Value(staticConstructor = "of")
@Accessors(fluent = true)
public class RevokeRole implements ReactorCommand<Void> {

    private final String username;

    private final String role;

}
