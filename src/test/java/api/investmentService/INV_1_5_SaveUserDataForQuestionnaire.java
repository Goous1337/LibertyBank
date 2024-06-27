package api.investmentService;

import api.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pojo.investmentService.*;

import static api.utils.JsonParser.parseJson;
import static constant.InvestmentConstants.ACCESS_TOKEN_INVALID_CUSTOMER_ID;
import static org.apache.hc.core5.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.ACCESS_TOKEN_INVESTMENT_SERVICE;
import static property.BaseProperties.INVESTMENT_SERVICE;
import static service.InvestmentService.getQuestionnaireRequest;
import static service.InvestmentService.saveQuestionnaireRequest;

@Tags({@Tag("API"), @Tag("2.0")})
@DisplayName("INV-1.5 Сохранить данные пользователя из анкеты")
public class INV_1_5_SaveUserDataForQuestionnaire extends BaseTest {
    private static final String JSON_NOT_FOUND = "/jsons/investmentJsons/invalidCustomerId.json";

    {
        RestAssured.baseURI = INVESTMENT_SERVICE;
    }

    @Test
    @TmsLink("LIB6-1023")
    @DisplayName("Сохранить данные пользователя из анкеты")
    @Description("Данный тест-кейс позволяет проверить сохранение данных пользователя")
    public void saveUserDataForQuestionnaire() {
        Response response = saveQuestionnaireRequest(ACCESS_TOKEN_INVESTMENT_SERVICE);
        assertEquals(SC_OK, response.statusCode(), "Код ответа не соответствует ожидаемому");
    }

    @Test
    @TmsLink("LIB6-1126")
    @DisplayName("Сохранить данные пользователя из анкеты, не найден customerId")
    @Description("Данный тест-кейс проверяет сохранение данных пользователя из анкеты, при запросе данных с невалидным customerId")
    public void saveUserDataForQuestionnaireInvalidCustomerId() {
        ErrorResponse actualData = saveQuestionnaireRequest(ACCESS_TOKEN_INVALID_CUSTOMER_ID)
                .as(ErrorResponse.class);
        ErrorResponse expectedData = parseJson(ErrorResponse.class, JSON_NOT_FOUND);
        assertAll(
                () -> assertEquals(actualData.getUri(), expectedData.getUri(), "Эндпоинт не соответствует ожидаемому"),
                () -> assertEquals(actualData.getType(), expectedData.getType(), "Тип ответа не соответствует ожидаемому"),
                () -> assertEquals(actualData.getMessage(), expectedData.getMessage(), "Сообщение об ошибке не соответствует ожидаемому"));
    }

    @Disabled("LIB6-1229")
    @ParameterizedTest
    @CsvSource({
            "false, true, true, true, true",
            "true, false, true, true, true",
            "true, true, false, true, true",
            "true, true, true, false, true",
            "true, true, true, true, false"
    })
    @TmsLink("LIB6-1129")
    @DisplayName("Сохранить данные пользователя из анкеты, невалидный входной параметр")
    @Description("Данные тест-кейсы проверяют сохранение данных пользователя из анкеты, при запросе с невалидным входным параметром")
    public void saveUserDataForQuestionnaireInvalidInputParameter(Boolean residence, Boolean abroadTax, Boolean beneficialOwner, Boolean representative, Boolean beneficiary) {
        QuestionnaireData defaultData = new QuestionnaireData(true, true, true, true, true);

        Response defaultPatchResponse = saveQuestionnaireRequest(ACCESS_TOKEN_INVESTMENT_SERVICE, defaultData);
        assertEquals(SC_OK, defaultPatchResponse.statusCode(), "Код ответа не соответствует ожидаемому");

        Response initialGetResponse = getQuestionnaireRequest(ACCESS_TOKEN_INVESTMENT_SERVICE);
        assertEquals(SC_OK, initialGetResponse.statusCode(), "Код ответа не соответствует ожидаемому");

        QuestionnaireFormResponse defaultActualData = initialGetResponse.as(QuestionnaireFormResponse.class);

        assertAll(
                () -> assertEquals(defaultData.getResidence(), defaultActualData.getResidence(), "Поле 'residence' не соответствует ожидаемому"),
                () -> assertEquals(defaultData.getAbroadTax(), defaultActualData.getAbroadTax(), "Поле 'abroadTax' не соответствует ожидаемому"),
                () -> assertEquals(defaultData.getBeneficialOwner(), defaultActualData.getBeneficialOwner(), "Поле 'beneficialOwner' не соответствует ожидаемому"),
                () -> assertEquals(defaultData.getRepresentative(), defaultActualData.getRepresentative(), "Поле 'representative' не соответствует ожидаемому"),
                () -> assertEquals(defaultData.getBeneficiary(), defaultActualData.getBeneficiary(), "Поле 'beneficiary' не соответствует ожидаемому")
        );

        QuestionnaireData updatedData = new QuestionnaireData(residence, abroadTax, beneficialOwner, representative, beneficiary);

        Response updatedPatchResponse = saveQuestionnaireRequest(ACCESS_TOKEN_INVESTMENT_SERVICE, updatedData);
        assertEquals(SC_OK, updatedPatchResponse.statusCode(), "Код ответа не соответствует ожидаемому");

        Response updatedGetResponse = getQuestionnaireRequest(ACCESS_TOKEN_INVESTMENT_SERVICE);
        assertEquals(SC_OK, updatedGetResponse.statusCode(), "Код ответа не соответствует ожидаемому");

        QuestionnaireFormResponse updatedActualData = updatedGetResponse.as(QuestionnaireFormResponse.class);

        assertAll(
                () -> assertEquals(updatedData.getResidence(), updatedActualData.getResidence(), "Поле 'residence' не соответствует ожидаемому"),
                () -> assertEquals(updatedData.getAbroadTax(), updatedActualData.getAbroadTax(), "Поле 'abroadTax' не соответствует ожидаемому"),
                () -> assertEquals(updatedData.getBeneficialOwner(), updatedActualData.getBeneficialOwner(), "Поле 'beneficialOwner' не соответствует ожидаемому"),
                () -> assertEquals(updatedData.getRepresentative(), updatedActualData.getRepresentative(), "Поле 'representative' не соответствует ожидаемому"),
                () -> assertEquals(updatedData.getBeneficiary(), updatedActualData.getBeneficiary(), "Поле 'beneficiary' не соответствует ожидаемому")
        );
    }
}