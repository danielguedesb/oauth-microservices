package com.baeldung.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

@Ignore
public class ResourceServer2LiveTest {

    @Test
    public void givenUserWithNoAdminAccess_whenConsumingThirdOperation_thenForbidden() {
        final String accessToken = obtainAccessTokenViaPasswordGrant("john", "123");

        final Response resourceServerResponse = RestAssured.given().header("Authorization", "Bearer " + accessToken).get("http://localhost:8083/resource-server-rest-2/third/100");
        assertThat(resourceServerResponse.getStatusCode(), equalTo(403));
    }

    //

    private String obtainAccessTokenViaPasswordGrant(final String username, final String password) {
        final Response authServerResponse = obtainAccessTokenViaPasswordGrantRaw("fooClientId", "secret", username, password);
        final String accessToken = authServerResponse.jsonPath().getString("access_token");
        return accessToken;
    }

    private Response obtainAccessTokenViaPasswordGrantRaw(final String clientId, final String clientSecret, final String username, final String password) {
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