package api.depositService;

import api.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static constant.DepositConstants.*;
import static org.apache.hc.core5.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.*;
import static property.BaseProperties.DEPOSIT_SERVICE;

@DisplayName("DM-9.2 Оформление нового депозита")
public class DM_9_2_MakeNewDepositTest extends BaseTest {
    {
        RestAssured.baseURI = DEPOSIT_SERVICE;
    }

    @DisplayName("Оформление нового депозита")
    @Description("Данный тест-кейс направлен на проверку DM 9.2 по US 9.2 на оформление нового депозита" +
            " авторизованным пользователем.")
    @Tags({@Tag("API"), @Tag("smoke")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-805")
    @Test
    public void checkMakeNewDeposit() {
        Response response = depositService.checkListMakeNewDeposit
                (1, 19000.0000f, "24", false);
        assertAll(
                () -> assertEquals(SC_OK,
                        response.getStatusCode()),

                () -> assertEquals(DEPOSIT_PRODUCT_ID, (Integer) response.jsonPath().get("depositProductId")),
                () -> assertEquals(DEPOSIT_AMOUNT, (Float) response.jsonPath().get("initialAmount")),
                () -> assertEquals(DEPOSIT_PERIOD, (String) response.jsonPath().get("periodMonths")),
                () -> assertEquals(DEPOSIT_RENEWAL, (Boolean) response.jsonPath().get("autoRenewal"))
        );
    }

    @DisplayName("Оформление нового депозита в случае некорректной конфигурации запроса")
    @Description("Данный тест-кейс направлен на проверку DM 9.2 по US 9.2 на оформление нового депозита" +
            "авторизованным пользователем в случае некорректной конфигурации запроса.")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-812")
    @Test
    public void checkMakeNewDepositInvalidRequest() {
        Response response = depositService.checkMakeNewDepositInvalidRequest
                (1, 19000.0000f, false);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.getStatusCode()),
                () -> assertNotNull(response.jsonPath().get("errorMessage"))
        );
    }

    @DisplayName("Оформление нового депозита с невалидным токеном")
    @Description("Данный тест-кейс направлен на проверку DM 9.2 по US 9.2 на оформление нового депозита" +
            "авторизованным пользователем в случае неуспешной валидации токена.")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-811")
    @Test
    public void checkMakeNewDepositInvalidToken() {
        Response response = depositService.checkListMakeNewDepositInvalidToken
                (1, 19000.0000f, "24", false);
        assertAll(
                () -> assertEquals(SC_UNAUTHORIZED, response.getStatusCode()),
                () -> assertNotNull(response.jsonPath().get("errorMessage"))
        );
    }

    @DisplayName("Проверка валидации обязательного поля 'сумма депозита' ")
    @Description("Данный тест-кейс направлен на проверку DM 9.2 по US 9.2 на оформление нового депозита" +
            "авторизованным пользователем при введении валидных и невалидных значений в обязательное поле" +
            "'сумма депозита'. Заявка с невалидными значениями не должна заноситься в БД.")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-814")
    @Test
    /**
     * 1.Отправить валидные значения в параметре "initialAmount" и "periodMonths" методом
     * POST для создания заявки нового депозита.
     */
    public void checkValidationDepositAmount() {
        Response response = depositService.checkListMakeNewDeposit
                (1, 19000.0000f, "24", false);
        assertAll(
                () -> assertEquals(SC_OK,
                        response.getStatusCode()),

                () -> assertEquals(DEPOSIT_PRODUCT_ID, (Integer) response.jsonPath().get("depositProductId")),
                () -> assertEquals(DEPOSIT_AMOUNT, (Float) response.jsonPath().get("initialAmount")),
                () -> assertEquals(DEPOSIT_PERIOD, (String) response.jsonPath().get("periodMonths")),
                () -> assertEquals(DEPOSIT_RENEWAL, (Boolean) response.jsonPath().get("autoRenewal"))
        );
    }

    @Test
    /**
     * 2. Отправить невалидные значения в параметре "initialAmount" и валидные значения
     * в параметре "periodMonths" методом POST для создания заявки нового депозита.
     */
    public void checkValidationDepositAmountIncorrectValues() {
        Response response = depositService.checkListValidationDepositAmountIncorrectValues
                (1, "десять 10", "24", false);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.getStatusCode()),
                () -> assertEquals(DEPOSIT_TITLE, response.jsonPath().get("title")),
                () -> assertEquals(DEPOSIT_400, response.jsonPath().get("status")),
                () -> assertEquals(DEPOSIT_DETAIL, response.jsonPath().get("detail"))
        );
    }

    @DisplayName("Проверка валидации обязательного поля 'срок депозита'")
    @Description("Данный тест-кейс направлен на проверку DM 9.2 по US 9.2 на оформление нового депозита" +
            " авторизованным пользователем при введении валидных и невалидных значений в обязательное поле" +
            " 'срок депозита'. Заявка с невалидными значениями не должна заноситься в БД.")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-815")
    @Test
    public void checkValidationDepositPeriod1() {
        Response response = depositService.checkListMakeNewDeposit
                (1, 19000.0000f, "двадцать 20", false);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.getStatusCode()),
                () -> assertNotNull(response.jsonPath().get("errorMessage"))
        );
    }
}
