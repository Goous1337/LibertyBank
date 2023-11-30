package api.creditService;

import api.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import service.CreditService;

import static org.apache.hc.core5.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.*;
import static property.BaseProperties.CREDIT_SERVICE;

@DisplayName("CM 3.3.1 Отображение электронной формы для оформления заявки на кредит")
public class CM_3_3_1_DisplayingElectronicBackgroundForApplyingCredit extends BaseTest {
    {
        RestAssured.baseURI = CREDIT_SERVICE;
    }

    @DisplayName("Отображение электронной формы для оформления заявки на кредит")
    @Description("Данный тест-кейс направлен на проверку отображения электронной формы для оформления заявки на кредит")
    @Tags({@Tag("API")})
    @TmsLink("https://jira.astondevs.ru/secure/StructureBoard.jspa?s=13#")
    @Test
    public void checkDisplayingElectronicBackgroundForApplyingCreditValidToken() {
        Response response = CreditService.checkGetRequestDisplayingElectronicBackground("3");
        assertAll(
                () -> assertEquals(SC_OK, response.statusCode(),
                        "Код ответа не соответствует ожидаемому")
        );
    }

    @Disabled("https://jira.astondevs.ru/browse/LIB3-1132")
    @DisplayName("Отображение электронной формы для оформления заявки на кредит при неуспешной валидации токена")
    @Description("Данный тест-кейс направлен на проверку отображения электронной формы для оформления заявки на кредит при неуспешной валидации токена")
    @Tags({@Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-851")
    @Test
    public void checkDisplayingElectronicBackgroundForApplyingCreditInvalidToken() {
        Response response = CreditService.checkGetRequestDisplayingElectronicBackgroundInvalidToken();
        assertAll(
                () -> assertEquals(SC_UNAUTHORIZED, response.statusCode(),
                        "Код ответа не соответствует ожидаемому")
        );
    }

    @DisplayName("Отображение электронной формы для оформления заявки на кредит в случае, если в результирующей таблице нет записей по указанным критериям")
    @Description("Данный тест-кейс направлен на проверку отображения ошибки в ответе сервера в случае, если в результирующей таблице нет записи по указанным критериям")
    @Tags({@Tag("API")})
    @TmsLink("https://jira.astondevs.ru/secure/StructureBoard.jspa?s=13#")
    @Test
    public void checkDisplayingElectronicBackgroundForApplyingCreditValidTokenWithoutParameters() {
        Response response = CreditService.checkGetRequestDisplayingElectronicBackground("4");
        assertAll(
                () -> assertEquals(SC_NOT_FOUND, response.statusCode(),
                        "Код ответа не соответствует ожидаемому")
        );
    }
}





