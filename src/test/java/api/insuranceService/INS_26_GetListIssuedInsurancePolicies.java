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

import static org.apache.hc.core5.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.INSURANCE_SERVICE_LIST_POLICES;

@DisplayName("InS-26 Получение списка оформленных страховых полисов")
public class INS_26_GetListIssuedInsurancePolicies extends BaseTest {

    {
        RestAssured.baseURI = INSURANCE_SERVICE_LIST_POLICES;
    }

    @DisplayName("Получение списка оформленных страховых полисов используя Id из БД")
    @Description("Тест направлен на проверку получение списка оформленных страховых полисов используя Id из БД")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB5-1904")
    @Test()
    public void getListInsurancePoliciesIdFromDatabase() {
        String jsonSchemaPath = "schemas/insuranceService/checkGetListIssuedInsurancePolicies.json";
        Response response = insuranceService.getAllUserPoliciesByClientId("c132bbd4-885d-4924-bd96-468484a5e53b");
        assertAll(() -> assertEquals(SC_OK, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                  () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath)));
    }

    @DisplayName("Получение списка оформленных страховых полисов при пустом clientId")
    @Description("Тест направлен на проверку получение списка оформленных страховых полисов при пустом clientId")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB5-1908")
    @Test()
    public void getListInsurancePoliciesClientIdEmpty() {
        String jsonSchemaPath = "schemas/insuranceService/checkGetListInsurancePoliciesClientIdEmpty.json";
        Response response = insuranceService.getAllUserPoliciesByClientId(" ");
        assertAll(() -> assertEquals(SC_CLIENT_ERROR, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                  () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath)));
    }

    @DisplayName("Получение списка оформленных страховых полисов при не существующий в базе clientId")
    @Description("Тест направлен на проверку получение списка оформленных страховых полисов при не существующий в базе clientId")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB5-1908")
    @Test()
    public void getListInsurancePoliciesClientIdNotExist() {
        String jsonSchemaPath = "schemas/insuranceService/checkGetListInsurancePoliciesClientIdNotExist.json";
        Response response = insuranceService.getAllUserPoliciesByClientId("6aa0d17a-74d0-45fd-8de8-7bd8a4d54f3f");
        assertAll(() -> assertEquals(SC_CLIENT_ERROR, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                  () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath)));
    }

    @DisplayName("Получения списка оформленных полисов пользователя, не имеющего оформленных полисов")
    @Description("Тест направлен на проверку получение списка оформленных страховых полисов при не имеющихся у пользоваетля ")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB5-2925")
    @Test()
    public void getListInsurancePoliciesUserDoesHave() {
        String jsonSchemaPath = "schemas/insuranceService/checkGetListInsurancePoliciesUserDoesHave.json";
        Response response = insuranceService.getAllUserPoliciesByClientId("e8b5bcb5-96a2-46d9-b6df-33be35ab697a");
        assertAll(() -> assertEquals(SC_NOT_FOUND, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                  () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath)));
    }
}