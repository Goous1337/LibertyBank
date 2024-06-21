package api.investmentService;

import api.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import pojo.investmentService.ErrorResponse;
import pojo.investmentService.QuestionnaireFormResponse;

import static api.utils.JsonParser.parseJson;
import static constant.InvestmentConstants.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.ACCESS_TOKEN_INVESTMENT_SERVICE;
import static property.BaseProperties.INVESTMENT_SERVICE;
import static service.InvestmentService.getQuestionnaireRequest;

@Tags({@Tag("API"), @Tag("2.0")})
@DisplayName("INV-1.4 Запросить данные пользователя для анкеты")
public class INV_1_4_RequestUserDataForQuestionnaire extends BaseTest {
    private static final String JSON = "/jsons/investmentJsons/questionnaireFormData.json";
    private static final String JSON_NOT_FOUND = "/jsons/investmentJsons/invalidCustomerId.json";

    {
        RestAssured.baseURI = INVESTMENT_SERVICE;
    }

    @Disabled("LIB6-1157")
    @Test
    @TmsLink("LIB6-858")
    @DisplayName("Запросить данные пользователя для анкеты")
    @Description("Данный тест-кейс проверяет запроc личных данных пользователя для анкеты")
    public void getUserDataForQuestionnaire() {
        QuestionnaireFormResponse actualData = getQuestionnaireRequest(ACCESS_TOKEN_INVESTMENT_SERVICE)
                .as(QuestionnaireFormResponse.class);
        QuestionnaireFormResponse expectedData = parseJson(QuestionnaireFormResponse.class, JSON);
        assertEquals(actualData, expectedData, "Данные пользователя не соответствуют ожидаемым");
    }

    @Test
    @TmsLink("LIB6-1040")
    @DisplayName("Запросить данные пользователя для анкеты, не найден customerId")
    @Description("Данный тест-кейс проверяет работу запроса данных пользователя для анкеты, при запросе данных с невалидным customerId")
    public void getUserDataForQuestionnaireInvalidCustomerId() {
        ErrorResponse actualData = getQuestionnaireRequest(ACCESS_TOKEN_INVALID_CUSTOMER_ID)
                .as(ErrorResponse.class);
        ErrorResponse expectedData = parseJson(ErrorResponse.class, JSON_NOT_FOUND);
        assertAll(
                () -> assertEquals(actualData.getUri(), expectedData.getUri(), "Эндпоинт не соответствует ожидаемому"),
                () -> assertEquals(actualData.getType(), expectedData.getType(), "Тип ответа не соответствует ожидаемому"),
                () -> assertEquals(actualData.getMessage(), expectedData.getMessage(), "Сообщение об ошибке не соответствует ожидаемому"));
    }
}