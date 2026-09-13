Feature: Booking Management

  @booking
  Scenario: Create, retrieve and update a booking
    Given the user has valid booking information
    When the user creates a new booking
    Then the booking should be created successfully
    And a booking ID should be returned
    When the user retrieves the created booking
    Then the booking information should be retrieved successfully
    When the user updates the created booking
    Then the booking should be updated successfully
    When the user deletes the created booking
    Then the booking should be deleted successfully
    When the user retrieves the deleted booking
    Then the booking should not be found

  @booking @patch
  Scenario: Partially update a booking
    Given the user has valid booking information
    When the user partially updates the booking first name
    Then the booking should be partially updated successfully
    And the booking first name should match the updated value


  @booking @data-validation
  Scenario Outline: Create a booking with different first name values
    Given the user has booking information with first name "<firstname>"
    When the user creates a new booking
    Then the booking response should be validated
    And the booking first name should match "<firstname>"

  Examples:
    | firstname   |
    | lowercase   |
    | UPPERCASE   |
    | MeLiSa      |
    | Melisa QA   |
    | José        |
    | Anne-Marie  |
    | O'Connor    |
    | 123456      |
    | Melisa123   |
    | Melisa@QA   |
    | @#$%        |

  @booking @data-validation
  Scenario Outline: Create a booking with different last name values
    Given the user has booking information with last name "<lastname>"
    When the user creates a new booking
    Then the booking response should be validated
    And the booking last name should match "<lastname>"

Examples:
  | lastname        |
  | lowercase       |
  | UPPERCASE       |
  | mArTiNeZ        |
  | Van der Berg    |
  | García          |
  | Smith-Johnson   |
  | D'Angelo        |
  | !@#$%           |
  | Martin@123      |
  | 987654          |
  | Johnson456      |

  @booking @data-validation
  Scenario Outline: Create a booking with different deposit paid values
    Given the user has booking information with deposit paid "<depositpaid>"
    When the user creates a new booking
    Then the booking should be created successfully
    And the booking deposit paid should match "<depositpaid>"

  Examples:
    | depositpaid |
    | true        |
    | false       |

  @booking @data-validation
  Scenario Outline: Create a booking with different additional needs values
    Given the user has booking information with additional needs "<additionalneeds>"
    When the user creates a new booking
    Then the booking should be created successfully
    And the booking additional needs should match "<additionalneeds>"

  Examples:
    | additionalneeds |
    | Breakfast       |
    | Dinner          |
    | Airport transfer|
    | Late checkout   |
    | None            |
    |                 |
    | WiFi            |
    | José's request  |
    | Room #101     |

  @booking @data-validation
  Scenario Outline: Create a booking with different total price values
    Given the user has booking information with total price <totalprice>
    When the user creates a new booking
    Then the booking should be created successfully
    And the booking total price should match <totalprice>

  Examples:
    | totalprice |
    | 0          |
    | 1          |
    | 88         |
    | 999        |
    | 100000     |


  @booking @negative
  Scenario: Retrieve a non-existing booking
    When the user retrieves a non-existing booking
    Then the booking should not be found

  @booking @negative
  Scenario: Update a booking without authentication
    Given the user has valid booking information
    When the user creates a new booking
    And the user tries to update the booking without authentication
    Then the booking update should be rejected

  @booking @negative
  Scenario: Delete a booking without authentication
    Given the user has valid booking information
    When the user creates a new booking
    And the user tries to delete the booking without authentication
    Then the booking deletion should be rejected


  @booking @known-defect
  Scenario Outline: Reject a booking with invalid date information
    Given the user has booking information with "<firstname>", "<lastname>", "<totalprice>", "<checkin>", and "<checkout>"
    When the user creates a new booking
    Then the booking creation should be rejected

    Examples:
      | firstname | lastname | totalprice | checkin    | checkout   |
      | Melisa    | QA       | 150        | 2026-09-25 | 2026-09-20 |
      | Melisa    | QA       | 150        | 2026-12-31 | 2026-01-01 |

  @booking @known-defect
  Scenario Outline: Accepts invalid booking data due to missing validation
    Given the user has booking information with "<firstname>", "<lastname>", "<totalprice>", "<checkin>", and "<checkout>"
    When the user creates a new booking
    Then the booking creation should be rejected

    Examples:
      | firstname | lastname | totalprice | checkin    | checkout   |
      | Melisa    | QA       | -100       | 2026-09-20 | 2026-09-25 |

  @booking @known-defect
  Scenario Outline: Reject booking with invalid required information
    Given the user has booking information with first name "<firstname>" and last name "<lastname>"
    When the user creates a new booking
    Then the booking creation should be rejected

  Examples:
    | firstname | lastname |
    |           | QA       |
    | Melisa    |          |
    |           |          |
    |           |     |

