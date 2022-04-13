Feature: Notes controller testing

  Scenario: Fetching notes fom database
    Given there are 10 random notes in database
    When user is fetching all notes
    Then fetched notes count is 10

    Scenario: Fetching note by id
      Given there are notes in database
      And random note id is selected
      When user fetches note with selected id
      Then received data matches selected note