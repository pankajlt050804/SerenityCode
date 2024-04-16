Feature: This Feature file check the Rates against Currency Code

  @testType=valid
  Scenario Outline: Verify the Currency Rate
    When I Call the exchange API to fetch the Rate for Code <Code>
    Then I Verify API Call is successful with Status Code <StatusCode> and Message <Message>
    Then I Fetch the USD price against Currency <Currency> matched with expected Rate <Rate>
    Then I Verify the Currency rate for Currency <Currency> between <RateLowerLimit> to <RateUpperLimit>
    Examples:
      | Code | Currency | Rate    | Message | StatusCode | RateLowerLimit | RateUpperLimit |
      | USD  | AED      | 3.6725F | success | 200        | 3.6F           | 3.7F           |

  @testType=invalid
  Scenario Outline: Verify Invalid Response when Pass invalid Currency
    When I Call the exchange API to fetch the Rate for Code <Code>
    Then I Verify API Call is successful with Status Code <StatusCode> and Message <Message>
    Examples:
      | Message | StatusCode | Code |
      | error   | 200        | US   |
      | error   | 404        | US   |

  @testType=valid
  Scenario Outline: Verify Rate Count
    When I Call the exchange API to fetch the Rate for Code <Code>
    Then I Verify API Call is successful with Status Code <StatusCode> and Message <Message>
    And I Verify the Response contains <RateCount> rates
    And I Verify Response time is Less than <APITimeOut> Seconds
    Then I Verify the JSON Schema of the Rate API
    Examples:
      | Code | RateCount | Message | StatusCode | APITimeOut |
      | USD  | 162       | success | 200        | 3          |



