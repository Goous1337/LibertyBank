package api.insuranceService;

import api.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static constant.InsuranceServiceConstants.COUNT_POPULAR_INSURANCE_PRODUCTS;
import static constant.Message.METHOD_NOT_ALLOWED;
import static org.apache.hc.core5.http.HttpStatus.SC_METHOD_NOT_ALLOWED;
import static org.apache.hc.core5.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.INSURANCE_POLICY_SERVICE;


@DisplayName("InS-6 Получение популярных продуктов страхования")
public class INS_6_GetPopularInsuranceProducts extends BaseTest {

    {
        RestAssured.baseURI = INSURANCE_POLICY_SERVICE;
    }

    @DisplayName("Получение данных популярных продуктов банка")
    @Description("Тест направлен на проверку получения информации о популярных продуктах банка")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB5-476")
    @Test()
    public void getPopularInsuranceProducts() {
        String jsonSchemaPath = "schemas/insuranceService/successfullyGetPopularInsuranceProducts.json";
        Response response = insuranceService.getPopularInsuranceProducts(Method.GET);
        assertAll(
                () -> assertEquals(SC_OK, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                () -> assertEquals(COUNT_POPULAR_INSURANCE_PRODUCTS, insuranceService.getResponseCountPopularInsuranceProducts(response)),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }


    @DisplayName("Получение данных популярных продуктов банка")
    @Description("Тест направлен на проверку получения информации о популярных продуктах банка")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB5-476")
    @Test()
    public void getPopularInsuranceProductsByPostRequest() {
        String jsonSchemaPath = "schemas/insuranceService/unableGetPopularProductByPostRequest.json";
        Response response = insuranceService.getPopularInsuranceProducts(Method.POST);
        assertAll(
                () -> assertEquals(SC_METHOD_NOT_ALLOWED, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                () -> assertEquals(METHOD_NOT_ALLOWED, insuranceService.getResponsePolicyErrorMessage(response, "error")),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }
}
