package api.insuranceService;

import api.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static constant.InsuranceServiceConstants.*;
import static org.apache.hc.core5.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.INSURANCE_POLICY_SERVICE;

@DisplayName("InS-9 Получение подробной информации о конкретном страховом полисе")
public class INS_9_GetInfoInsurancePolicy extends BaseTest {

    {
        RestAssured.baseURI = INSURANCE_POLICY_SERVICE;
    }

    @DisplayName("Получение подробной информации о конкретном страховом полисе домашнего имущества")
    @Description("Тест направлен на проверку получения информации о конкртеном страховом полисе домашнего имущества")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB5-2900")
    @Test()
    public void getThingInsurancePolicy() {
        String jsonSchemaPath = "schemas/insuranceService/successfullyGetThingInsurancePolicy.json";
        Response response = insuranceService.getPolicyInfo(POLICY_THING_ID);
        assertAll(
                () -> assertEquals(SC_OK, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                () -> assertEquals(THING_INSURANCE_SERVICE_PRODUCT_NAME, insuranceService.getResponsePolicyName(response)),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Получение подробной информации о конкретном страховом полисе КАСКО/ОСАГО")
    @Description("Тест направлен на проверку получения информации о конкртеном страховом полисе КАСКО/ОСАГО")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB5-484")
    @Test()
    public void getVehicleInsurancePolicy() {
        String jsonSchemaPath = "schemas/insuranceService/successfullyGetVehicleInsurancePolicy.json";
        Response response = insuranceService.getPolicyInfo(POLICY_VEHICLE_ID);
        assertAll(
                () -> assertEquals(SC_OK, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                () -> assertEquals(VEHICLE_OSAGO_INSURANCE_SERVICE_PRODUCT_NAME, insuranceService.getResponsePolicyName(response)),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Невозможность получения подробной информации о конкретном страховом полисе при пустом policyId")
    @Description("Тест направлен на проверку невозможности получения информации о конкртеном страховом полисе при пустом policyId")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB5-488")
    @Test()
    public void getInsurancePolicyWithEmptyPolicyId() {
        String jsonSchemaPath = "schemas/insuranceService/emptyPolicyIdGetInsurancePolicy.json";
        Response response = insuranceService.getPolicyInfo("");
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Получения подробной информации о несуществующем страховом полисе")
    @Description("Тест направлен на проверку получения информации о несуществующем страховом полисе")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB5-491")
    @Test()
    public void getInsurancePolicyWithFakePolicyId() {
        String jsonSchemaPath = "schemas/insuranceService/fakePolicyIdGetInsurancePolicy.json";
        Response response = insuranceService.getPolicyInfo(FAKE_INSURANCE_POLICY_ID);
        assertAll(
                () -> assertEquals(SC_NOT_FOUND, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                () -> assertEquals(FAKE_POLICY_ID_ERROR_MESSAGE, insuranceService.getResponsePolicyErrorMessage(response)),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Получения подробной информации конкретном страховом полисе по невалидному policyId")
    @Description("Тест направлен на проверку получения информации о конкретном страховом полисе по не валидному policyId")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB5-492")
    @Test()
    public void getInsurancePolicyWithInvalidPolicyId() {
        String jsonSchemaPath = "schemas/insuranceService/invalidPolicyIdGetInsurancePolicy.json";
        Response response = insuranceService.getPolicyInfo("invalidId");
        System.out.println(insuranceService.getResponsePolicyErrorMessage(response));
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                () -> assertEquals(INVALID_POLICY_ID_ERROR_MESSAGE, insuranceService.getResponsePolicyErrorMessage(response)),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }
}
