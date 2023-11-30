package api.creditService;

import api.BaseTest;
import api.core.RequestParam;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static constant.CreditServiceConstants.ERROR;
import static constant.CreditServiceConstants.STATUS_404;
import static org.apache.hc.core5.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.*;
import static property.BaseProperties.CREDIT_SERVICE;

@DisplayName("СМ 3.8 Просмотр количества поданных кредитных заявок")
public class CM_3_8_CheckNumberOfLoanApplicationsSubmittedTest extends BaseTest {
    {
        RestAssured.baseURI = CREDIT_SERVICE;
    }

    @DisplayName("Просмотр количества поданных кредитных заявок")
    @Description("Данный тест-кейс направлен на проверку СМ 3.8 по US 3.8 на получение количества заявок," +
            " находящихся в одном из двух статусов: a) В обработке; б) Одобрена.")
    @Tags({@Tag("smoke"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-533")
    @Test
    public void checkNumberOfLoanApplicationsSubmitted() {
        Response response = creditService.checkListNumberOfLoanApplicationsSubmitted();
        assertAll(
                () -> assertEquals(SC_OK, response.getStatusCode()),
                () -> assertNotNull(response.jsonPath().get("count"))
        );
    }

    @DisplayName("Получение количества поданных кредитных заявок при неуспешной валидации токена")
    @Description("Данный тест-кейс направлен на получение STATUS CODE 401 UNAUTHORIZED при неуспешной валидации" +
            " токена для получения поданных кредитных заявок СМ-3.8 и US-3.8")
    @Tags({@Tag("negative"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-536")
    @Test
    public void checkNumberOfLoanApplicationsSubmittedInvalidToken() {
        Response response = creditService.checkListNumberOfLoanApplicationsSubmittedInvalidToken();
        assertAll(
                () -> assertEquals(SC_UNAUTHORIZED, response.getStatusCode()),
                () -> assertNotNull(response.jsonPath().get("errorMessage"))
        );
    }

    @DisplayName("Получение количества поданных кредитных заявок в случае, если в результирующей" +
            " таблице нет записей по указанным критериям")
    @Description("Данный тест-кейс направлен на получение STATUS CODE 404 NOT FOUND в случае, если" +
            " в результирующей таблице нет записей по указанным критериям для получения количества" +
            " поданных кредитных заявок по СМ-3.8 и US-3.8")
    @Tags({@Tag("negative"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB3-537")
    @Test
    public void checkNumberOfLoanApplicationsSubmittedNoRecordsInTheTable() {
        Response response = creditService.checkListNumberOfLoanApplicationsSubmittedNoRecordsInTheTable();
        assertAll(
                () -> assertEquals(SC_NOT_FOUND, response.getStatusCode()),
                () -> assertEquals(STATUS_404, (Integer) response.jsonPath().get("status")),
                () -> assertEquals(ERROR, (String) response.jsonPath().get("error"))
        );
    }
}
