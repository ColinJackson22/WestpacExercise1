Feature: Calculations

  Description: Tests the calculations accuracy for the KiwiSaver Retirement Calculator

  Scenario Outline: KiwiSaver Retirement Calculator calculates an appropriate project balance
    Given I am on the "KiwSaver Retirement Calculator" page
    When I enter calculation parameters "<age>" "<employmentStatus>" "<currentSalary>" "<contribution>" "<volContrib>" "<volContribRate>" "<currentBalance>" "<pir>" "<savingGoalsRequirement>" "<profileType>"
    Then I see the project balance at retirement matches the expected value "<expectedProjectedBalance>"

    Examples:
      | age | employmentStatus  | currentSalary | contribution | volContrib | volContribRate | currentBalance   | pir   | savingGoalsRequirement  | profileType | expectedProjectedBalance |
      | 30  | Employed          | 82000         | 4            |            |                |                  | 17.5  |                         | high        | 313971                   |
      | 45  | Self-employed     |               |              | 90         | Fortnightly    | 100000           | 10.5  | 290000                  | medium      | 230481                   |
      | 55  | Not employed      |               |              | 90         | Fortnightly    | 140000           | 10.5  | 200000                  | medium      | 208574                   |
