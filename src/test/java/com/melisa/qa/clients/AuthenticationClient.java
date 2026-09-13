package com.melisa.qa.clients;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthenticationClient {

    private static final String BASE_URL = "https://restful-booker.herokuapp.com";

    /**
     * Authenticates a user with the provided username and password.
     *
     * @param username the username of the user
     * @param password the password of the user
     * @return API response containing the authentication token
     */
    public Response authenticate(String username, String password) {

        return given()
                .baseUri(BASE_URL)
                .contentType("application/json")
                .body("""
                        {
                            "username": "%s",
                            "password": "%s"
                        }
                        """.formatted(username, password))
                .when()
                .post("/auth");
    }
}