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
import org.junit.jupiter.params.provider.MethodSource;
import pojo.investmentService.ErrorResponse;
import pojo.investmentService.ViewListSortedArticles;

import static api.utils.JsonParser.parseJson;
import static constant.InvestmentConstants.*;
import static org.apache.hc.core5.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.hc.core5.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.ACCESS_TOKEN_INVESTMENT_SERVICE;
import static property.BaseProperties.INVESTMENT_SERVICE;
import static service.InvestmentService.getViewSortedArticlesFeed;

@Tags({@Tag("API"), @Tag("3.0")})
@DisplayName("INV-5.3 Просмотреть статью")
public class INV_5_3_ViewListSortedArticles extends BaseTest {
    private static final String JSON_ARTICLE_NEWS = "/jsons/investmentJsons/viewListSortedArticlesNews.json";
    private static final String JSON_ARTICLE_ANALYTICS = "/jsons/investmentJsons/viewListSortedArticlesAnalytics.json";
    private static final String JSON_ARTICLES_FEED_BAD_REQUEST = "/jsons/investmentJsons/articlesFeedBadRequest.json";
    private static final String DEFAULT_LIMIT = "10";
    private static final String DEFAULT_TAG = "ECONOMICS";
    private static final String DEFAULT_TIMESTAMP = "";


    {
        RestAssured.baseURI = INVESTMENT_SERVICE;
    }

    @Test
    @TmsLink("LIB6-1256")
    @DisplayName("Просмотр списка статей согласно сортировке для раздела 'Новости'")
    @Description("Данный тест-кейс предназначен для сортировки статей:'Новости'")
    public void showArticleNews() {
        ViewListSortedArticles actualData = getViewSortedArticlesFeed(ACCESS_TOKEN_INVESTMENT_SERVICE, DEFAULT_TIMESTAMP, DEFAULT_LIMIT, DEFAULT_TAG, NEWS, SC_OK)
                .as(ViewListSortedArticles.class);
        ViewListSortedArticles expectedData = parseJson(ViewListSortedArticles.class, JSON_ARTICLE_NEWS);
        assertEquals(expectedData, actualData, "Данные пользователя не соответствуют ожидаемым");
    }

    @Test
    @TmsLink("LIB6-1306")
    @DisplayName("Просмотр списка статей согласно сортировке для раздела 'Аналитика'")
    @Description("Данный тест-кейс предназначен для сортировки статей:'Аналитика'")
    public void showArticleAnalytics() {
        ViewListSortedArticles actualData = getViewSortedArticlesFeed(ACCESS_TOKEN_INVESTMENT_SERVICE, DEFAULT_TIMESTAMP, DEFAULT_LIMIT, DEFAULT_TAG, ANALYTICS, SC_OK)
                .as(ViewListSortedArticles.class);
        ViewListSortedArticles expectedData = parseJson(ViewListSortedArticles.class, JSON_ARTICLE_ANALYTICS);
        assertEquals(expectedData, actualData, "Данные пользователя не соответствуют ожидаемым");
    }

    @ParameterizedTest
    @MethodSource("api.helpers.ApiFakerHelper#generateTimestamps")
    @TmsLink("LIB6-1306")
    @DisplayName("Просмотр списка статей согласно сортировке для раздела 'Аналитика' по TIMESTAMP")
    @Description("Данный тест-кейс предназначен для сортировки статей: 'Аналитика' с различными значениями TIMESTAMP")
    public void showArticleAnalyticsByTimestamp(String timestamp) {
        ErrorResponse response = getViewSortedArticlesFeed(ACCESS_TOKEN_INVESTMENT_SERVICE, timestamp, DEFAULT_LIMIT, DEFAULT_TAG, ANALYTICS, SC_BAD_REQUEST)
                .as(ErrorResponse.class);

        ErrorResponse expectedData = parseJson(ErrorResponse.class, JSON_ARTICLES_FEED_BAD_REQUEST);
        assertEquals(expectedData, response, "Данные пользователя не соответствуют ожидаемым");
    }

    @ParameterizedTest
    @MethodSource("api.helpers.ApiFakerHelper#generateLimits")
    @TmsLink("LIB6-1306")
    @DisplayName("Просмотр списка статей согласно сортировке для раздела 'Аналитика' по LIMIT")
    @Description("Данный тест-кейс предназначен для сортировки статей: 'Аналитика' с различными значениями LIMIT")
    public void showArticleAnalyticsByLimit(String limit) {
        ErrorResponse response = getViewSortedArticlesFeed(ACCESS_TOKEN_INVESTMENT_SERVICE, DEFAULT_TIMESTAMP, limit, DEFAULT_TAG, ANALYTICS, SC_BAD_REQUEST)
                .as(ErrorResponse.class);

        ErrorResponse expectedData = parseJson(ErrorResponse.class, JSON_ARTICLES_FEED_BAD_REQUEST);
        assertEquals(expectedData, response, "Данные пользователя не соответствуют ожидаемым");
    }

    @ParameterizedTest
    @MethodSource("api.helpers.ApiFakerHelper#generateTags")
    @TmsLink("LIB6-1306")
    @DisplayName("Просмотр списка статей согласно сортировке для раздела 'Аналитика' по TAG")
    @Description("Данный тест-кейс предназначен для сортировки статей: 'Аналитика' с различными значениями TAG")
    public void showArticleAnalyticsByTag(String tag) {
        ErrorResponse response = getViewSortedArticlesFeed(ACCESS_TOKEN_INVESTMENT_SERVICE, TIMESTAMP, DEFAULT_LIMIT, tag, ANALYTICS, SC_BAD_REQUEST)
                .as(ErrorResponse.class);

        ErrorResponse expectedData = parseJson(ErrorResponse.class, JSON_ARTICLES_FEED_BAD_REQUEST);
        assertEquals(expectedData, response, "Данные пользователя не соответствуют ожидаемым");
    }
}
