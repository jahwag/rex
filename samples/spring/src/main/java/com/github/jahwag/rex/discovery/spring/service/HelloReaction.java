package com.github.jahwag.rex.discovery.spring.service;

import com.github.jahwag.rex.annotations.AutoSubscribe;
import com.github.jahwag.rex.reaction.Reaction;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@AutoSubscribe(HelloCommand.class)
@Component
public final class HelloReaction implements Reaction<HelloCommand, String> {

    @Override
    public Mono<String> apply(HelloCommand command) {
        return Mono.just("Hello, World!");
    }
}
