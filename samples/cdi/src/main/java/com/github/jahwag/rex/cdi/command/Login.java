package com.github.jahwag.rex.cdi.command;

import com.github.jahwag.rex.reactor.command.ReactorCommand;
import lombok.Value;
import lombok.experimental.Accessors;

import java.security.Principal;

@Value(staticConstructor = "of")
@Accessors(fluent = true)
public final class Login implements ReactorCommand<String> {

    private final Principal principal;

}
