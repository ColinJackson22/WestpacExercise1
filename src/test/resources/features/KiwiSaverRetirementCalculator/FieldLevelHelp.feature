Feature: Field-level Help

  Description: Tests the field level help for the KiwiSaver Retirement Calculator controls

  # TODO: Add checks for more info controls as required
  # I've added a couple of examples just to prove the scenario outline works
  Scenario Outline: KiwiSaver Retirement Calculator controls have appropriate field-level help
    Given I am on the "KiwSaver Retirement Calculator" page
    When I click in the information icon for the "<control>" control
    Then I see the information "<message>" displayed for the "<control>" control

    Examples:
      | control                   | message                                                                                                   |
      | Current age               | This calculator has an age limit of 64 years old as you need to be under the age of 65 to join KiwiSaver. |
      | Current KiwiSaver balance | If you do not have a KiwiSaver account, then leave this field blank.                                      |
