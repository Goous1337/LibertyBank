package api.insuranceService;

import api.BaseTest;
import api.utils.Retry;
import dataBase.requests.InsuranceServiceDataBaseRequest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import pojo.insuranceService.OfflineInsuranceApplication;

import static constant.InsuranceServiceConstants.CLIENT_ID;
import static constant.InsuranceServiceConstants.NOT_FOUND;
import static constant.InsuranceServiceConstants.NO_CLIENT_ID;
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
                "Иваан", "Жук", "", 9009998877L, "2024-09-05", "12:00",
                "Москва", "Ул и-ц'а", "12", "", "", "", "HOME");
        Response response = insuranceService.makeNewApplicationInsuranceOffline(CLIENT_ID, offlineInsuranceApplication);
        String applicationId = response.jsonPath().getString("applicationId");
        assertAll(
                () -> assertEquals(SC_CREATED, response.statusCode(), "Код ответа не соответсвует ожидаемому"),
                () -> assertEquals(insuranceService.getResponseIdNewApplicationInsuranceOffline(response),
                        InsuranceServiceDataBaseRequest.getApplicationId(applicationId), "Заявка не создана в базе данных"),
                () -> assertEquals(insuranceService.getResponseIdNewApplicationInsuranceOffline(response),
                        InsuranceServiceDataBaseRequest.getApplicationId(applicationId),"Ваша заявка успешно оформлена"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(JSON_SCHEMA_PATH))
        );
    }

    @DisplayName("Подача заявки на страхование офлайн без авторизации пользователя")
    @Description("Тест направлен на проверку подачи заявки на страхование без авторизации пользователя")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB5-2304")
    @Test()
    public void getApplicationInsuranceOfflineWithoutAuthorization() {
        OfflineInsuranceApplication offlineInsuranceApplication = new OfflineInsuranceApplication("2",
                "Иваан", "Иванов", "Иванов", 9009998877L, "2024-09-05", "12:00",
                "Москва", "Ул и-ц'а", "12ф3-1ф", "12", "123", "012", "HOME");
        Response response = insuranceService.makeNewApplicationInsuranceOfflineWithoutAuthorization(offlineInsuranceApplication);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.statusCode(), "Код ответа не соответсвует ожидаемому"),
                () -> assertEquals(NO_CLIENT_ID, response.jsonPath().get("message"), "Поле clientId является обязательным")
        );
    }

    @DisplayName("Подача заявки на страхование офлайн с неверным end point")
    @Description("Тест направлен на проверку подачи заявки на страхование офлайн с неверным end point")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB5-2427")
    @Test()
    public void getApplicationInsuranceOfflineWithIncorrectEndpoint() {
        OfflineInsuranceApplication offlineInsuranceApplication = new OfflineInsuranceApplication("1",
                "Иваан", "Иванов", "Иванов", 9009998877L, "2024-09-05", "12:00",
                "Москва", "Ул и-ц'а", "12ф3-1ф", "12", "123", "012", "HOME");
        Response response = insuranceService.makeNewApplicationInsuranceOfflineWithIncorrectEndpoint(CLIENT_ID, offlineInsuranceApplication);
        assertAll(
                () -> assertEquals(SC_NOT_FOUND, response.statusCode(), "Код ответа не соответсвует ожидаемому"),
                () -> assertEquals(NOT_FOUND, response.jsonPath().get("error"), "Тело ответа не соответсвует ожидаемому")
        );
    }
}
