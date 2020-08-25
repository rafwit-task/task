Feature: Get rates

    Scenario Outline: User submit GET Method to service to get a rates
        Given An endpoint exist "<URL>"
        When User set header content type as "<ContentType>"
        When User submit the "<RequestMethod>" request
        Then User should get a response code "<StatusCode>"
        And List of rates should not be empty
        Examples:
            |URL                                | ContentType      | RequestMethod | StatusCode |
            |https://api.ratesapi.io/api/latest | application/json | GET           | 200        |

