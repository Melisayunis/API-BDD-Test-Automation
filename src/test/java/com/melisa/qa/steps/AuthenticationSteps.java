package com.melisa.qa.steps;

import com.melisa.qa.clients.AuthenticationClient;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AuthenticationSteps {

    private final AuthenticationClient authenticationClient = new AuthenticationClient();

    private String username;
    private String password;
    private Response response;

    @Given("the user has valid authentication credentials")
    public void theUserHasValidAuthenticationCredentials() {

        username = "admin";
        password = "password123";
    }

    @When("the user sends an authentication request")
    public void theUserSendsAnAuthenticationRequest() {

        response = authenticationClient.authenticate(username, password);
    }

    @Then("the authentication request should be successful")
    public void theAuthenticationRequestShouldBeSuccessful() {

        assertEquals(200, response.statusCode());
    }

    @Then("an authentication token should be returned")
    public void anAuthenticationTokenShouldBeReturned() {

        String token = response.jsonPath().getString("token");

        assertNotNull(token);
    }

    @Given("the user has authentication credentials {string} and {string}")
    public void theUserHasAuthenticationCredentials(String username, String password) {

        this.username = username;
        this.password = password;
    }

    @Then("the authentication response should match {string}")
    public void theAuthenticationResponseShouldMatch(String expected) {

        if (expected.equals("success")) {

            assertEquals(200, response.statusCode());

            String token = response.jsonPath().getString("token");

            assertNotNull(token);

        } else {

            assertEquals(200, response.statusCode());

            String token = response.jsonPath().getString("token");

            assertNull(token);
        }
    }

    
}