package com.github.jahwag.rex.discovery.spring.service;

import com.github.jahwag.rex.reactor.service.ReactorRex;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/greet")
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public final class GreetingEndpoint {

    private final ReactorRex rex;

    @RequestMapping(value = "/async", method = RequestMethod.GET)
    public CompletableFuture<String> async() {
        return new HelloCommand().publish(rex)
                                 .next()
                                 .toFuture();
    }

    @Inject
    @RequestMapping(value = "/reactive", method = RequestMethod.GET)
    public Mono<String> reactive() {
        return new HelloCommand().publish(rex)
                                 .next();
    }

}