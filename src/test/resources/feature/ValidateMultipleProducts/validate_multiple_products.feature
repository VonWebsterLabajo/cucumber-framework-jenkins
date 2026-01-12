@validateMultipleProducts
Feature: Validate Multiple Products Functionality

  Scenario: Multiple Products
    Given User navigate to webshop
    When User should search for a product and click add to cart
      | keyword | item             | no |
      | laptop  | 14.1-inch Laptop | 31 |
      | book    | Health Book      | 22 |
      | belt    | Casual Golf Belt | 40 |
    And User should click the shopping cart
    Then Verify added products
      | item             | price   |
      | 14.1-inch Laptop | 1590.00 |
      | Health Book      |   10.00 |
      | Casual Golf Belt |    1.00 |
