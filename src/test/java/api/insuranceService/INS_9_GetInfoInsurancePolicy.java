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

import static constant.InsuranceServiceConstants.POLICY_THING_ID;
import static constant.InsuranceServiceConstants.POLICY_VEHICLE_ID;
import static org.apache.hc.core5.http.HttpStatus.SC_OK;
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
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }
}
