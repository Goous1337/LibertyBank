package api.investmentService;

import api.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pojo.investmentService.ErrorResponse;
import pojo.investmentService.ViewArticleForNews;

import static api.utils.JsonParser.parseJson;
import static constant.InvestmentConstants.VALID_ARTICLE_ID;
import static org.apache.hc.core5.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.ACCESS_TOKEN_INVESTMENT_SERVICE;
import static property.BaseProperties.INVESTMENT_SERVICE;
import static service.InvestmentService.getViewArticleById;

@Tags({@Tag("API"), @Tag("3.0")})
@DisplayName("INV-5.2 Просмотреть статью")
public class INV_5_2_ShowArticle extends BaseTest {
    private static final String JSON_NEWS = "/jsons/investmentJsons/viewArticleForNews.json";
    private static final String JSON_ARTICLE_NOT_FOUND = "/jsons/investmentJsons/articleNotFound.json";
    private static final String JSON_ARTICLE_BAD_REQUEST = "/jsons/investmentJsons/articleNotFound.json";

    {
        RestAssured.baseURI = INVESTMENT_SERVICE;
    }

    @Test
    @TmsLink("LIB6-1251")
    @DisplayName("Просмотр статьи для раздела 'Новости'")
    @Description("Данный тест-кейс проверяет отображение статьи на вкладке в разделах 'Аналитика', 'Новости' ")

    public void showArticleNews() {
        ViewArticleForNews actualData = getViewArticleById(ACCESS_TOKEN_INVESTMENT_SERVICE, VALID_ARTICLE_ID, SC_OK)
                .as(ViewArticleForNews.class);
        ViewArticleForNews expectedData = parseJson(ViewArticleForNews.class, JSON_NEWS);
        assertEquals(expectedData, actualData, "Данные пользователя не соответствуют ожидаемым");
    }

    @Disabled("LIB6-1311")
    @ParameterizedTest
    @ValueSource(strings = {" ", "0", "-1", "123456"})
    @TmsLink("LIB6-1315")
    @DisplayName("INV-5.2 Просмотреть статью при ее отсутствии")
    @Description("Данный тест-кейс направлен на проверку просмотра статьи при невалидных query параметрах(статья не найдена)")

    public void showArticleNewsNegative(String articleId) {
        ErrorResponse response = getViewArticleById(ACCESS_TOKEN_INVESTMENT_SERVICE, articleId, SC_NOT_FOUND)
                .as(ErrorResponse.class);
        ErrorResponse expectedData = parseJson(ErrorResponse.class, JSON_ARTICLE_NOT_FOUND);
        assertAll(
                () -> assertEquals(response.getType(), expectedData.getType(), "Тип ответа не соответствует ожидаемому"),
                () -> assertEquals(response.getMessage(), expectedData.getMessage(), "Сообщение об ошибке не соответствует ожидаемому"));
    }

    @Disabled("LIB6-1311")
    @ParameterizedTest // перепроверить баг
    @ValueSource(strings = {"-_.~", "Документ", "Document"})
    @TmsLink("LIB6-1281")
    @DisplayName("INV-5.2 Просмотреть статью с невалидными данными")
    @Description("Данный тест-кейс направлен на проверку просмотра статьи при невалидных query параметрах")

    public void showArticleNewsNegative1(String articleId) {
        ErrorResponse response = getViewArticleById(ACCESS_TOKEN_INVESTMENT_SERVICE, articleId, SC_BAD_REQUEST)
                .as(ErrorResponse.class);
        ErrorResponse expectedData = parseJson(ErrorResponse.class, JSON_ARTICLE_BAD_REQUEST);
        assertAll(
                () -> assertEquals(response.getType(), expectedData.getType(), "Тип ответа не соответствует ожидаемому"),
                () -> assertEquals(response.getMessage(), expectedData.getMessage(), "Сообщение об ошибке не соответствует ожидаемому"));
    }
}
