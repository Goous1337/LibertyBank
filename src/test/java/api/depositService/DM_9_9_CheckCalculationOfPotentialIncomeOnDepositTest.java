package api.depositService;

import api.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static constant.DepositConstants.DEPOSIT_TITLE;
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
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-744" +
            "https://jira.astondevs.ru/browse/LIB3-734")
    @ParameterizedTest(name = "initialSum: {1}")
    @ValueSource(ints = {99999, 100000, 1000, 1001})
    public void checkPositiveBoundaryValueScenarios(Integer initialSum) {
        Response response = depositService.checkListBoundaryValueScenarios
                (2, initialSum, 13, true);
        assertAll(
                () -> assertEquals(SC_OK, response.getStatusCode()),
                () -> assertNotNull(response.jsonPath().get("finalSum")),
                () -> assertNotNull(response.jsonPath().get("moneyProfit")),
                () -> assertNotNull(response.jsonPath().get("percentProfit"))
        );
    }

    @DisplayName("Проверка негативных сценариев граничных значений")
    @Description("Данный тест-кейс направлен на проверку негативных сценариев граничных значений" +
            " в расчете потенциального дохода по депозиту на определенный срок")
    @Tags({@Tag("API"), @Tag("Negative")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-818")
    @ParameterizedTest(name = "initialSum: {1}")
    @ValueSource(ints = {-1, -2})
    public void checkNegativeBoundaryValueScenarios(Integer initialSum) {
        Response response = depositService.checkListBoundaryValueScenarios
                (2, initialSum, 13, true);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.getStatusCode()),
                () -> assertEquals(DEPOSIT_TITLE, response.jsonPath().get("title"))
        );
    }

    @DisplayName("Расчет потенциального дохода по депозиту на определенный срок без капитализации")
    @Description("Данный тест-кейс направлен на расчет потенциального дохода по депозиту на определенный" +
            " срок без капитализации")
    @Tags({@Tag("API"), @Tag("Smoke")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-735")
    @Test
    public void checkCalculationIncomeFromDepositForCertainPeriodCapitalizationFalse() {
        Response response = depositService.checkListBoundaryValueScenarios
                (2, 1000, 13, false);
        assertAll(
                () -> assertEquals(SC_OK, response.getStatusCode()),
                () -> assertNotNull(response.jsonPath().get("finalSum")),
                () -> assertNotNull(response.jsonPath().get("moneyProfit")),
                () -> assertNotNull(response.jsonPath().get("percentProfit"))
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
                (2, 1000, 13);
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
                (1000, 13, true);
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
                (2, 1000, true);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.getStatusCode()),
                () -> assertEquals(DEPOSIT_TITLE, response.jsonPath().get("title"))
        );
    }
}
