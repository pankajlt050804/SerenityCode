package starter.currencyCode;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class CurrencyRate {

    private static String CurrencyUSDRate = "https://open.er-api.com/v6/latest/%s";

    @Step("Get location by postcode {0} in country {1}")
    public void fetchUSDRate(String Code) {
        SerenityRest.given()
                .get(String.format(CurrencyUSDRate,Code));
    }
}
