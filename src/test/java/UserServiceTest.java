import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.util.JSONPObject;

import api.core.ApiClient;
import api.core.RequestParam;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.response.Response;
import model.response.JsonResponse;

import static api.core.ApiClient.sendRequest;
import static api.core.ApiClient.sendSimpleRequest;
import static api.core.RequestParamType.PARAMETER;
import static constant.ApiEndpoints.USER_STATUS;
import static io.restassured.http.Method.GET;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserServiceTest extends BaseTest {

    @Test
    @TmsLink("https://jira.astondevs.ru/browse/LIB-239  ")
    @Description("Проверка регистрации, если номер есть в базе и пользователь зарегистрирован")
    public void firstApiTest() {
        params.add(new RequestParam(PARAMETER, "mobilePhone", "79998887777"));
        Response response = sendSimpleRequest(GET, USER_STATUS, params);
        // Также можно  написать PojoClass pojo = ApiClient.sendRequest(GET, USER_STATUS, params).as(PojoClass.class);
        // И выполнять уже операции с данными этого объекта.

        JsonResponse jsonResponse = sendRequest(GET, USER_STATUS, params);

        assertEquals(409, response.getStatusCode(), "Код ответа не соответствует ожидаемому");
        assertEquals(409, jsonResponse.getStatusCode(), "Код ответа не соответствует ожидаемому");
    }

}
