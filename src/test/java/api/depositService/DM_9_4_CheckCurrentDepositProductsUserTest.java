package api.depositService;

import api.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.apache.hc.core5.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.*;
import static property.BaseProperties.DEPOSIT_SERVICE;

@DisplayName("DM 9.4 Просмотр действующих депозитных продуктов пользователя")
public class DM_9_4_CheckCurrentDepositProductsUserTest extends BaseTest {

    {
        RestAssured.baseURI = DEPOSIT_SERVICE;
    }

    @Disabled("Уточнение требований")
    @DisplayName("Просмотр перечня действующих депозитных продуктов пользователя")
    @Description("Данный тест-кейс направлен на получение списка действующих депозитных продуктов пользователя " +
            "в виде коллекции, состоящей из Депозитных продуктов авторизованным пользователем")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-540")
    @Test

    public void checkInformationBankDepositProducts() {

        Response response = depositService.checkListCurrentDepositProductsUsers();
        assertAll(
                () -> assertEquals(SC_OK,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> {
                    List<Map<String, Object>> items = response.jsonPath().getList("$");
                    for (int i = 0; i < items.size(); i++) {
                        Map<String, Object> item = items.get(i);
                        assertTrue(item.get("id") instanceof String, "Поле items[" + i + "].'id' не соответствует ожидаемому");
                        assertTrue(item.get("name") instanceof String, "Поле items[" + i + "].'name' не соответствует ожидаемому");
                        assertTrue(item.get("currencyCode") instanceof String, "Поле items[" + i + "].'currencyCode' не соответствует ожидаемому");
                        assertTrue(item.get("currentBalance") instanceof Number, "Поле items[" + i + "].'currentBalance' не соответствует ожидаемому");
                        assertTrue(item.get("closeDate") instanceof String, "Поле items[" + i + "].'closeDate' не соответствует ожидаемому");
                        assertTrue(item.get("interestRate") instanceof Number, "Поле items[" + i + "].'interestRate' не соответствует ожидаемому");
                        assertTrue(item.get("mainDepaccountId") instanceof String, "Поле items[" + i + "].'mainDepaccountId' не соответствует ожидаемому");
                    }
                }
        );
    }

    @DisplayName("Просмотр информации по депозитам клиента при неуспешной валидации токена")
    @Description("Данный тест-кейс направлен на получение STATUS CODE 401  при неуспешной валидации токена")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-542")
    @Test

    public void unsuccessfulInformationBankDepositProducts() {
        Response response = depositService.checkListCurrentDepositProductUserEmptyToken();
        assertAll(
                () -> assertEquals(SC_UNAUTHORIZED,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertEquals(response.jsonPath().get("errorMessage"), "Unsuccessful token validation: Empty token",
                        "Сообщение в ответе не соответствует ожидаемому")
        );
    }
}
