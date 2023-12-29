package api.absInfoService;

import api.BaseTest;
import dataBase.requests.AbsInfoServiceDataBaseRequest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import pojo.absInfoService.AbsInfoServiceDataBankBranch;

import static constant.AbsInfoServiceConstants.INVALID_UUID_ABS;
import static constant.AbsInfoServiceConstants.UN_EXIST_UUID_ABS;
import static org.apache.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.*;
import static property.BaseProperties.ABS_INFO_SERVICE;

@DisplayName("IS-3.1 Редактирование информации о филиалах")
public class IS_3_1_EditingInformationAboutBranchesTest extends BaseTest {
    {
        RestAssured.baseURI = ABS_INFO_SERVICE;
    }

    @DisplayName("[IS-3.1] [STATUS CODE 200] (PUT) Успешное обновление информации о филиале")
    @Description("Данный тест-кейс направлен на проверку редактирование информации о филиалах")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB4-916")
    @Test
    public void checkEditingInformationAboutBranches() {
        String uuid = AbsInfoServiceDataBaseRequest.getBankBranchUuid(5);
        AbsInfoServiceDataBankBranch absInfoServiceDataBankBranch = new AbsInfoServiceDataBankBranch
                ("123", "Беларусь", "Витебский", "Витебск", "Правды",
                        "66",
                        "210029", "150", true, "102", false,
                        "10:00",
                        "20:00", true, true, true,
                        true,
                        true, true, true, true, false,
                        "210022",
                        "DVC", "135", "Stable", "accaunt",
                        "Белбанк",
                        "345900", "Бел", "56784");
        Response response = absInfoService.checkListEditingInformationAboutBranches(absInfoServiceDataBankBranch, uuid);
        AbsInfoServiceDataBankBranch absInfoServiceDataBankBranchFromBd;
        absInfoServiceDataBankBranchFromBd = AbsInfoServiceDataBaseRequest.getBankBranchData(5);
        assertAll(
                () -> assertEquals(SC_OK, response.getStatusCode()),
                () -> assertNotNull(response.jsonPath().get("message")),
                () -> assertEquals(absInfoServiceDataBankBranch, absInfoServiceDataBankBranchFromBd)
        );
    }

    @DisplayName("[IS-3.1] [STATUS CODE 500] (PUT) Слишком большой uuid")
    @Description("Данный тест-кейс направлен на проверку редактирование информации о филиалах при слишком большом uuid")
    @Tags({@Tag("API"), @Tag("Negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB4-920")
    @Test
    public void checkEditingInformationAboutBranchesInvalidUuid() {
        AbsInfoServiceDataBankBranch absInfoServiceDataBankBranch = new AbsInfoServiceDataBankBranch
                ("123", "Беларусь", "Витебский", "Витебск", "Правды",
                        "66",
                        "210029", "150", true, "102", false,
                        "10:00",
                        "20:00", true, true, true,
                        true,
                        true, true, true, true, false,
                        "210022",
                        "DVC", "135", "Stable", "accaunt",
                        "Белбанк",
                        "345900", "Бел", "56784");
        Response response = absInfoService.checkListEditingInformationAboutBranches
                (absInfoServiceDataBankBranch, INVALID_UUID_ABS);
        assertAll(
                () -> assertEquals(SC_INTERNAL_SERVER_ERROR, response.getStatusCode()),
                () -> assertNotNull(response.jsonPath().get("message"))
        );
    }

    @DisplayName("[IS-3.1] [STATUS CODE 404] (PUT) Не удалось найти запрашиваемый ресурс")
    @Description("Данный тест-кейс направлен на проверку редактирование информации о филиалах при неверном ендпоинте")
    @Tags({@Tag("API"), @Tag("Negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB4-919")
    @Test
    public void checkEditingInformationAboutBranchesInvalidEndpoint() {
        String uuid = AbsInfoServiceDataBaseRequest.getBankBranchUuid(5);
        String jsonSchemaPath = "schemas/StatusCode404InfoService.json";
        AbsInfoServiceDataBankBranch absInfoServiceDataBankBranch = new AbsInfoServiceDataBankBranch
                ("123", "Беларусь", "Витебский", "Витебск", "Правды",
                        "66",
                        "210029", "150", true, "102", false,
                        "10:00",
                        "20:00", true, true, true,
                        true,
                        true, true, true, true, false,
                        "210022",
                        "DVC", "135", "Stable", "accaunt",
                        "Белбанк",
                        "345900", "Бел", "56784");
        Response response = absInfoService.checkListEditingInformationAboutBranchesInvalidEndpoint
                (absInfoServiceDataBankBranch, uuid);
        assertAll(
                () -> assertEquals(SC_NOT_FOUND, response.getStatusCode()),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("[IS-3.1] [STATUS CODE 404] (PUT) Не удалось найти запрашиваемый ресурс")
    @Description("Данный тест-кейс направлен на проверку редактирование информации о филиалах при неверном ендпоинте")
    @Tags({@Tag("API"), @Tag("Negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB4-919")
    @Test
    public void checkEditingInformationAboutBranchesUnExistUuid() {
        AbsInfoServiceDataBankBranch absInfoServiceDataBankBranch = new AbsInfoServiceDataBankBranch
                ("123", "Беларусь", "Витебский", "Витебск", "Правды",
                        "66",
                        "210029", "150", true, "102", false,
                        "10:00",
                        "20:00", true, true, true,
                        true,
                        true, true, true, true, false,
                        "210022",
                        "DVC", "135", "Stable", "accaunt",
                        "Белбанк",
                        "345900", "Бел", "56784");
        Response response = absInfoService.checkListEditingInformationAboutBranches
                (absInfoServiceDataBankBranch, UN_EXIST_UUID_ABS);
        assertAll(
                () -> assertEquals(SC_NOT_FOUND, response.getStatusCode()),
                () -> assertNotNull(response.jsonPath().get("message"))
        );
    }
}
