package api.insuranceService;

import api.BaseTest;
import api.Retry;
import dataBase.DataBaseConnector;
import dataBase.requests.InsuranceServiceDataBaseRequest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import pojo.insuranceService.OfflineInsuranceApplication;

import static constant.InsuranceServiceConstants.BAD_REQUEST;
import static constant.InsuranceServiceConstants.CLIENT_ID;
import static constant.InsuranceServiceConstants.NOT_FOUND;
import static org.apache.hc.core5.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.hc.core5.http.HttpStatus.SC_CREATED;
import static org.apache.hc.core5.http.HttpStatus.SC_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.INSURANCE_POLICY_SERVICE;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("InS-28 Подача заявки на страхование оффлайн")
public class INS_28_CheckApplyingForInsuranceOffline extends BaseTest {
    private final String JSON_SCHEMA_PATH = "schemas/insuranceService/createdApplicationInsuranceOfflineSuccessfull.json";

    {
        RestAssured.baseURI = INSURANCE_POLICY_SERVICE;
    }

    @DisplayName("Подача заявки на страхование офлайн при валидных данных")
    @Description("Тест направлен на проверку подачи заявки на страхование офлайн при валидных данных")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB5-2303")
    @Test()
    @ExtendWith(Retry.class)
    public void getApplicationInsuranceOfflineWithValidData() {
        OfflineInsuranceApplication offlineInsuranceApplication = new OfflineInsuranceApplication("1",
                "Иван", "Иванов", "Иванович", 9009998877L, "2024-06-27", "12:00",
                "РФ, г. Москва, ул. 1-й Армии, д. 35, кв. 124", "", "HOME");
        Response response = insuranceService.makeNewApplicationInsuranceOffline(CLIENT_ID, offlineInsuranceApplication);
        String applicationId = response.jsonPath().getString("applicationId");
        assertAll(
                () -> assertEquals(SC_CREATED, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                () -> assertEquals(insuranceService.getResponseIdNewApplicationInsuranceOffline(response),
                        InsuranceServiceDataBaseRequest.getApplicationId(applicationId), "Заявка не создана в базе данных"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(JSON_SCHEMA_PATH))
        );
    }

    @DisplayName("Подача заявки на страхование офлайн без авторизации пользователя")
    @Description("Тест направлен на проверку подачи заявки на страхование без авторизации пользователя")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB5-2304")
    @Test()
    public void getApplicationInsuranceOfflineWithoutAuthorization() {
        OfflineInsuranceApplication offlineInsuranceApplication = new OfflineInsuranceApplication("1",
                "Иван", "Иванов", "Иванович", 9009998877L, "2024-06-27", "12:00",
                "РФ, г. Москва, ул. 1-й Армии, д. 35, кв. 124", "", "HOME");
        Response response = insuranceService.makeNewApplicationInsuranceOfflineWithoutAuthorization(offlineInsuranceApplication);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                () -> assertEquals(BAD_REQUEST, response.jsonPath().get("error"), "Тело ответа не соответсвует ожидаемому")
        );
    }

    @DisplayName("Подача заявки на страхование офлайн с неверным end point")
    @Description("Тест направлен на проверку подачи заявки на страхование офлайн с неверным end point")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB5-2427")
    @Test()
    public void getApplicationInsuranceOfflineWithIncorrectEndpoint() {
        OfflineInsuranceApplication offlineInsuranceApplication = new OfflineInsuranceApplication("1",
                "Иван", "Иванов", "Иванович", 9009998877L, "2024-06-27", "12:00",
                "РФ, г. Москва, ул. 1-й Армии, д. 35, кв. 124", "", "HOME");
        Response response = insuranceService.makeNewApplicationInsuranceOfflineWithIncorrectEndpoint(CLIENT_ID, offlineInsuranceApplication);
        assertAll(
                () -> assertEquals(SC_NOT_FOUND, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                () -> assertEquals(NOT_FOUND, response.jsonPath().get("error"), "Тело ответа не соответсвует ожидаемому")
        );
    }

    @AfterAll
    public static void closeDB() {
        DataBaseConnector.closeDataBase();
    }
}
