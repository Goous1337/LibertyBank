package api.customerService;

import api.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.apache.hc.core5.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.CUSTOMER_SERVICE;

@DisplayName("CRS-9 Получение информации о пользователе")
public class CRS_9_GettingUserInformationTest extends BaseTest {

    {
        RestAssured.baseURI = CUSTOMER_SERVICE;
    }

    @DisplayName("Получение информации о пользователе")
    @Description("Тестирование получения информации о пользователе")
    @Tags({@Tag("smoke"), @Tag("API")})
    @TmsLink("https://jira.astondevs.ru/browse/LIB-1338")
    @ParameterizedTest(name = "clientId: {0}, firstName: {1}, lastName: {2}, patronymic: {3}, birthDate: {4}," +
            " mobilePhone: {5}, email: {6}, customerStatus: {7}")
    @CsvSource({
            "6733729f-d627-4122-baab-8100d7242f30, Александр, Иванов, Сергеевич, 1981-12-02, 79101464757, aleksandr.ivanov789@yahoo.com, 2",
            "73481ef5-4703-44cb-b8e9-a36c58058959, Никита, Бондарев, Андроидович, 2000-06-11, 79228134511, n.bondarev@android.ru, 2",
            "e670aafa-adfd-49e6-bdbf-b65d9e0c7d53, Сергей, Лебедев, Павлович, 1985-08-14, 79031553942, sergey.lebedev123@yahoo.com, 1",
    })

    public void successfulGettingUserInformation(String customerId, String firstName, String lastName, String patronymic,
                                                 String birthDate, String mobilePhone, String email, String customerStatus) {
        String jsonSchemaPath = "schemas/customerService/CRS_9/successfulGettingUserInformation.json";
        Response response = customerService.checkGettingUserInformation(customerId);
        assertAll(
                () -> assertEquals(SC_OK,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @Disabled("Данный тест-кейс пока не может быть выполнен, так как механизм авторизации пользователя пока не реализован")
    @DisplayName("Получение информации о пользователе (не авторизованный пользователь)")
    @Description("Тестирование получения информации о пользователе, который не был авторизован")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-1339")
    @Test
    public void checkGettingUserInformationNotAuthorized() {
    }

    @Disabled("Данный тест-кейс пока не может быть выполнен, так как механизм авторизации пользователя пока не реализован")
    @DisplayName("Тестирование получения информации о пользователе от имени другого пользователя.")
    @Description("Тестирование получения информации о пользователе, с валидным access token другого пользователя")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-1339")
    @Test
    public void checkGettingUserInformationWithTokenFromAnotherUser() {
    }

    @DisplayName("Получение информации о пользователе (не успешное выполнение запроса)")
    @Description("Тестирование получения информации о пользователе, при использовании не валидных данных.")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-1340")
    @Test

    public void checkGettingUserInformationUsingInvalidData() {
        String jsonSchemaPath = "schemas/errorMessage.json";
        String customerId = "813f5509";
        Response response = customerService.checkGettingUserInformation(customerId);

        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Получение информации о пользователе (не валидный метод запроса)")
    @Description("Тестирование получения информации о пользователе, при использовании http методов " +
            "отличных от GET/OPTIONS: POST, PUT, PATCH.")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB-1341")
    @ParameterizedTest(name = "invalidHttpMethod: {0}, customerId: {1}")
    @CsvSource({
            "PUT, 6733729f-d627-4122-baab-8100d7242f30",
            "POST, 6733729f-d627-4122-baab-8100d7242f30",
            "PATCH, 6733729f-d627-4122-baab-8100d7242f30"
    })

    public void checkGettingUserInformationInvalidMethod(String invalidHttpMethod, String customerId) {
        String jsonSchemaPath = "schemas/errorMessage.json";
        Response response = customerService.checkGettingUserInformationInvalidMethod(invalidHttpMethod, customerId);

        assertAll(
                () -> assertEquals(SC_METHOD_NOT_ALLOWED, response.statusCode(), "Код ответа не соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }
}
