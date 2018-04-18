package com.github.jahwag.rex.cdi.service;

import org.jboss.arquillian.container.test.api.RunAsClient;
import org.junit.Test;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.Base64;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;

public class RoleEndpointIT extends AbstractIT {

    public static final String API_URL = "http://localhost:8080/role";

    @Test
    @RunAsClient
    public void revoke() throws ExecutionException, InterruptedException {
        String userPassword = "admin" + ":" + "Password";
        String authValue = "Basic " + Base64.getEncoder()
                                            .encodeToString(userPassword.getBytes());

        Future<Response> future = ClientBuilder.newBuilder()
                                               .build()
                                               .target(API_URL)
                                               .path("user/user")
                                               .request()
                                               .header(HttpHeaders.AUTHORIZATION, authValue)
                                               .async()
                                               .delete();

        Response response = future.get();

        assertEquals(Response.Status.OK.getReasonPhrase(), response.getStatusInfo()
                                                                   .getReasonPhrase());
    }
}