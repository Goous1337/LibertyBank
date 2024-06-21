package api.creditService;

import api.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static constant.CreditServiceConstants.*;
import static org.apache.hc.core5.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.hc.core5.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.*;
import static property.BaseProperties.CREDIT_SERVICE;

@Tag("API")
@Epic("3 - Кредиты")
@Feature("CM-3.6 Просмотр подробной информации о кредитном продукте банка")
@DisplayName("CM-3.6 Просмотр подробной информации о кредитном продукте банка")
public class CM_3_6_CheckObtainingInformationOnBanksLoanProductTest extends BaseTest {
    {
        RestAssured.baseURI = CREDIT_SERVICE;
    }

    @DisplayName("Получение подробной информации по кредитному продукту банка")
    @Description("Данный тест-кейс направлен на проверку CM-3.6 по US 3.6 на получение подробной информации по" +
            " кредитному продукту банка")
    @Tag("Smoke")
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-416")
    @Test
    public void checkObtainingInformationOnBanksLoanProduct() {
        Response response = creditService.checkListObtainingInformationOnBanksLoanProduct();
        assertAll(
                () -> assertEquals(SC_OK, response.getStatusCode()),
                () -> assertEquals(CREDIT_ID, response.jsonPath().get("id")),
                () -> assertEquals(CREDIT_NAME, response.jsonPath().get("name")),
                () -> assertEquals(CREDIT_MIN_SUM, (Integer) response.jsonPath().get("minSum")),
                () -> assertEquals(CREDIT_MAX_SUM, (Integer) response.jsonPath().get("maxSum")),
                () -> assertEquals(CREDIT_CODE, response.jsonPath().get("currencyCode")),
                () -> assertEquals(CREDIT_RATE, (Float) response.jsonPath().get("interestRate")),
                () -> assertTrue(response.jsonPath().getBoolean("needGuarantees")),
                () -> assertFalse(response.jsonPath().getBoolean("deliveryInCash")),
                () -> assertTrue(response.jsonPath().getBoolean("earlyRepayment")),
                () -> assertTrue(response.jsonPath().getBoolean("needIncomeDetails")),
                () -> assertEquals(PERIOD_MONTH_MIN, (Integer) response.jsonPath().get("minPeriodMonths")),
                () -> assertEquals(PERIOD_MONTH_MAX, (Integer) response.jsonPath().get("maxPeriodMonths")),
                () -> assertEquals(CREDIT_CALCULATOR_MODE, response.jsonPath().get("calculationMode")),
                () -> assertEquals(GRACE_PERIOD_MONTH, (Integer) response.jsonPath().get("gracePeriodMonths")),
                () -> assertFalse(response.jsonPath().getBoolean("rateIsAdjustable"))
        );
    }

    @DisplayName("Получение подробной информации по кредитному продукту банка в случае, если в результирующей" +
            " таблице нет записей по указанным критериям")
    @Description("Данный тест-кейс направлен на получение STATUS CODE  404 В случае, если в результирующей таблице" +
            " нет записей по указанным критериям по CM-3.6 и US 3.6")
    @Tag("Negative")
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-419")
    @Test
    public void checkObtainingInformationOnBanksLoanProductNoRecordsInResultingTable() {
        Response response = creditService.checkListObtainingInformationOnBanksLoanProductNoRecordsInResultingTable();
        assertAll(
                () -> assertEquals(SC_NOT_FOUND, response.getStatusCode()),
                () -> assertNotNull(response.jsonPath().get("errorMessage"))
        );
    }
}
