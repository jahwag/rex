package com.github.jahwag.rex.cdi.service;

import com.github.jahwag.rex.cdi.command.Login;
import com.github.jahwag.rex.reactor.ReactorRex;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import java.security.Principal;

@ApplicationScoped
@Path("/login")
public class LoginEndpoint {

    @Inject
    Principal principal;

    @Inject
    ReactorRex rex;

    @GET
    @RolesAllowed({"user", "admin"})
    public void login(@Suspended AsyncResponse response) {
        Login.of(principal)
             .publish(rex)
             .subscribe(response::resume, response::resume);
    }
}