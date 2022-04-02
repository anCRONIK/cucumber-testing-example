# Cucumber testing example

Sample application with configured cucumber for UI and API testing.

Project is created as Spring Boot application used for exposing API.

Application is really simple, its purpose is to save user note. After user is signed in, user has option to see their note
or to create new one.
Also, user can edit and delete existing note.

# Security

API endpoints are secured with basic user authentication. For authentication, you can use default user(author):
- username: `user`
- password: `user`

# Storage
Application uses H2 in memory database and data is not persisted. On each startup application will populate database with some initial data.

# API documentation

TODO: swagger doc

## API Response format

All endpoint responses are in JSON format.

# Tests

By default, only unit test are running in maven lifecycle. Integration and acceptance tests are separated from that
lifecycle and are called using maven profiles.

All tests must have the __tag__ which is used as group by surefire and failsafe maven plugins.

## Unit tests

- in default maven `test` directory
- tagged with __UnitTest__
- follow maven naming convention (in this case, all test end with suffix _Test_)

## Integration tests

- in separate directory called `integration-test`
- they are using Docker, so they can be only run on machine with docker support
- follow maven naming convention (in this case, all test end with suffix _IT_)
- use test containers for any dependency
- run with `mvn clean verify -P integration-test`
- you can run test in IDEA the same way as unit tests

## Acceptance Tests

- in separate directory called `integration-test` under package `acceptance`
- Cucumber as testing framework
- run with `mvn clean verify -P acceptance-test -D...`
- to run them from IDEA you need to use `AcceptanceTestRunner` class and provide needed VM variables.

To run acceptance tests, you need to provide following system properties:

| property           | description                                                           |
|--------------------|-----------------------------------------------------------------------|
| __cassandra_host__ | ip or hostname of the cassandra instance that can be used for testing |
| __cassandra_port__ | port on which Cassandra instance is listening                         |
