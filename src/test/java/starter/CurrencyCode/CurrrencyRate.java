package starter.CurrencyCode;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class CurrrencyRate {

    private static String LOCATION_BY_POST_CODE_AND_COUNTRY = "https://open.er-api.com/v6/latest/%s";

    @Step("Get location by postcode {0} in country {1}")
    public void fetchLocationByPostCodeAndCountry(String Code) {
        SerenityRest.given()
                .get(String.format(LOCATION_BY_POST_CODE_AND_COUNTRY,Code));
    }
}
