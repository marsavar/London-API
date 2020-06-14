# London-API

An API that calls https://bpdts-test-app.herokuapp.com/ and returns people who are listed as either living in London, or whose current coordinates are within 50 miles of London.

Built with Maven and Spring Boot.

## GET methods

The API supports the following `GET` requests:

GET request | Returns
------------|--------
`/London`| a Json-formatted list of users who are listed as living in London.
`/FiftyMiles` | a Json-formatted list of users whose current coordinates are within 50 miles of London.
`/LondonOrFiftyMiles` | a Json-formatted list resulting from the combination of the two requests above.

To install, simply run
`TBC`
