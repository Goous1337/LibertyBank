package api.depositService;

import api.BaseTest;
import dataBase.requests.DepositServiceDataBaseRequest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static constant.DepositConstants.*;
import static org.apache.hc.core5.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.hc.core5.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.*;
import static property.BaseProperties.DEPOSIT_SERVICE_NEW;

@DisplayName("DM-9.9 Расчет потенциального дохода по депозиту на определенный срок.")
public class DM_9_9_CheckCalculationOfPotentialIncomeOnDepositTest extends BaseTest {
    {
        RestAssured.baseURI = DEPOSIT_SERVICE_NEW;
    }

    @DisplayName("Проверка позитивных сценариев граничных значений")
    @Description("Данный тест-кейс направлен на проверку позитивных сценариев граничных значений" +
            " в расчете потенциального дохода по депозиту на определенный срок")
    @Tags({@Tag("API"), @Tag("Smoke")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-744")
    @ParameterizedTest(name = "initialSum: {1}")
    @ValueSource(floats = {99999, 100000, 1000, 1001})
    public void checkPositiveBoundaryValueScenarios(Float initialSum) {
        Response response = depositService.checkListBoundaryValueScenarios
                (2, initialSum, 13, true);
        assertAll(
                () -> assertEquals(SC_OK, response.getStatusCode()),
                () -> assertNotNull(response.jsonPath().get("finalSum")),
                () -> assertNotNull(response.jsonPath().get("moneyProfit")),
                () -> assertNotNull(response.jsonPath().get("percentProfit"))
        );
    }

    @Disabled("Bug https://jira.astondevs.ru/browse/LIB3-1338")
    @DisplayName("Проверка негативных сценариев граничных значений")
    @Description("Данный тест-кейс направлен на проверку негативных сценариев граничных значений" +
            " в расчете потенциального дохода по депозиту на определенный срок")
    @Tags({@Tag("API"), @Tag("Negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-818")
    @ParameterizedTest(name = "initialSum: {1}")
    @ValueSource(floats = {-1, 500001})
    public void checkNegativeBoundaryValueScenarios(Float initialSum) {
        Response response = depositService.checkListBoundaryValueScenarios
                (2, initialSum, 13, true);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.getStatusCode()),
                () -> assertEquals(DEPOSIT_TITLE, response.jsonPath().get("title"))
        );
    }

    @Disabled("Bug https://jira.astondevs.ru/browse/LIB3-1339")
    @DisplayName("Расчет потенциального дохода по депозиту на определенный срок без капитализации")
    @Description("Данный тест-кейс направлен на расчет потенциального дохода по депозиту на определенный" +
            " срок без капитализации")
    @Tags({@Tag("API"), @Tag("Smoke")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-735")
    @Test
    public void checkCalculationIncomeFromDepositForCertainPeriodCapitalizationFalse() {
        String annualInsertRate = DepositServiceDataBaseRequest.getCapitalisationValue(DEPOSIT_ID_PRODUCT, TERM_TIME);
        Float convertAnnualInsertRate = Float.valueOf(annualInsertRate);
        Float valueFromFormulaIfFalse = depositService.calculationFinalDepositAmountIsFalse
                (INITIAL_SUM, convertAnnualInsertRate, TERM_TIME);
        Response response = depositService.checkListBoundaryValueScenarios
                (DEPOSIT_ID_PRODUCT, INITIAL_SUM, TERM_TIME, false);
        assertAll(
                () -> assertEquals(SC_OK, response.getStatusCode()),
                () -> assertNotNull(response.jsonPath().get("finalSum")),
                () -> assertNotNull(response.jsonPath().get("moneyProfit")),
                () -> assertNotNull(response.jsonPath().get("percentProfit")),
                () -> assertEquals(valueFromFormulaIfFalse, response.jsonPath().get("percentProfit"))
        );
    }

    @DisplayName("Расчет потенциального дохода по депозиту на определенный срок без параметра isCapitalisation")
    @Description("Данный тест-кейс направлен на расчет потенциального дохода по депозиту на определенный" +
            " срок без параметра isCapitalisation")
    @Tags({@Tag("API"), @Tag("Negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-739")
    @Test
    public void checkCalculationIncomeFromDepositForCertainPeriodWithoutCapitalization() {
        Response response = depositService.checkListCalculatingOfPotentialIncomeOnDepositWithoutCapitalisation
                (2, 1000f, 13);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.getStatusCode()),
                () -> assertEquals(DEPOSIT_TITLE, response.jsonPath().get("title"))
        );
    }

    @DisplayName("Расчет потенциального дохода по депозиту на определенный срок без параметра depositProductId")
    @Description("Данный тест-кейс направлен на расчет потенциального дохода по депозиту на определенный срок" +
            " без параметра depositProductId")
    @Tags({@Tag("API"), @Tag("Negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-736")
    @Test
    public void checkCalculatingOfPotentialIncomeOnDepositWithoutId() {
        Response response = depositService.checkListCalculatingOfPotentialIncomeOnDepositWithoutId
                (1000f, 13, true);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.getStatusCode()),
                () -> assertEquals(DEPOSIT_TITLE, response.jsonPath().get("title"))
        );
    }

    @DisplayName("Расчет потенциального дохода по депозиту на определенный срок без параметра initialSum")
    @Description("Данный тест-кейс направлен на расчет потенциального дохода по депозиту на определенный срок" +
            " без параметра initialSum")
    @Tags({@Tag("API"), @Tag("Negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-737")
    @Test
    public void checkCalculatingOfPotentialIncomeOnDepositWithoutInitialSum() {
        Response response = depositService.checkListCalculatingOfPotentialIncomeOnDepositWithoutInitialSum
                (2, 13, true);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.getStatusCode()),
                () -> assertEquals(DEPOSIT_TITLE, response.jsonPath().get("title"))
        );
    }

    @DisplayName("Расчет потенциального дохода по депозиту на определенный срок без параметра termTime")
    @Description("Данный тест-кейс направлен на расчет потенциального дохода по депозиту на определенный срок" +
            " без параметра termTime")
    @Tags({@Tag("API"), @Tag("Negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-738")
    @Test
    public void checkCalculatingOfPotentialIncomeOnDepositWithoutTermTime() {
        Response response = depositService.checkListCalculatingOfPotentialIncomeOnDepositWithoutTermTime
                (2, 1000f, true);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.getStatusCode()),
                () -> assertEquals(DEPOSIT_TITLE, response.jsonPath().get("title"))
        );
    }

    @Disabled("Bug https://jira.astondevs.ru/browse/LIB3-1340")
    @DisplayName("Расчет потенциального дохода по депозиту на определенный срок c капитализацией")
    @Description("Данный тест-кейс направлен на расчет потенциального дохода по депозиту на определенный" +
            "срок с капитализацией")
    @Tags({@Tag("API"), @Tag("Smoke")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-734")
    @Test
    public void checkCalculationIncomeFromDepositForCertainPeriodCapitalizationTrue() {
        String annualInsertRate = DepositServiceDataBaseRequest.getCapitalisationValue(DEPOSIT_ID_PRODUCT, TERM_TIME);
        Float convertAnnualInsertRate = Float.valueOf(annualInsertRate);
        Float getValueFromFormulaIfTrue = depositService.calculationFinalDepositAmountIsTrue
                (INITIAL_SUM, convertAnnualInsertRate, TERM_TIME);
        Response response = depositService.checkListBoundaryValueScenarios
                (DEPOSIT_ID_PRODUCT, INITIAL_SUM, TERM_TIME, true);
        assertAll(
                () -> assertEquals(SC_OK, response.statusCode()),
                () -> assertEquals(getValueFromFormulaIfTrue, response.jsonPath().get("finalSum"))
        );
    }
}
