package api.absInfoService;

import api.BaseTest;
import dataBase.requests.AbsInfoServiceDataBaseRequest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.ABS_INFO_SERVICE;

public class IS_4_1_GetInfoAboutBankByUuidTest extends BaseTest {
    {
        RestAssured.baseURI = ABS_INFO_SERVICE;
    }

    @DisplayName("[IS-4.1.] [STATUS CODE 200] (GET) Просмотр информации о конкретном филиале ")
    @Description("Проверить, что приходит информация о конкретном филиале банка")
    @Tags({@Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB4-1131")
    @Test

    public void successfulGetInfoAboutBank() {
        String jsonSchemaPath = "schemas/IS_4_1_GetInfoAboutBankByUuidTest/fieldsInfoAboutBank.json";
        String uuid = AbsInfoServiceDataBaseRequest.getUuid();
        Response response = absInfoService.checkListEditingInformationBranches(uuid);
        assertAll(
                () -> assertEquals(HttpStatus.SC_OK,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }
}
