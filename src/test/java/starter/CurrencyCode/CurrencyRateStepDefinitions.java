package starter.currencyCode;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import java.io.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.InputStream;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;

public class CurrencyRateStepDefinitions {

    @Steps
    CurrencyRate currencyRate;

    @When("I Call the exchange API to fetch the Rate for Code {}")
    public void fetchCurrencyRate(String Code) {
        currencyRate.fetchUSDRate(Code);
    }

    @Then("I Fetch the USD price against Currency {} matched with expected Rate {}")
    public void theResultingLocationShouldBe(String Currency, String Rate) {
        restAssuredThat(response -> response.body(String.format(CurrencyRateResponse.RATE, Currency), equalTo(Float.parseFloat(Rate))));

    }


    @Then("I Verify API Call is successful with Status Code {int} and Message {}")
    public void i_verify_the_response_code_and_message_success(Integer statusCode, String message) {
        restAssuredThat(response -> response.statusCode(statusCode));
        restAssuredThat(response -> response.body(CurrencyRateResponse.Message, equalTo(message)));
    }

    @Then("I Verify the Currency rate for Currency {} between {} to {}")
    public void i_verify_the_currency_rate_for_currency_(String Currency, String rateLowerLimit, String rateUpperLimit) {
        JsonPath jp = new JsonPath(SerenityRest.lastResponse().asString());
        Float rate = jp.get(String.format(CurrencyRateResponse.RATE, Currency));
        Assert.assertTrue(rate >= Float.parseFloat(rateLowerLimit) && rate <= Float.parseFloat(rateUpperLimit));
        ;
    }

    @Then("I Verify the Response contains {int} rates")
    public void i_verify_the_response_contains_data(Integer RateCount) {
        JsonPath jp = new JsonPath(SerenityRest.lastResponse().asString());
        String value = jp.get(CurrencyRateResponse.RateCount).toString();
        int count = value.split(",").length;
        Assert.assertTrue(count == RateCount);


    }

    @Then("I Verify Response time is Less than {int} Seconds")
    public void i_verify_response_time_is_less_than_seconds(Integer timeOut) {
        long time = SerenityRest.lastResponse().getTimeIn(TimeUnit.SECONDS);
        Assert.assertTrue(time < timeOut);
    }

    @Then("I Verify the JSON Schema of the Rate API")
    public void i_verify_the_json_schema_of_the_rate_api() {
        ObjectMapper objectMapper = new ObjectMapper();

        // create an instance of the JsonSchemaFactory using version flag
        JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V201909);
        String pat = System.getProperty("user.dir");
        // store the JSON data in InputStream
        File file= new File(System.getProperty("user.dir")+"\\src\\main\\java\\DataModel\\CurrrencyData.json");
        try  {
            InputStream jsonStream = new FileInputStream(file);
            file= new File(System.getProperty("user.dir")+"\\src\\main\\java\\DataModel\\CurrencyRateSchema.json");
            InputStream schemaStream = new FileInputStream(file);
            // read data from the stream and store it into JsonNode
            JsonNode json = null;
            try {
                json = objectMapper.readTree(jsonStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // get schema from the schemaStream and store it into JsonSchema
            JsonSchema schema = schemaFactory.getSchema(schemaStream);

            // create set of validation message and store result in it
            Set<ValidationMessage> validationResult = schema.validate(json);

            // show the validation errors
            if (validationResult.isEmpty()) {

                // show custom message if there is no validation error
                System.out.println("There is no validation errors");

            } else {

                // show all the validation error
                validationResult.forEach(vm -> System.out.println(vm.getMessage()));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static InputStream inputStreamFromClasspath(String path) {

        // returning stream
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
    }

}
