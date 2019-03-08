Feature:Value Feature
  Validate the all the values for this exercises based on the objective.


  Scenario: all the elements in this website are working as expected
    Given the site is opened with chrome
    When verify the total number of value
    And verify all value are greater than zero
    And verify values are formatted as currencies
    And verify total value equals to sum of values
