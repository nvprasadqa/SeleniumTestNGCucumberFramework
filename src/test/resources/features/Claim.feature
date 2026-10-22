Feature: Submit Claim

  @Login @Claim
  Scenario: Submit claim using json data
    When User go to claim tab
    And User submit claim from json data
    Then Claim should be submitted successfully