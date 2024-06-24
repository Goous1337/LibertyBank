package api.depositService;

import api.BaseTest;
import dataBase.requests.DepositServiceDataBaseRequest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static constant.DepositConstants.*;
import static constant.Message.RESPONSE_CODE_NOT_EXPECTED;
import static org.apache.hc.core5.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.hc.core5.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.*;
import static property.BaseProperties.DEPOSIT_SERVICE;

@Tags({@Tag("API"), @Tag("3.0")})
@DisplayName("DM-9.9 Расчет потенциального дохода по депозиту на определенный срок.")
public class DM_9_9_CheckCalculationOfPotentialIncomeOnDepositTest extends BaseTest {
    {
        RestAssured.baseURI = DEPOSIT_SERVICE;
    }

    private final int depositProductIdUsd = DepositServiceDataBaseRequest.getDepositProductId(CURRENCY_CODE_USD);
    private final int depositProductIdRub = DepositServiceDataBaseRequest.getDepositProductId(CURRENCY_CODE_RUB);

    @DisplayName("Проверка позитивных сценариев граничных значений")
    @Description("Данный тест-кейс направлен на проверку позитивных сценариев граничных значений" +
            " в расчете потенциального дохода по депозиту на определенный срок")
    @Tag("Smoke")
    @TmsLink("LIB3-744")
    @ParameterizedTest(name = "initialSum: {1}")
    @ValueSource(floats = {99999, 100000, 1000, 1001})
    public void checkPositiveBoundaryValueScenarios(Float initialSum) {
        Response response = depositService.checkListBoundaryValueScenarios
                (depositProductIdRub, initialSum, TERM_TIME, true, CURRENCY_CODE_RUB);
        assertAll(
                () -> assertEquals(SC_OK, response.getStatusCode(), RESPONSE_CODE_NOT_EXPECTED),
                () -> assertNotNull(response.jsonPath().get("finalSum")),
                () -> assertNotNull(response.jsonPath().get("moneyProfit")),
                () -> assertNotNull(response.jsonPath().get("percentProfit"))
        );
    }

    @Disabled("Bug https://jira.astondevs.ru/browse/LIB3-1338")
    @DisplayName("Проверка негативных сценариев граничных значений")
    @Description("Данный тест-кейс направлен на проверку негативных сценариев граничных значений" +
            " в расчете потенциального дохода по депозиту на определенный срок")
    @Tag("Negative")
    @TmsLink("LIB3-818")
    @ParameterizedTest(name = "initialSum: {1}")
    @ValueSource(floats = {-1, 1000001, 2999})
    public void checkNegativeBoundaryValueScenarios(Float initialSum) {
        Response response = depositService.checkListBoundaryValueScenarios
                (depositProductIdUsd, initialSum, TERM_TIME, true, CURRENCY_CODE_USD);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.getStatusCode(), RESPONSE_CODE_NOT_EXPECTED)
        );
    }

    @Disabled("Bug https://jira.astondevs.ru/browse/LIB3-1339")
    @DisplayName("Расчет потенциального дохода по депозиту на определенный срок без капитализации")
    @Description("Данный тест-кейс направлен на расчет потенциального дохода по депозиту на определенный" +
            " срок без капитализации")
    @Tag("Smoke")
    @TmsLink("LIB3-735")
    @Test
    public void checkCalculationIncomeFromDepositForCertainPeriodCapitalizationFalse() {
        String annualInsertRate = DepositServiceDataBaseRequest.getCapitalisationValue(depositProductIdRub, TERM_TIME);
        Float convertAnnualInsertRate = Float.valueOf(annualInsertRate);
        Float valueFromFormulaIfFalse = depositService.calculationFinalDepositAmountIsFalse
                (INITIAL_SUM, convertAnnualInsertRate, TERM_TIME);
        Response response = depositService.checkListBoundaryValueScenarios
                (depositProductIdRub, INITIAL_SUM, TERM_TIME, false, CURRENCY_CODE_RUB);
        assertAll(
                () -> assertEquals(SC_OK, response.getStatusCode(), RESPONSE_CODE_NOT_EXPECTED),
                () -> assertNotNull(response.jsonPath().get("finalSum")),
                () -> assertNotNull(response.jsonPath().get("moneyProfit")),
                () -> assertNotNull(response.jsonPath().get("percentProfit")),
                () -> assertEquals(valueFromFormulaIfFalse, response.jsonPath().get("percentProfit"))
        );
    }

    @DisplayName("Расчет потенциального дохода по депозиту на определенный срок без параметра isCapitalisation")
    @Description("Данный тест-кейс направлен на расчет потенциального дохода по депозиту на определенный" +
            " срок без параметра isCapitalisation")
    @Tag("Negative")
    @TmsLink("LIB3-739")
    @Test
    public void checkCalculationIncomeFromDepositForCertainPeriodWithoutCapitalization() {
        Response response = depositService.checkListCalculatingOfPotentialIncomeOnDepositWithoutCapitalisation
                (depositProductIdRub, 1000f, TERM_TIME, CURRENCY_CODE_RUB);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.getStatusCode(), RESPONSE_CODE_NOT_EXPECTED)
        );
    }

    @DisplayName("Расчет потенциального дохода по депозиту на определенный срок без параметра depositProductId")
    @Description("Данный тест-кейс направлен на расчет потенциального дохода по депозиту на определенный срок" +
            " без параметра depositProductId")
    @Tag("Negative")
    @TmsLink("LIB3-736")
    @Test
    public void checkCalculatingOfPotentialIncomeOnDepositWithoutId() {
        Response response = depositService.checkListCalculatingOfPotentialIncomeOnDepositWithoutId
                (1000f, TERM_TIME, true, CURRENCY_CODE_RUB);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.getStatusCode(), RESPONSE_CODE_NOT_EXPECTED)
        );
    }

    @DisplayName("Расчет потенциального дохода по депозиту на определенный срок без параметра initialSum")
    @Description("Данный тест-кейс направлен на расчет потенциального дохода по депозиту на определенный срок" +
            " без параметра initialSum")
    @Tag("Negative")
    @TmsLink("LIB3-737")
    @Test
    public void checkCalculatingOfPotentialIncomeOnDepositWithoutInitialSum() {
        Response response = depositService.checkListCalculatingOfPotentialIncomeOnDepositWithoutInitialSum
                (depositProductIdRub, TERM_TIME, true, CURRENCY_CODE_RUB);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.getStatusCode(), RESPONSE_CODE_NOT_EXPECTED)
        );
    }

    @DisplayName("Расчет потенциального дохода по депозиту на определенный срок без параметра termTime")
    @Description("Данный тест-кейс направлен на расчет потенциального дохода по депозиту на определенный срок" +
            " без параметра termTime")
    @Tag("Negative")
    @TmsLink("LIB3-738")
    @Test
    public void checkCalculatingOfPotentialIncomeOnDepositWithoutTermTime() {
        Response response = depositService.checkListCalculatingOfPotentialIncomeOnDepositWithoutTermTime
                (depositProductIdRub, 1000f, true, CURRENCY_CODE_RUB);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.getStatusCode(), RESPONSE_CODE_NOT_EXPECTED)
        );
    }

    @DisplayName("Расчет потенциального дохода по депозиту на определенный срок c капитализацией")
    @Description("Данный тест-кейс направлен на расчет потенциального дохода по депозиту на определенный" +
            "срок с капитализацией")
    @Tag("Smoke")
    @TmsLink("LIB3-734")
    @Test
    public void checkCalculationIncomeFromDepositForCertainPeriodCapitalizationTrue() {
        String annualInsertRate = DepositServiceDataBaseRequest.getCapitalisationValue(depositProductIdRub, TERM_TIME);
        Float convertAnnualInsertRate = Float.valueOf(annualInsertRate);
        Float getValueFromFormulaIfTrue = depositService.calculationFinalDepositAmountIsTrue
                (INITIAL_SUM, convertAnnualInsertRate, TERM_TIME);
        Response response = depositService.checkListBoundaryValueScenarios
                (depositProductIdRub, INITIAL_SUM, TERM_TIME, true, CURRENCY_CODE_RUB);
        assertAll(
                () -> assertEquals(SC_OK, response.statusCode(), RESPONSE_CODE_NOT_EXPECTED),
                () -> assertEquals(getValueFromFormulaIfTrue, response.jsonPath().get("finalSum"))
        );
    }
}
