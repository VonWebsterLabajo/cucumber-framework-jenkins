@validateCart
Feature: Validate Cart

  Scenario: Validate added product
    Given User navigate to webshop login page
      | strategyType | locatorTag | message |
      | text         | a          | Log in  |
    When User should input valid login credentials
      | username             | password   |
      | cicdtest01@gmail.com | cicdtest01 |
    And Verify successfull login
    And User should click on Books category
      | category |
      | Books    |
    And User should locate Fiction book
      | bookTitle |
      | Fiction   |
    And User should locate the price
    And User should click add to cart
    And User should click on the shopping cart link
    And User should locate the remove checkbox
    And User should click the remove checkbox and update the cart
      | bookTitle |
      | Fiction   |
    Then User should click logout
