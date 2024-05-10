package api.insuranceService;

import api.BaseTest;
import dataBase.requests.InsuranceServiceDataBaseRequest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pojo.insuranceService.OfflineInsuranceApplication;

import static constant.InsuranceServiceConstants.CLIENT_ID;
import static org.apache.hc.core5.http.HttpStatus.SC_CREATED;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.INSURANCE_POLICY_SERVICE;

@DisplayName("InS-28 Подача заявки на страхование оффлайн")
public class INS_28_CheckApplyingForInsuranceOffline extends BaseTest {

    {
        RestAssured.baseURI = INSURANCE_POLICY_SERVICE;
    }

    @DisplayName("Подача заявки на страхование офлайн при валидных данных")
    @Description("Тест направлен на проверку подачи заявки на страхование офлайн при валидных данных")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB5-2303")
    @Test()
    public void getApplicationInsuranceOfflineWithValidData() {
        String jsonSchemaPath = "schemas/insuranceService/createdApplicationInsuranceOfflineSuccessfull.json";
        OfflineInsuranceApplication offlineInsuranceApplication = new OfflineInsuranceApplication("1",
                "Иван", "Иванов", "Иванович", 9009998877L, "2024-05-27", "12:00",
                "РФ, г. Москва, ул. 1-й Армии, д. 35, кв. 124", "");
        Response response = insuranceService.makeNewApplicationInsuranceOffline(CLIENT_ID, offlineInsuranceApplication);

        String applicationId = response.jsonPath().getString("applicationId");

        assertAll(
                () -> assertEquals(SC_CREATED, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                () -> assertEquals(insuranceService.getResponseIdNewApplicationInsuranceOffline(response),
                        InsuranceServiceDataBaseRequest.getApplicationId(applicationId), "Заявка не создана в базе данных"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }
}
