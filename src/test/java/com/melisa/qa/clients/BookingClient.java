package com.melisa.qa.clients;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import com.melisa.qa.models.BookingRequest;

public class BookingClient {

    private static final String BASE_URL = "https://restful-booker.herokuapp.com";

    /**
     * Creates a new booking using the provided booking information.
     *
     * @param bookingRequest booking data to be sent to the API
     * @return API response containing the created booking
     */
    public Response createBooking(BookingRequest bookingRequest) {

        return given()
                .baseUri(BASE_URL)
                .contentType("application/json")
                .body(bookingRequest)
                .when()
                .post("/booking");
    }

    /**
     * Retrieves the booking information for the specified booking ID.
     *
     * @param bookingId ID of the booking to be retrieved
     * @return API response containing the booking information
     */
    public Response getBooking(int bookingId) {

        return given()
                .baseUri(BASE_URL)
                .when()
                .get("/booking/" + bookingId);
    }

    /**
     * Updates the booking information for the specified booking ID using the provided booking data and authentication token.
     *
     * @param bookingId ID of the booking to be updated
     * @param bookingRequest updated booking data to be sent to the API
     * @param token authentication token for the API
     * @return API response containing the updated booking information
     */
    public Response updateBooking(int bookingId, BookingRequest bookingRequest, String token) {

        return given()
                .baseUri(BASE_URL)
                .contentType("application/json")
                .cookie("token", token)
                .body(bookingRequest)
                .when()
                .put("/booking/" + bookingId);
    }

    /**
     * Attempts to update a booking without authentication.
     *
     * @param bookingId ID of the booking to update
     * @param bookingRequest booking data to be sent to the API
     * @return API response
     */
    public Response updateBookingWithoutAuthentication(
        int bookingId,
        BookingRequest bookingRequest) {

        return given()
                .baseUri(BASE_URL)
                .contentType("application/json")
                .body(bookingRequest)
                .when()
                .put("/booking/" + bookingId);
    }

    /**
     * Deletes the booking for the specified booking ID using the provided authentication token.
     *
     * @param bookingId ID of the booking to be deleted
     * @param token authentication token for the API
     * @return API response indicating the result of the delete operation
     */
    public Response deleteBooking(int bookingId, String token) {

        return given()
                .baseUri(BASE_URL)
                .cookie("token", token)
                .when()
                .delete("/booking/" + bookingId);
    }

    /**
     * Attempts to delete a booking without authentication.
     *
     * @param bookingId ID of the booking to delete
     * @return API response
     */
    public Response deleteBookingWithoutAuthentication(int bookingId) {

        return given()
                .baseUri(BASE_URL)
                .when()
                .delete("/booking/" + bookingId);
    }

    /**
     * Partially updates the booking for the specified booking ID using the provided authentication token.
     *
     * @param bookingId ID of the booking to update
     * @param firstName new first name to be updated
     * @param token authentication token for the API
     * @return API response indicating the result of the patch operation
     */
    public Response patchBooking(
        int bookingId,
        String firstName,
        String token) {

        return given()
                .baseUri(BASE_URL)
                .contentType("application/json")
                .cookie("token", token)
                .body("""
                        {
                            "firstname": "%s"
                        }
                        """.formatted(firstName))
                .when()
                .patch("/booking/" + bookingId);
    }

}