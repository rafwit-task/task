Feature: Get specific symbol rate

    Scenario Outline: User submit GET Method to service to get a specific symbol rate
        Given An URL "<URL>" and Symbol "<Symbol>" exists
        When User set header content type as "<ContentType>"
        When User submit the "<RequestMethod>" request
        Then User should get a response code "<StatusCode>"
        And User should get rate for "<Symbol>"
        Examples:
            |URL                                         | ContentType      | Symbol | RequestMethod | StatusCode |
            |https://api.ratesapi.io/api/latest?symbols= | application/json | PLN    | GET           | 200        |


    Scenario Outline: User submit GET Method to service with wrong symbol rate
        Given An URL "<URL>" and Symbol "<Symbol>" exists
        When User set header content type as "<ContentType>"
        When User submit the "<RequestMethod>" request
        Then User should get a response code "<StatusCode>"
        Examples:
            |URL                                         | ContentType      | Symbol | RequestMethod | StatusCode |
            |https://api.ratesapi.io/api/latest?symbols= | application/json | OOO    | GET           | 400        |

