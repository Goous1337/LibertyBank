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
import pojo.insuranceService.CreateHealthApplicationRequest;
import pojo.insuranceService.HealthApplication;
import pojo.insuranceService.InsuredPerson;

import static constant.InsuranceServiceConstants.CLIENT_ID;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.INSURANCE_SERVICE;

@DisplayName("InS-15 Подача заявки на договор страхования здоровья")
public class INS_15_CheckNewHealthApplicationRequest extends BaseTest {
    {
        RestAssured.baseURI = INSURANCE_SERVICE;
    }

    @DisplayName("Заявка подана при всех заполненных полях валидными данными")
    @Description("Тест направлен на проверку подачи заявки при всех заполненных полях валидными данными согласно требованию")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB5-1070")
    @Test()
    public void successfulRequestHealthApplication() {
        String jsonSchemaPath = "schemas/insuranceService/checkApplyingContract.json";
        InsuredPerson insuredPerson = new InsuredPerson("Иван", "Иванов", "Иванович", "1995-04-12", "РФ, г.Москва, ул. 1-й Армии, д. 35, кв. 124", "РФ, г.Москва, ул. 2-й Армии, д. 66, кв. 222");
        HealthApplication healthApplication = new HealthApplication(4, 643, 35, false, insuredPerson);
        CreateHealthApplicationRequest createHealthApplicationRequest = new CreateHealthApplicationRequest("HEALTH", healthApplication);
        Response response = insuranceService.checkMakeNewHealthApplicationsRequest(CLIENT_ID, createHealthApplicationRequest);


        assertAll(
                () -> assertEquals(SC_CREATED,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Заявка подана на ДМС при несуществующем в базе clientId")
    @Description("Тест направлен на проверку подачи заявки при несуществующем в базе clientId")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB5-1078")
    @Test()
    public void unsuccessfulRequestHealthClientId() {
        String jsonSchemaPath = "schemas/insuranceService/checkApplyingFail.json";
        InsuredPerson insuredPerson = new InsuredPerson("Иван", "Иванов", "Иванович", "1995-04-12", "РФ, г.Москва, ул. 1-й Армии, д. 35, кв. 124", "РФ, г.Москва, ул. 2-й Армии, д. 66, кв. 222");
        HealthApplication healthApplication = new HealthApplication(4, 643, 35, true, insuredPerson);
        CreateHealthApplicationRequest createHealthApplicationRequest = new CreateHealthApplicationRequest("HEALTH", healthApplication);
        Response response = insuranceService.checkMakeNewHealthApplicationsRequest(CLIENT_ID, createHealthApplicationRequest);


        assertAll(
                () -> assertEquals(SC_NOT_FOUND,
                        response.statusCode(),
                        "Код ответа соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Заявка подана на ДМС при пустом clientId")
    @Description("Тест направлен на проверку подачи заявки при пустом поле clientId")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB5-1079")
    @Test()
    public void unsuccessfulRequestHealthEmptinessClientId() {
        String jsonSchemaPath = "schemas/insuranceService/checkApplyingFail.json";
        InsuredPerson insuredPerson = new InsuredPerson("Иван", "Иванов", "Иванович", "1995-04-12", "РФ, г.Москва, ул. 1-й Армии, д. 35, кв. 124", "РФ, г.Москва, ул. 2-й Армии, д. 66, кв. 222");
        HealthApplication healthApplication = new HealthApplication(4, 643, 35, false, insuredPerson);
        CreateHealthApplicationRequest createHealthApplicationRequest = new CreateHealthApplicationRequest("HEALTH", healthApplication);
        Response response = insuranceService.checkMakeNewHealthApplicationsRequest(CLIENT_ID, createHealthApplicationRequest);


        assertAll(
                () -> assertEquals(SC_BAD_REQUEST,
                        response.statusCode(),
                        "Код ответа соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Заявка подана на ДМС при невалидном insuranceProductId")
    @Description("Тест направлен на проверку подачи заявки при невалидном поле insuranceProductId")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB5-1081")
    @Test()
    public void unsuccessfulRequestHealthInsuranceProductId() {
        String jsonSchemaPath = "schemas/insuranceService/checkApplyingFail.json";
        InsuredPerson insuredPerson = new InsuredPerson("Иван", "Иванов", "Иванович", "1995-04-12", "РФ, г.Москва, ул. 1-й Армии, д. 35, кв. 124", "РФ, г.Москва, ул. 2-й Армии, д. 66, кв. 222");
        HealthApplication healthApplication = new HealthApplication(10, 643, 35, false, insuredPerson);
        CreateHealthApplicationRequest createHealthApplicationRequest = new CreateHealthApplicationRequest("HEALTH", healthApplication);
        Response response = insuranceService.checkMakeNewHealthApplicationsRequest(CLIENT_ID, createHealthApplicationRequest);


        assertAll(
                () -> assertEquals(SC_BAD_REQUEST,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }
}
