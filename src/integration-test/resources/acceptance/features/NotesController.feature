Feature: Notes controller testing

  Scenario: Fetching notes fom database
    Given there are 10 random notes in database
    When user is fetching all notes
    Then fetched notes count is 10

    Scenario: Fetching first note from database
      Given at least 1 note exists in database
      When user fetches first note
      Then fetched notes count is 1