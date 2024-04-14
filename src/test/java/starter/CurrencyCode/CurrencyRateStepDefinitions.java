package starter.CurrencyCode;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import net.serenitybdd.rest.Ensure;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static net.serenitybdd.rest.SerenityRest.expect;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.equalTo;

public class CurrencyRateStepDefinitions {

    @Steps
    CurrrencyRate currrencyRate;

    @When("I Fetch the Rate for Code {}")
    public void lookUpAPostCode(String Code) {
        currrencyRate.fetchLocationByPostCodeAndCountry(Code);
    }

    @Then("I Verify the Rate {} for Currency {}")
    public void theResultingLocationShouldBe(String Rate, String Currency) {
        restAssuredThat(response -> response.body(String.format(CurrencyRateResponse.RATE,Currency), equalTo(Float.parseFloat(Rate))));

    }


    @Then("I Verify the Response code {int} and Message {}")
    public void i_verify_the_response_code_and_message_success(Integer statusCode,String message) {
        restAssuredThat(response -> response.statusCode(statusCode));
        restAssuredThat(response -> response.body(CurrencyRateResponse.Message, equalTo(message)));
    }

    @Then("I Verify the Currency rate for Currency {} between {} to {}")
    public void i_verify_the_currency_rate_for_currency_(String Currency,String rateLowerLimit, String rateUpperLimit) {
        JsonPath jp = new JsonPath(SerenityRest.lastResponse().asString());
        Float rate=jp.get(String.format(CurrencyRateResponse.RATE,Currency));
        Assert.assertTrue(rate>=Float.parseFloat(rateLowerLimit) && rate<=Float.parseFloat(rateUpperLimit)); ;
    }

    @Then("I Verify the Response contains {int} Data")
    public void i_verify_the_response_contains_data(Integer RateCount) {
        JsonPath jp = new JsonPath(SerenityRest.lastResponse().asString());
        String value = jp.get(CurrencyRateResponse.RateCount).toString();
        int count = value.split(",").length;
        Assert.assertTrue(count ==RateCount);


    }

    @Then("I Verify Response time is Less than {int} Seconds")
    public void i_verify_response_time_is_less_than_seconds(Integer timeOut){
        long time =SerenityRest.lastResponse().getTimeIn(TimeUnit.SECONDS);
        Assert.assertTrue(time<timeOut);
    }


}
