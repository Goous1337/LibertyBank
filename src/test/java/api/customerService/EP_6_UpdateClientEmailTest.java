package api.customerService;

import api.BaseTest;
import dataBase.DataBaseConnector;
import dataBase.requests.CustomerServiceDataBaseRequest;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.CUSTOMER_SERVICE;
import static property.BaseProperties.URL_USER_ACCOUNT_SERVICE;

@DisplayName("EP-6 Обновление email Клиента")
public class EP_6_UpdateClientEmailTest extends BaseTest {

    static {
        RestAssured.baseURI = CUSTOMER_SERVICE;
    }

    @DisplayName("Проверка успешного обновления адреса электронной почты клиента в БД")
    @Description("В данном  тест-кейсе проверяем обновление  адреса  электронной почты, привязанный к учетной записи " +
            "Пользователя в базе данных")
    @Tags({@Tag("smoke"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-925")
    @Issue("https://jira.astondevs.ru/browse/LIB-1131")
    @ParameterizedTest(name = "ID клиента: {0}, email: {1}")
    @CsvSource({
            "403fcd57-6721-48af-9554-3703518d489e, Natalya.Fedorova_059@outlook.com",
            "403fcd57-6721-48af-9554-3703518d489e, Allarm_18@yandex.com",
            "403fcd57-6721-48af-9554-3703518d489e, Allarm_18@[192.168.2.1]",
            "403fcd57-6721-48af-9554-3703518d489e, A@y.c",
            "403fcd57-6721-48af-9554-3703518d489e, 999999999999999999999999999999999999999999999999999999999999999@mail.ru",
            "403fcd57-6721-48af-9554-3703518d489e, 9999999999999999999999999999999999999999999999999999999999999999@mail.ru",
            "403fcd57-6721-48af-9554-3703518d489e, 99@mail.ru",
            "403fcd57-6721-48af-9554-3703518d489e, 99@takoyvotooooooooooooooooooooo4endlinnuydomennnnnnnnnnnnnnnnn.ru",
            "403fcd57-6721-48af-9554-3703518d489e, 99@takoyvotooooooooooooooooooooo4endlinnuydomennnnnnnnnnnnnnnnnn.ru",
    })

    public void successfulUpdateClientEmail(String customerId, String email) {
        Response response = customerService.checkUpdateClientEmail(customerId, email);
        String actualEmail = CustomerServiceDataBaseRequest.receivingEmailCustomerByCustomerId(customerId);
        assertAll(
                () -> assertEquals(HttpStatus.SC_OK,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertEquals(email, actualEmail,
                        "email не соответствует ожидаемому")
        );
    }

    @DisplayName("Обновление email клиента когда метод не PATCH / OPTIONS")
    @Description("В данном тест- кейсе проверяем возвращение ответа от сервера STATUS CODE 405 METHOD NOT ALLOWED на " +
            "запрос любым методом, вместо PATCH")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-956")
    @ParameterizedTest(name = "Метод: {0}, ID клиента: {1}, email: {2}")
    @CsvSource({
            "HEAD, 403fcd57-6721-48af-9554-3703518d489e, atalya.edorova_059@outlook.com",
            "GET, 403fcd57-6721-48af-9554-3703518d489e, Allarm_18@yandex.com",
            "PUT, 403fcd57-6721-48af-9554-3703518d489e, Allarm_18@[192.168.2.1]",
    })

    public void unsuccessfulUpdateEmailInvalidMethod(String invalidHttpMethod, String customerId, String email) {
        Response response = customerService.checkUnsuccessfulUpdateEmailInvalidHttpMethod(invalidHttpMethod,
                customerId, email);
        assertEquals(HttpStatus.SC_METHOD_NOT_ALLOWED, response.statusCode()
        );
    }

    @DisplayName("Проверка обновления электронной почты пользователя при вводе невалидных данных")
    @Description("Проводим проверку обновления  адреса  электронной почты пользователя в БД , с использованием " +
            "невалидных данных")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-960")
    @ParameterizedTest(name = "ID клиента: {0}, email: {1}")
    @CsvSource({
            "403fcd57-6721-48af-9554-3703518d489e, Наталья_059@outlook.com",
            "403fcd57-6721-48af-9554-3703518d489e, Nata..059@outlook.com",
            "403fcd57-6721-48af-9554-3703518d489e, Nata.Nata059Nata.Nata059Nata.Nata059Nata.Nata059Nata.Nata059Nata.@outlook.com",
            "403fcd57-6721-48af-9554-3703518d489e, Nata.Nata059@яндекс.ру",
            "403fcd57-6721-48af-9554-3703518d489e, Nata.Nata059@outlook_com",
            "403fcd57-6721-48af-9554-3703518d489e, Nata.Nata059@outlook.4com",
            "403fcd57-6721-48af-9554-3703518d489e, Nata.Nata059@outlook.-com",
            "403fcd57-6721-48af-9554-3703518d489e, Nata.Nata059@outlook-.com",
            "403fcd57-6721-48af-9554-3703518d489e, Nata.Nata059@Outlook.com",
            "403fcd57-6721-48af-9554-3703518d489e, Nata.Nata059@pghfjd+*^gfh.ru",
            "403fcd57-6721-48af-9554-3703518d489e, Nata.Nata059@outloookoutloookcomoutloookoutloookoutloookcomoutloookoutloo.com",
            "403fcd57-6721-48af-9554-3703518d489e, Nata.Nata059@",
            "403fcd57-6721-48af-9554-3703518d489e, @outlook.com",
            "'', Natalya.Fedorova_059@outlook.com",
            "403fcd57-6721-48af-9554-3703518d489e, ''",
    })

    public void unsuccessfulUpdateEmailInvalidData(String customerId, String email) {
        Response response = customerService.checkUpdateClientEmail(customerId, email);
        assertEquals(HttpStatus.SC_BAD_REQUEST,
                response.statusCode(),
                "Код ответа не соответствует ожидаемому");
    }

    @DisplayName("Обновление email клиента при указании невалидного URL")
    @Description("Проверяем обновление адреса электронной почты пользователя при указании невалидного URL")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-966")
    @ParameterizedTest(name = "EP: {0}, ID: {1}, email: {2}")
    @CsvSource({
            "auth/user/*set*/email, 403fcd57-6721-48af-9554-3703518d489e, Nataliya.059@outlook.com",
    })

    public void unsuccessfulUpdateClientEmailInvalidURL(String invalidURL, String customerId, String email) {
        assertEquals(HttpStatus.SC_NOT_FOUND,
                customerService.checkUpdateClientEmailInvalidURL(invalidURL, customerId, email).statusCode(),
                "Код ответа не соответствует ожидаемому");
    }

    @DisplayName("Проверка обязательности параметров в запросе")
    @Description("Проверяем обязательность указания  ключевых параметров  в  теле запроса")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-967")
    @Issue("https://jira.astondevs.ru/browse/LIB-1134")
    @ParameterizedTest(name = "ID клиента: {0}, email: {1}")
    @CsvSource({
            ", atalya.edorova_059@outlook.com",
            "403fcd57-6721-48af-9554-3703518d489e,",
    })
    public void unsuccessfulUpdateEmailEmptyData(String customerId, String email) {
        Response response = customerService.checkUpdateClientEmail(customerId, email);
        assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR,
                response.statusCode(),
                "Код ответа не соответствует ожидаемому");
    }

    @DisplayName("Изменение электронной почты неавторизованного клиента")
    @Description("Проводим проверку обновления  адреса  электронной почты неавторизованного клиента ")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-1039")
    @Issue("https://jira.astondevs.ru/browse/LIB-1136")
    @ParameterizedTest(name = "ID клиента: {0}, email: {1}")
    @CsvSource({
            "bd493ba6-bfc7-4656-8fb7-85db766fb636, elena.kozlova0001@outlook.com",
            "e455e1f7-1f9c-4c99-99f1-ccab5556f4b1, artem.temov.egorov@mail.com",
    })

    public void unsuccessfulUpdateNotAuthorizedClientEmail(String customerId, String email) {
        Response response = customerService.checkUpdateClientEmail(customerId, email);
        assertEquals(HttpStatus.SC_UNAUTHORIZED,
                response.statusCode(),
                "Код ответа не соответствует ожидаемому");
    }

}
