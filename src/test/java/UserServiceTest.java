import org.junit.jupiter.api.Test;

import api.core.RequestParam;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.response.Response;
import model.response.JsonResponse;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static api.core.ApiClient.sendRequest;
import static api.core.ApiClient.sendSimpleRequest;
import static api.core.RequestParamType.PARAMETER;
import static constant.ApiEndpoints.REGISTRATION;
import static constant.ParamList.MOBILE_PHONE;
import static constant.ParamList.PHONE_NUMBER;
import static constant.ApiEndpoints.USER_STATUS;
import static io.restassured.http.Method.GET;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserServiceTest extends BaseTest {

    @Test
    @TmsLink("https://jira.astondevs.ru/browse/LIB-239  ")
    @Description("Проверка регистрации, если номер есть в базе и пользователь зарегистрирован")
    public void firstApiTest() {
        params.add(new RequestParam(PARAMETER, MOBILE_PHONE, PHONE_NUMBER));
        Response response = sendSimpleRequest(GET, USER_STATUS, params);
        // Также можно  написать PojoClass pojo = ApiClient.sendRequest(GET, USER_STATUS, params).as(PojoClass.class);
        // И выполнять уже операции с данными этого объекта.

        JsonResponse jsonResponse = sendRequest(GET, USER_STATUS, params);

        assertEquals(409, response.getStatusCode(), "Код ответа не соответствует ожидаемому");
        assertEquals(409, jsonResponse.getStatusCode(), "Код ответа не соответствует ожидаемому");
    }


    @ParameterizedTest
    @ValueSource(
            strings = {"7999888777", "799988877714", "7999888", "799fkfs"}
    )
    @TmsLink("https://jira.astondevs.ru/browse/LIB-275")
    @Description ("[US EP-1 Negative] Проверка работы валидации номера телефона")

    public void regUserWithInvalidNumberPhone(String invalidNumber){
        params.add(new RequestParam(PARAMETER, MOBILE_PHONE, invalidNumber));
        Response response = sendSimpleRequest(GET, REGISTRATION, params);

        JsonResponse jsonResponse = sendRequest(GET, REGISTRATION, params);

        assertEquals(400, response.getStatusCode(), "Код ответа не соответствует ожидаемому");
        assertEquals(400, jsonResponse.getStatusCode(), "Код ответа не соответствует ожидаемому");
    }
}


