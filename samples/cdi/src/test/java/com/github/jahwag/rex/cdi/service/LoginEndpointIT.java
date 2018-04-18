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

/**
 * Verifies that CDI compliance is maintained.
 */
public class LoginEndpointIT extends AbstractIT {

    public static final String API_URL = "http://localhost:8080/login";

    @Test
    @RunAsClient
    public void login() throws ExecutionException, InterruptedException {
        asUser("user", "Password");
        asUser("admin", "Password");
    }

    private void asUser(String username, String password) throws InterruptedException, ExecutionException {
        String userPassword = username + ":" + password;
        String authValue = "Basic " + Base64.getEncoder()
                                            .encodeToString(userPassword.getBytes());

        Future<Response> future = ClientBuilder.newBuilder()
                                               .build()
                                               .target(API_URL)
                                               .request()
                                               .header(HttpHeaders.AUTHORIZATION, authValue)
                                               .async()
                                               .get();

        Response response = future.get();
        assertEquals(Response.Status.OK.getReasonPhrase(), response.getStatusInfo()
                                                                   .getReasonPhrase());
        String msg = response.readEntity(String.class);
        assertEquals("Hello " + username + ", this is your greeting # 1!", msg);
    }

}