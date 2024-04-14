Feature: This Feature file check the Rates against Currency Code

  Scenario Outline: Verify the Currency Rate
    When I Fetch the Rate for Code <Code>
    Then I Verify the Response code <StatusCode> and Message <Message>
    Then I Verify the Rate <Rate> for Currency <Currency>
    Then I Verify the Currency rate for Currency <Currency> between <RateLowerLimit> to <RateUpperLimit>
    Examples:
      | Code | Currency | Rate    | Message | StatusCode | RateLowerLimit | RateUpperLimit |
      | USD  | AED      | 3.6725F | success | 200        | 3.6F           | 3.8F           |

  Scenario Outline: Verify Invalid Response when Pass invalid Currency
    When I Fetch the Rate for Code <Code>
    Then I Verify the Response code <StatusCode> and Message <Message>
    Examples:
      | Message | StatusCode | Code |
      | error   | 200        | US   |

  Scenario Outline: Verify Rate Count
    When I Fetch the Rate for Code <Code>
    Then I Verify the Response code <StatusCode> and Message <Message>
    And I Verify the Response contains <RateCount> Data
    And I Verify Response time is Less than 3 Seconds
    Examples:
      | Code | RateCount | Message | StatusCode |
      | USD  | 162       | success | 200        |



