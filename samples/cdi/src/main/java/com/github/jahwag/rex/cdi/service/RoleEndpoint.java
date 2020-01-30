package com.github.jahwag.rex.cdi.service;

import com.github.jahwag.rex.cdi.command.RevokeRole;
import com.github.jahwag.rex.reactor.service.ReactorRex;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/role")
public class RoleEndpoint {

    @Inject
    ReactorRex rex;

    @DELETE
    @Path("/{username}/{role}")
    @RolesAllowed("admin")
    public void revoke(@Suspended AsyncResponse response,
                       @PathParam("username") String username,
                       @PathParam("role") String role) {
        RevokeRole.of(username, role)
                  .publish(rex)
                  .subscribe(response::resume, response::resume, ok(response));
    }

    private Runnable ok(@Suspended AsyncResponse response) {
        return () -> response.resume(Response.ok());
    }

}
