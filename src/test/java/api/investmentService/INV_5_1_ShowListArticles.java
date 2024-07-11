package api.investmentService;

import api.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pojo.investmentService.ErrorResponse;
import pojo.investmentService.ShowListArticle;

import static api.utils.JsonParser.parseJson;
import static constant.InvestmentConstants.ANALYTICS;
import static constant.InvestmentConstants.NEWS;
import static org.apache.hc.core5.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.hc.core5.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.ACCESS_TOKEN_INVESTMENT_SERVICE;
import static property.BaseProperties.INVESTMENT_SERVICE;
import static service.InvestmentService.getListArticlesType;

@Tags({@Tag("API"), @Tag("3.0")})
@DisplayName("INV-5.1 Просмотр списка статей для разделов 'Аналитика', 'Новости'")
public class INV_5_1_ShowListArticles extends BaseTest {
    private static final String JSON_NEWS = "/jsons/investmentJsons/articlesInfoNews.json";
    private static final String JSON_ANALYTICS = "/jsons/investmentJsons/articlesInfoAnalytics.json";
    private static final String JSON_INVALID = "/jsons/investmentJsons/badRequest.json";

    {
        RestAssured.baseURI = INVESTMENT_SERVICE;
    }

    @Test
    @TmsLink("LIB6-1305")
    @DisplayName("Просмотр списка статей для раздела 'Новости'")
    @Description("Данный тест-кейс проверяет отображение статей на вкладке 'Новости'")
    public void showListArticlesNews() {
        ShowListArticle actualData = getListArticlesType(ACCESS_TOKEN_INVESTMENT_SERVICE, NEWS, SC_OK)
                .as(ShowListArticle.class);
        ShowListArticle expectedData = parseJson(ShowListArticle.class, JSON_NEWS);
        assertEquals(actualData, expectedData, "Данные пользователя не соответствуют ожидаемым");
    }

    @Test
    @TmsLink("LIB-1248")
    @DisplayName("Просмотр списка статей для раздела 'Аналитика'")
    @Description("Данный тест-кейс проверяет отображение статей на вкладке 'Аналитика'")
    public void showListArticlesAnalytics() {
        ShowListArticle actualData = getListArticlesType(ACCESS_TOKEN_INVESTMENT_SERVICE, ANALYTICS, SC_OK)
                .as(ShowListArticle.class);
        ShowListArticle expectedData = parseJson(ShowListArticle.class, JSON_ANALYTICS);
        assertEquals(actualData, expectedData, "Данные пользователя не соответствуют ожидаемым");
    }

    @ParameterizedTest
    @ValueSource(strings = {"Документ", "Document", "-_.~", "123456"})
    @TmsLink("LIB6-1261")
    @DisplayName("Просмотр списка статей для разделов 'Аналитика', 'Новости' при невалидных данных")
    @Description("Данный тест-кейс проверяет отображение статей на вкладке 'Аналитика', 'Новости' при невалидных данных")
    public void showListArticleNewsInvalid(String queryParam) {
        ErrorResponse actualData = getListArticlesType(ACCESS_TOKEN_INVESTMENT_SERVICE, queryParam, SC_BAD_REQUEST)
                .as(ErrorResponse.class);
        ErrorResponse expectedData = parseJson(ErrorResponse.class, JSON_INVALID);
        assertAll(
                () -> assertEquals(actualData.getType(), expectedData.getType(), "Тип ответа не соответствует ожидаемому"),
                () -> assertEquals(actualData.getMessage(), expectedData.getMessage(), "Сообщение об ошибке не соответствует ожидаемому"));
    }
}
