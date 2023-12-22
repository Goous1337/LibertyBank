package api.absInfoService;

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
import service.InfoService;

import static org.asynchttpclient.util.HttpConstants.Methods.GET;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.ABS_INFO_SERVICE;

public class IS_4_CheckListOfBankBranchesAreGettingSuccessfullyTest {

    {
        RestAssured.baseURI = ABS_INFO_SERVICE;
    }

    @DisplayName("[IS-4] [STATUS CODE 200] (GET) Успешное получение списка филиалов")
    @Description("Проверить, что приходит список всех филиалов банка")
    @Tags({@Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB4-932")
    @Test

    public void successfulGettingListOfBankBranches() {
        String jsonSchemaPath = "schemas/IS_4_CheckListOfBanks/successfulGettingListOfBankBranches.json";
        Response response = InfoService.gettingListOfBankBranches(GET);
        assertAll(
                () -> assertEquals(HttpStatus.SC_OK,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }
    @DisplayName("[IS-4] [STATUS CODE 404] (GET) Ошибка в URL'e")
    @Description("Проверить, что при ошибке в URL появляется 404 ошибка")
    @Tags({@Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB4-935")
    @Test

    public void unsuccessfulGettingListOfBankBranches() {
        String jsonSchemaPath = "schemas/StatusCode404InfoService.json";
        Response response = InfoService.unGettingListOfBankBranches(GET);
        assertAll(
                () -> assertEquals(HttpStatus.SC_NOT_FOUND,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }
}


