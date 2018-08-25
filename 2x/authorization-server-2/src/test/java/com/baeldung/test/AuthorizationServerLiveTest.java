package com.baeldung.test;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

@Ignore
public class AuthorizationServerLiveTest {

    @Test
    public void whenObtainingAccessToken_thenOK() {
        final Response authServerResponse = obtainAccessToken("fooClientId", "secret", "john", "123");
        final String accessToken = authServerResponse.jsonPath().getString("access_token");
        assertNotNull(accessToken);
    }

    //

    private Response obtainAccessToken(final String clientId, final String clientSecret, final String username, final String password) {
        final Map<String, String> params = new HashMap<String, String>();
        params.put("grant_type", "password");
        params.put("client_id", clientId);
        params.put("username", username);
        params.put("password", password);
        return RestAssured.given().auth().preemptive().basic(clientId, clientSecret).and().with().params(params).when().post("http://localhost:8081/authorization-server-2/oauth/token");
        // response.jsonPath().getString("refresh_token");
        // response.jsonPath().getString("access_token")
    }

}