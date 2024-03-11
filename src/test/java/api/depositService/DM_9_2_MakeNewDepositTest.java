package api.depositService;

import api.BaseTest;
import dataBase.requests.DepositServiceDataBaseRequest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static constant.DepositConstants.*;
import static org.apache.hc.core5.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.*;
import static property.BaseProperties.DEPOSIT_SERVICE;

@DisplayName("DM-9.2 Оформление нового депозита")
public class DM_9_2_MakeNewDepositTest extends BaseTest {
    {
        RestAssured.baseURI = DEPOSIT_SERVICE;
    }

    String jsonSchemaPath = "schemas/depositService/newDepositResponse.json";
    String jsonSchemaPathIncorrectValues = "schemas/depositService/depositIncorrectValues.json";
    private static final int DEPOSIT_PRODUCT_ID_USD = DepositServiceDataBaseRequest.getDepositProductId(CURRENCY_CODE_USD);
    private static final int DEPOSIT_PRODUCT_ID_RUB = DepositServiceDataBaseRequest.getDepositProductId(CURRENCY_CODE_RUB);

    static Stream<Integer> dataProviderDepositProductId() {
        return Stream.of(DEPOSIT_PRODUCT_ID_RUB, DEPOSIT_PRODUCT_ID_USD);
    }

    @DisplayName("Оформление нового депозита")
    @Description("""
            Данный тест-кейс направлен на проверку DM 9.2 по US 9.2 на оформление нового депозита
            авторизованным пользователем.
            """)
    @Tags({@Tag("API"), @Tag("smoke")})
    @TmsLink("LIB3-805")
    @ParameterizedTest()
    @MethodSource({"dataProviderDepositProductId"})
    public void checkMakeNewDeposit(int depositProductId) {
        DepositServiceDataBaseRequest.clearUserDepositProductsById(DEPOSIT_CUSTOMER_ID, depositProductId);
        Float initialAmountMin = DepositServiceDataBaseRequest.getDepositAmountByProductId
                (depositProductId, DEPOSIT_AMOUNT_MIN);
        String periodMonthsMin = DepositServiceDataBaseRequest.getDepositDurationByProductId
                (depositProductId, DEPOSIT_MIN_DURATION);
        String periodMonthMax = DepositServiceDataBaseRequest.getDepositDurationByProductId
                (depositProductId, DEPOSIT_MAX_DURATION);
        Float initialAmountMax = DepositServiceDataBaseRequest.getDepositAmountByProductId
                (depositProductId, DEPOSIT_AMOUNT_MAX);
        Response responseMin = depositService.checkListMakeNewDeposit
                (depositProductId, initialAmountMin, periodMonthsMin, false);
        Float createdInitialAmountMin = DepositServiceDataBaseRequest.getInitialAmountById(DEPOSIT_CUSTOMER_ID, depositProductId);
        DepositServiceDataBaseRequest.clearUserDepositProductsById(DEPOSIT_CUSTOMER_ID, depositProductId);
        Response responseMax = depositService.checkListMakeNewDeposit
                (depositProductId, initialAmountMax, periodMonthMax, false);
        Float createdInitialAmountMax = DepositServiceDataBaseRequest.getInitialAmountById(DEPOSIT_CUSTOMER_ID, depositProductId);
        assertAll(
                () -> assertEquals(SC_OK, responseMin.getStatusCode()),
                () -> assertEquals(initialAmountMin, createdInitialAmountMin),
                () -> responseMin.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath)),
                () -> assertEquals(SC_OK, responseMax.getStatusCode()),
                () -> assertEquals(initialAmountMax, createdInitialAmountMax),
                () -> responseMax.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
        DepositServiceDataBaseRequest.clearUserDepositProductsById(DEPOSIT_CUSTOMER_ID, depositProductId);
    }

    @DisplayName(" Оформление нового депозита в случае некорректной конфигурации запроса")
    @Description("""
            Данный тест-кейс направлен на проверку DM 9.2 по US 9.2 на оформление нового депозита
            авторизованным пользователем в случае некорректной конфигурации запроса.
            """)
    @Tag("API")
    @TmsLink("LIB3-812")
    @ParameterizedTest
    @MethodSource("dataProviderDepositProductId")
    public void checkMakeNewDepositInvalidRequest(int depositProductId) {
        Float initialAmountMin = DepositServiceDataBaseRequest.getDepositAmountByProductId
                (depositProductId, DEPOSIT_AMOUNT_MIN);
        Float initialAmountMax = DepositServiceDataBaseRequest.getDepositAmountByProductId
                (depositProductId, DEPOSIT_AMOUNT_MAX);
        Response responseMin = depositService.checkMakeNewDepositInvalidRequest
                (depositProductId, initialAmountMin, false);
        Response responseMax = depositService.checkMakeNewDepositInvalidRequest
                (depositProductId, initialAmountMax, false);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, responseMin.getStatusCode()),
                () -> assertEquals(SC_BAD_REQUEST, responseMax.getStatusCode()),
                () -> assertNotNull(responseMin.jsonPath().get("errorMessage")),
                () -> assertNotNull(responseMax.jsonPath().get("errorMessage"))
        );
    }

    @DisplayName("Оформление нового депозита с невалидным токеном")
    @Description("""
            Данный тест-кейс направлен на проверку DM 9.2 по US 9.2 на оформление нового депозита
            авторизованным пользователем в случае неуспешной валидации токена.
            """)
    @Tag("API")
    @TmsLink("LIB3-811")
    @ParameterizedTest
    @MethodSource("dataProviderDepositProductId")
    public void checkMakeNewDepositInvalidToken(int depositProductId) {
        Float initialAmountMax = DepositServiceDataBaseRequest.getDepositAmountByProductId
                (depositProductId, DEPOSIT_AMOUNT_MAX);
        String periodMonthMax = DepositServiceDataBaseRequest.getDepositDurationByProductId
                (depositProductId, DEPOSIT_MAX_DURATION);
        Response response = depositService.checkListMakeNewDepositInvalidToken
                (depositProductId, initialAmountMax, periodMonthMax, false);
        assertAll(
                () -> assertEquals(SC_UNAUTHORIZED, response.getStatusCode()),
                () -> assertNotNull(response.jsonPath().get("errorMessage"))
        );
    }

    @DisplayName("Проверка валидации обязательного поля 'сумма депозита' ")
    @Description("""
            Данный тест-кейс направлен на проверку DM 9.2 по US 9.2 на оформление нового депозита
            авторизованным пользователем при введении валидных и невалидных значений в обязательное поле
            'сумма депозита'. Заявка с невалидными значениями не должна заноситься в БД.
            """)
    @Tag("API")
    @TmsLink("LIB3-814")
    @ParameterizedTest
    @MethodSource("dataProviderDepositProductId")
    public void checkValidationDepositAmountIncorrectValues(int depositProductId) {
        DepositServiceDataBaseRequest.clearUserDepositProductsById(DEPOSIT_CUSTOMER_ID, depositProductId);
        String periodMonth = DepositServiceDataBaseRequest.getDepositDurationByProductId
                (depositProductId, DEPOSIT_MAX_DURATION);
        Response response = depositService.checkListValidationDepositAmountIncorrectValues
                (depositProductId, "десять 10", periodMonth, false);
        List<String> depositCustomersId = DepositServiceDataBaseRequest.getAllCustomersId(depositProductId);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.getStatusCode()),
                () -> assertFalse(depositCustomersId.contains(DEPOSIT_CUSTOMER_ID)),
                () -> response.then().assertThat().body(JsonSchemaValidator
                        .matchesJsonSchemaInClasspath(jsonSchemaPathIncorrectValues))
        );
    }

    @DisplayName("Проверка валидации обязательного поля 'срок депозита'")
    @Description("""
            Данный тест-кейс направлен на проверку DM 9.2 по US 9.2 на оформление нового депозита
            авторизованным пользователем при введении валидных и невалидных значений в обязательное поле
            'срок депозита'. Заявка с невалидными значениями не должна заноситься в БД.
            """)
    @Tag("API")
    @TmsLink("LIB3-815")
    @ParameterizedTest
    @MethodSource("dataProviderDepositProductId")
    public void checkValidationDepositPeriodIncorrectValues(int depositProductId) {
        DepositServiceDataBaseRequest.clearUserDepositProductsById(DEPOSIT_CUSTOMER_ID, depositProductId);
        Float initialAmount = DepositServiceDataBaseRequest.getDepositAmountByProductId(depositProductId, DEPOSIT_AMOUNT_MAX);
        Response response = depositService.checkListMakeNewDeposit
                (depositProductId, initialAmount, "двадцать 20", false);
        List<String> depositCustomersId = DepositServiceDataBaseRequest.getAllCustomersId(depositProductId);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.getStatusCode()),
                () -> assertFalse(depositCustomersId.contains(DEPOSIT_CUSTOMER_ID)),
                () -> response.then().assertThat()
                        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPathIncorrectValues))
        );
    }
}
