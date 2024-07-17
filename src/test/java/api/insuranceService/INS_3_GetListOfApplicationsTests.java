package api.insuranceService;

import api.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static constant.InsuranceServiceConstants.CLIENT_ID;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static property.BaseProperties.INSURANCE_SERVICE;

@DisplayName("InsS-3 Получение списка заявок (Актуально)")
public class INS_3_GetListOfApplicationsTests extends BaseTest {

    {
        RestAssured.baseURI = INSURANCE_SERVICE;
    }

    @DisplayName("Успешное получение списка заявок")
    @Description("Тест направлен на проверку получения списка заявок при отправке валидных данных согласно требованиям")
    @TmsLink("LIB5-441")
    @Test()
    public void successfulGetListOfApplicationsTest() {
        String jsonSchemaPath = "schemas/insuranceService/getListOfApplications.json";
        Response response = insuranceService.getListOfInsuranceApplications(CLIENT_ID);
        assertAll(
                () -> assertEquals(SC_OK, response.statusCode(),
                        "Статус код ответа не соответствует ожидаемому"),
                () -> assertNotNull(response.jsonPath().get("applicationId").toString()),
                () -> assertNotNull(response.jsonPath().get("status").toString()),
                () -> assertNotNull(response.jsonPath().get("productName").toString()),
                () -> assertNotNull(response.jsonPath().get("submissionDate").toString()),
                () -> assertNotNull(response.jsonPath().get("productId").toString()),
                () -> response.then().assertThat().body(JsonSchemaValidator
                        .matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Получение списка заявок без clintID")
    @Description("Тест направлен на проверку получения списка заявок при отправке запроса без clientId")
    @TmsLink("LIB5-443")
    @Test()
    public void getListOfApplicationsWithoutAuthorizationTest() {
        String jsonSchemaPath = "schemas/insuranceService/getListOfApplicationsFail.json";
        Response response = insuranceService.getListOfInsuranceApplicationsWithoutAuthorization();
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.statusCode(),
                        "Статус код ответа не соответсвует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator
                        .matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Получение списка заявок несуществующий URL")
    @Description("Тест направлен на проверку получения списка заявок при отправке запроса на несуществующий URL")
    @TmsLink("LIB5-442")
    @Test()
    public void getListOfApplicationsIncorrectEndpointTest() {
        String jsonSchemaPath = "schemas/insuranceService/getListOfApplicationsFail.json";
        Response response = insuranceService.getListOfInsuranceApplicationsIncorrectEndoint(CLIENT_ID);
        assertAll(
                () -> assertEquals(SC_NOT_FOUND, response.statusCode(),
                        "Статус код ответа не соответсвует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator
                        .matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

}
