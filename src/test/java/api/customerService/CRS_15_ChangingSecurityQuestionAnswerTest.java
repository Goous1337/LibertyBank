package api.customerService;

import api.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Issues;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.apache.hc.core5.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.CUSTOMER_SERVICE;

@DisplayName("CRS-15 Изменение контрольного вопроса / ответа")
public class CRS_15_ChangingSecurityQuestionAnswerTest extends BaseTest {

    {
        RestAssured.baseURI = CUSTOMER_SERVICE;
    }

    @DisplayName("Изменение контрольного вопроса/ответа")
    @Description("Изменение контрольного вопроса/ответа")
    @Tags({@Tag("smoke"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-955")
    @Issues({@Issue("https://jira.astondevs.ru/browse/LIB-1129"), @Issue("https://jira.astondevs.ru/browse/LIB-1130")})
    @ParameterizedTest(name = "Id Клиента: {0}, Текст Вопроса: {1}, Текст Ответа: {2}")
    @CsvSource({
            "d54eb158-7499-4bda-bafb-d4bd965a1985, Вопрос, Ответ",
            "d54eb158-7499-4bda-bafb-d4bd965a1985, Вопрос c буквой Ё, Ответ с буквой ё",
    })

    public void successfulUpdateQuestionAnswer(String customerId, String securityQuestion, String securityAnswer) {
        Response response = customerService.checkSuccessfulUpdateQuestionAnswer(customerId, securityQuestion, securityAnswer);
        assertAll(
                () -> assertEquals(HttpStatus.SC_OK,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertEquals(securityQuestion,
                        response.body().jsonPath().get("securityQuestion"),
                        "Секретный вопрос в ответе не соответствует ожидаемому"),
                () -> assertEquals(securityAnswer,
                        response.body().jsonPath().get("securityAnswer"),
                        "Секретный ответ в ответе не соответствует ожидаемому")
        );
    }

    @DisplayName("Изменение контрольного вопроса/ответа, используя некорректный метод запроса")
    @Description("Изменение контрольного вопроса/ответа, используя некорректный метод запроса (POST вместо PATCH/OPTIONS)")
    @Tags({@Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-954")
    @ParameterizedTest(name = "Http метод: {0}, Id Клиента: {1}, Вопрос: {2}, Ответ: {3}")
    @CsvSource({
            "POST, d54eb158-7499-4bda-bafb-d4bd965a1985, Вопрос, Ответ",
            "PUT, d54eb158-7499-4bda-bafb-d4bd965a1985, Вопрос, Ответ",
    })
    public void checkUpdateQuestionAnswerInvalidHttpMethod(String invalidHttpMethod, String customerId, String securityQuestion, String securityAnswer) {
        Response response = customerService.checkUpdateQuestionAnswerInvalidHttpMethod(invalidHttpMethod,
                customerId, securityQuestion, securityAnswer);
        assertAll(
                () -> assertEquals(SC_METHOD_NOT_ALLOWED, response.statusCode(),
                        "Код ответа не соответствует ожидаемому")
        );
    }

    @DisplayName("Изменение контрольного вопроса/ответа, используя невалидный (не существующий) URL")
    @Description("Изменение контрольного вопроса/ответа, используя невалидный (не существующий) URL")
    @Tags({@Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-953")
    @ParameterizedTest(name = "Id Клиента: {0}, Текст Вопроса: {1}, Текст Ответа: {2}")
    @CsvSource({
            "d54eb158-7499-4bda-bafb-d4bd965a1985, Вопрос, Ответ"
    })

    public void checkUpdateQuestionAnswerInvalidUrl(String customerId, String securityQuestion, String securityAnswer) {
        Response response = customerService.checkUpdateQuestionAnswerInvalidUrl(customerId, securityQuestion, securityAnswer);
        assertAll(
                () -> assertEquals(SC_NOT_FOUND, response.statusCode(),
                        "Код ответа не соответствует ожидаемому")
        );
    }

    @DisplayName("Изменение контрольного вопроса/ответа (переданы пустые/невалидные данные)")
    @Description("Попытка передачи в теле запроса на изменение контрольного вопроса/ответа пустых или невалидных данных.")
    @Tags({@Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-951")
    @ParameterizedTest(name = "Id Клиента: {1}, Вопрос: {2}, Ответ: {3}")
    @CsvSource({
            "d54eb158-7499-4bda-bafb-d4bd965a1985, '', ''",
            "d54eb158-7499-4bda-bafb-d4bd965a1985, аааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааа," +
                    ", аааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааа",
            "d54eb158-7499-4bda-bafb-d4bd965a1985, 1, 1"
    })

    public void checkUpdateQuestionAnswerInvalidInvalidData(String customerId, String securityQuestion, String securityAnswer) {
        Response response = customerService.checkSuccessfulUpdateQuestionAnswer(customerId, securityQuestion, securityAnswer);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertEquals("Некорректный запрос. Убедитесь, что адрес указан верно и попробуйте еще раз.",
                        response.body().jsonPath().get("message"),
                        "Сообщение об ошибке не соответствует ожидаемому")
        );
    }
}
