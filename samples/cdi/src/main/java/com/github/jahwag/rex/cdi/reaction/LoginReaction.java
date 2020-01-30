package com.github.jahwag.rex.cdi.reaction;

import com.github.jahwag.rex.cdi.command.Login;
import com.github.jahwag.rex.reaction.AutoSubscribe;
import com.github.jahwag.rex.reaction.Reaction;
import reactor.core.publisher.Mono;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.spi.PassivationCapable;
import java.io.Serializable;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@AutoSubscribe(Login.class)
@SessionScoped
public class LoginReaction implements Reaction<Login, String>, PassivationCapable, Serializable {

    private final UUID uuid = UUID.randomUUID();

    private final AtomicInteger counter = new AtomicInteger(1);

    @Override
    public Mono<String> apply(Login command) {
        return Mono.just(String.format("Hello %s, this is your greeting # %d!", command.principal()
                                                                                       .getName(), counter.getAndIncrement()));
    }

    @Override
    public String getId() {
        return getClass().getName() + "." + uuid;
    }
}
