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

import static constant.InsuranceServiceConstants.CLIENT_ID;
import static constant.InsuranceServiceConstants.CLIENT_ID_NOT_EXIST;
import static constant.InsuranceServiceConstants.CLIENT_ID_WITHOUT_POLICIES;
import static constant.Message.BAD_REQUEST_MESSAGE;
import static constant.Message.NOT_FOUND_CLIENT;
import static constant.Message.NOT_FOUND_ISSUED_POLICIES;
import static org.apache.hc.core5.http.HttpStatus.SC_CLIENT_ERROR;
import static org.apache.hc.core5.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.hc.core5.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.INSURANCE_SERVICE_LIST_POLICES;

@DisplayName("InS-26 Получение списка оформленных страховых полисов")
public class INS_26_GetListIssuedInsurancePoliciesTests extends BaseTest {

    {
        RestAssured.baseURI = INSURANCE_SERVICE_LIST_POLICES;
    }

    @DisplayName("Получение списка оформленных страховых полисов используя client Id из БД")
    @Description("Тест направлен на проверку получение списка оформленных страховых полисов используя client Id из БД")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB5-1904")
    @Test()
    public void getListInsurancePoliciesIdFromDatabase() {
        String jsonSchemaPath = "schemas/insuranceService/checkGetListIssuedInsurancePolicies.json";
        Response response = insuranceService.getAllUserPoliciesByClientId(CLIENT_ID);
        assertAll(() -> assertEquals(SC_OK, response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
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
        assertAll(() -> assertEquals(SC_CLIENT_ERROR, response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertEquals(BAD_REQUEST_MESSAGE, insuranceService.getResponsePolicyErrorMessage(response,
                        "error"), "Сообщение об ошибке не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath)));
    }

    @DisplayName("Получение списка оформленных страховых полисов при не существующий в базе clientId")
    @Description("Тест направлен на проверку получение списка оформленных страховых полисов при не существующий в базе clientId")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB5-1910")
    @Test()
    public void getListInsurancePoliciesClientIdNotExist() {
        String jsonSchemaPath = "schemas/insuranceService/checkGetListInsurancePoliciesClientIdNotExist.json";
        Response response = insuranceService.getAllUserPoliciesByClientId(CLIENT_ID_NOT_EXIST);
        assertAll(() -> assertEquals(SC_CLIENT_ERROR, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                () -> assertEquals(NOT_FOUND_CLIENT, insuranceService.getResponsePolicyErrorMessage(response,
                        "message"), "Сообщение об ошибке не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath)));
    }

    @DisplayName("Получения списка оформленных полисов пользователя, не имеющего оформленных полисов")
    @Description("Тест направлен на проверку получение списка оформленных страховых полисов при не имеющихся у пользоваетля ")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB5-2925")
    @Test()
    public void getListInsurancePoliciesUserDoesHave() {
        String jsonSchemaPath = "schemas/insuranceService/checkGetListInsurancePoliciesUserDoesHave.json";
        Response response = insuranceService.getAllUserPoliciesByClientId(CLIENT_ID_WITHOUT_POLICIES);
        assertAll(() -> assertEquals(SC_NOT_FOUND, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                () -> assertEquals(NOT_FOUND_ISSUED_POLICIES, insuranceService.getResponsePolicyErrorMessage(response,
                        "message"), "Сообщение об ошибке не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath)));
    }
}