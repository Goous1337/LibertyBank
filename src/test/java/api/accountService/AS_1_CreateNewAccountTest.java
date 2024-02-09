package api.accountService;

import api.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static constant.AccountServiceConstants.ACCOUNT_TYPE_PAYMENT;
import static constant.AccountServiceConstants.CURRENCY_RUB;
import static org.apache.hc.core5.http.HttpStatus.SC_ACCEPTED;
import static org.apache.hc.core5.http.HttpStatus.SC_BAD_REQUEST;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.ACCOUNT_SERVICE;

@DisplayName("Открыть счет")
public class AS_1_CreateNewAccountTest extends BaseTest {

    {
        RestAssured.baseURI = ACCOUNT_SERVICE;
    }

    @DisplayName("Открытие нового счета")
    @Description("Данный тест-кейс направлен на проверку AS-1 по US-4.1 на открытие нового счета с разными валидными параметрами запроса")
    @Tag("API")
    @TmsLink("LIB2-1007")
    @ParameterizedTest
    @CsvSource({
            "RUB, PAYMENT, false",
            "USD, PAYMENT, false",
            "EUR, PAYMENT, false",
            "RUB, PAYMENT, true"
    })
    public void checkCreateNewAccount(String currency, String accountType, Boolean isMain) {
        Response response = accountService.checkCreateNewAccount(currency, accountType, isMain);
        String jsonSchemaPath = "schemas/accountService/successfulCreatingNewAccountList.json";
        assertAll(
                () -> assertEquals(SC_ACCEPTED,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Открытие нового счета для несуществующего пользователя")
    @Description("Данный тест-кейс направлен на проверку AS-1 по US-4.1 на открытие нового счета для несуществующего пользователя")
    @Tag("API")
    @TmsLink("LIB2-1009")
    @Test
    public void checkCreateNewAccountForNotRegisteredUser() {
        Response response = accountService.checkCreateNewAccountWithInvalidUserData(CURRENCY_RUB, ACCOUNT_TYPE_PAYMENT, true);
        String jsonSchemaPath = "schemas/accountService/errorResponse.json";
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Открытие нового счета с невалидными данными запроса")
    @Description("Данный тест-кейс направлен на проверку AS-1 по US-4.1 на открытие нового счета с пустым телом запроса и с невалидным телом запроса")
    @Tag("API")
    @TmsLink("LIB2-1008")
    @ParameterizedTest
    @CsvSource({
            " , , , ",
            "RUB, FOREIGN CURRENCY, false"
    })
    public void checkCreateNewAccountWithInvalidData(String currency, String accountType, Boolean isMain) {
        Response response = accountService.checkCreateNewAccount(currency, accountType, isMain);
        String jsonSchemaPath = "schemas/accountService/errorResponse.json";
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому для невалидного запроса"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }
}
