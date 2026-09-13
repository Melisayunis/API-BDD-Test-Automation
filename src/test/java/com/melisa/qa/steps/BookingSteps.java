package com.melisa.qa.steps;

import com.melisa.qa.clients.AuthenticationClient;
import com.melisa.qa.clients.BookingClient;
import com.melisa.qa.models.BookingRequest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookingSteps {

    private final BookingClient bookingClient = new BookingClient();
    private final AuthenticationClient authenticationClient = new AuthenticationClient();

    private BookingRequest originalBooking;
    private BookingRequest updatedBooking;

    private String token;
    private Response response;
    private int bookingId;

    // Given

    @Given("the user has valid booking information")
    public void theUserHasValidBookingInformation() {

        BookingRequest.BookingDates bookingDates =
                new BookingRequest.BookingDates(
                        "2026-09-20",
                        "2026-09-25");

        originalBooking = new BookingRequest(
                "Melisa",
                "QA",
                150,
                true,
                bookingDates,
                "Breakfast");

        response = bookingClient.createBooking(originalBooking);

        bookingId = response.jsonPath().getInt("bookingid");
    }

    @Given("the user has invalid booking information")
    public void theUserHasInvalidBookingInformation() {

        BookingRequest.BookingDates bookingDates =
                new BookingRequest.BookingDates(
                        "2026-09-20",
                        "2026-09-25");

        originalBooking = new BookingRequest(
                "Melisa",
                "QA",
                -100,
                true,
                bookingDates,
                "Breakfast");
    }

    @Given("the user has booking information with {string}, {string}, {string}, {string}, and {string}")
    public void theUserHasBookingInformation(
            String firstname,
            String lastname,
            String totalprice,
            String checkin,
            String checkout) {

        BookingRequest.BookingDates bookingDates =
                new BookingRequest.BookingDates(checkin, checkout);

        originalBooking = new BookingRequest(
                firstname,
                lastname,
                Integer.parseInt(totalprice),
                true,
                bookingDates,
                "Breakfast");
    }

    @Given("the user has booking information with first name {string}")
    public void theUserHasBookingInformationWithFirstName(
            String firstName) {

        BookingRequest.BookingDates bookingDates =
                new BookingRequest.BookingDates("2026-10-20", "2026-10-25");

        originalBooking = new BookingRequest(
                firstName,
                "LastLAst",
                88,
                true,
                bookingDates,
                "Flowers");
    }

    @Given("the user has booking information with last name {string}")
    public void theUserHasBookingInformationWithLastName(
            String lastName) {

        BookingRequest.BookingDates bookingDates =
                new BookingRequest.BookingDates("2026-08-22", "2026-10-05");

        originalBooking = new BookingRequest(
                "Testing",
                lastName,
                88,
                true,
                bookingDates,
                "Something");
    }

    @Given("the user has booking information with first name {string} and last name {string}")
    public void theUserHasBookingInformationWithFirstNameAndLastName(
            String firstName,
            String lastName) {

        BookingRequest.BookingDates bookingDates =
                new BookingRequest.BookingDates("2026-08-22", "2026-10-05");

        originalBooking = new BookingRequest(
                firstName,
                lastName,
                88,
                true,
                bookingDates,
                "Something");
    }

    @Given("the user has booking information with deposit paid {string}")
    public void theUserHasBookingInformationWithDepositPaid(
            String depositPaid) {

        BookingRequest.BookingDates bookingDates =
                new BookingRequest.BookingDates("2026-10-20", "2026-10-25");

        originalBooking = new BookingRequest(
                "First Name",
                "Last",
                88,
                Boolean.parseBoolean(depositPaid),
                bookingDates,
                "Rain");
    }

    @Given("the user has booking information with total price {int}")
    public void theUserHasBookingInformationWithTotalPrice(
            int totalPrice) {

        BookingRequest.BookingDates bookingDates =
                new BookingRequest.BookingDates("2026-10-20", "2026-11-25");

        originalBooking = new BookingRequest(
                "First",
                "Other Last Name",
                totalPrice,
                true,
                bookingDates,
                "Sun");
    }

    @Given("the user has booking information with additional needs {string}")
    public void theUserHasBookingInformationWithAdditionalNeeds(
            String additionalNeeds) {

        BookingRequest.BookingDates bookingDates =
                new BookingRequest.BookingDates("2026-10-30", "2026-12-25");

        originalBooking = new BookingRequest(
                "The name",
                "The Last",
                88,
                true,
                bookingDates,
                additionalNeeds);
    }

    // When

    @When("the user creates a new booking")
    public void theUserCreatesANewBooking() {

        response = bookingClient.createBooking(originalBooking);

        bookingId = response.jsonPath().getInt("bookingid");
    }

    @When("the user retrieves the created booking")
    public void theUserRetrievesTheCreatedBooking() {

        response = bookingClient.getBooking(bookingId);
    }

    @When("the user updates the created booking")
    public void theUserUpdatesTheCreatedBooking() {

        token = authenticationClient
                .authenticate("admin", "password123")
                .jsonPath()
                .getString("token");

        BookingRequest.BookingDates updatedBookingDates =
                new BookingRequest.BookingDates(
                        "2026-09-21",
                        "2026-09-26");

        updatedBooking = new BookingRequest(
                "Melissa",
                "Automation",
                200,
                false,
                updatedBookingDates,
                "Dinner");

        response = bookingClient.updateBooking(
                bookingId,
                updatedBooking,
                token);
    }

    @When("the user partially updates the booking first name")
    public void theUserPartiallyUpdatesTheBookingFirstName() {

        token = authenticationClient
                .authenticate("admin", "password123")
                .jsonPath()
                .getString("token");

        response = bookingClient.patchBooking(
                bookingId,
                "UpdatedFirstName",
                token);
    }

    @When("the user tries to update the booking without authentication")
    public void theUserTriesToUpdateTheBookingWithoutAuthentication() {

        BookingRequest.BookingDates updatedBookingDates =
                new BookingRequest.BookingDates(
                        "2026-09-22",
                        "2026-09-27");

        updatedBooking = new BookingRequest(
                "Melissa",
                "Automation",
                250,
                false,
                updatedBookingDates,
                "Dinner");

        response = bookingClient.updateBookingWithoutAuthentication(
                bookingId,
                updatedBooking);
    }

    @When("the user deletes the created booking")
    public void theUserDeletesTheCreatedBooking() {

        token = authenticationClient
                .authenticate("admin", "password123")
                .jsonPath()
                .getString("token");

        response = bookingClient.deleteBooking(
                bookingId,
                token);
    }

    @When("the user retrieves the deleted booking")
    public void theUserRetrievesTheDeletedBooking() {

        response = bookingClient.getBooking(bookingId);
    }

    @When("the user tries to delete the booking without authentication")
    public void theUserTriesToDeleteTheBookingWithoutAuthentication() {

        response = bookingClient.deleteBookingWithoutAuthentication(bookingId);
    }

    @When("the user retrieves a non-existing booking")
    public void theUserRetrievesANonExistingBooking() {

        response = bookingClient.getBooking(999999);
    }

    // Then

    @Then("the booking should be created successfully")
    public void theBookingShouldBeCreatedSuccessfully() {

        assertEquals(200, response.statusCode());
    }

    @Then("a booking ID should be returned")
    public void aBookingIdShouldBeReturned() {

        assertTrue(bookingId > 0);
    }

    @Then("the booking information should be retrieved successfully")
    public void theBookingInformationShouldBeRetrievedSuccessfully() {

        assertEquals(200, response.statusCode());

        assertEquals(
                originalBooking.getFirstname(),
                response.jsonPath().getString("firstname")
        );

        assertEquals(
                originalBooking.getLastname(),
                response.jsonPath().getString("lastname")
        );

        assertEquals(
                originalBooking.getTotalprice(),
                response.jsonPath().getInt("totalprice")
        );
    }

    @Then("the booking should be updated successfully")
    public void theBookingShouldBeUpdatedSuccessfully() {

        assertEquals(200, response.statusCode());

        assertEquals(
                updatedBooking.getFirstname(),
                response.jsonPath().getString("firstname")
        );

        assertEquals(
                updatedBooking.getLastname(),
                response.jsonPath().getString("lastname")
        );

        assertEquals(
                updatedBooking.getTotalprice(),
                response.jsonPath().getInt("totalprice")
        );

        assertEquals(
                updatedBooking.isDepositpaid(),
                response.jsonPath().getBoolean("depositpaid")
        );

        assertEquals(
                updatedBooking.getBookingdates().getCheckin(),
                response.jsonPath().getString("bookingdates.checkin")
        );

        assertEquals(
                updatedBooking.getBookingdates().getCheckout(),
                response.jsonPath().getString("bookingdates.checkout")
        );

        assertEquals(
                updatedBooking.getAdditionalneeds(),
                response.jsonPath().getString("additionalneeds")
        );
    }

    @Then("the booking should be partially updated successfully")
    public void theBookingShouldBePartiallyUpdatedSuccessfully() {

        assertEquals(200, response.statusCode());
    }

    @Then("the booking should be deleted successfully")
    public void theBookingShouldBeDeletedSuccessfully() {

        assertEquals(201, response.getStatusCode());
    }

    @Then("the booking should not be found")
    public void theBookingShouldNotBeFound() {

        assertEquals(404, response.getStatusCode());
    }

    @Then("the booking update should be rejected")
    public void theBookingUpdateShouldBeRejected() {

        assertTrue(
                response.getStatusCode() == 401 ||
                response.getStatusCode() == 403);
    }

    @Then("the booking deletion should be rejected")
    public void theBookingDeletionShouldBeRejected() {

        assertTrue(
                response.getStatusCode() == 401 ||
                response.getStatusCode() == 403);
    }

    @Then("the booking creation should be rejected")
    public void theBookingCreationShouldBeRejected() {

        System.out.println("Status code: " + response.getStatusCode());
        System.out.println("Response body: " + response.getBody().asString());

        assertTrue(response.getStatusCode() >= 400);
    }

    @Then("the booking last name should match {string}")
    public void theBookingLastNameShouldMatch(String expectedLastName) {

        String actualLastName = response.jsonPath().getString("booking.lastname");

        assertEquals(expectedLastName, actualLastName);
    }

    @Then("the booking first name should match {string}")
    public void theBookingFirstNameShouldMatch(String expectedFirstName) {

        String actualFirstName = response.jsonPath().getString("booking.firstname");

        assertEquals(expectedFirstName, actualFirstName);
    }

    @Then("the booking additional needs should match {string}")
    public void theBookingAdditionalNeedsShouldMatch(String expectedAdditionalNeeds) {

        String actualAdditionalNeeds = response.jsonPath().getString("booking.additionalneeds");

        assertEquals(expectedAdditionalNeeds, actualAdditionalNeeds);
    }

    @Then("the booking first name should match the updated value")
    public void theBookingFirstNameShouldMatchTheUpdatedValue() {

        String actualFirstName = response.jsonPath().getString("firstname");

        assertEquals("UpdatedFirstName", actualFirstName);
    }

    @Then("the booking total price should match {int}")
    public void theBookingTotalPriceShouldMatch(int expectedTotalPrice) {

        Integer actualTotalPrice = response.jsonPath().getInt("booking.totalprice");

        assertEquals(expectedTotalPrice, actualTotalPrice);
    }

    @Then("the booking deposit paid should match {string}")
    public void theBookingDepositPaidShouldMatch(String expectedDepositPaid) {

        boolean actualDepositPaid = response.jsonPath().getBoolean("booking.depositpaid");

        assertEquals(Boolean.parseBoolean(expectedDepositPaid), actualDepositPaid);
    }

    @Then("the booking should be rejected")
    public void theBookingShouldBeRejected() {

        assertTrue(
                response.getStatusCode() >= 400,
                "Expected the booking to be rejected, but received status code: "
                        + response.getStatusCode()
        );
    }

    @Then("the booking response should be validated")
    public void theBookingResponseShouldBeValidated() {

        assertEquals(200, response.getStatusCode());

        assertTrue(
                response.getStatusCode() >= 200 && response.getStatusCode() < 300,
                "Expected the booking to be successful, but received status code: "
                        + response.getStatusCode()
        );
    }

}