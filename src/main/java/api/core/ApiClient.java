package api.core;

import java.util.Collections;
import java.util.List;

import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSender;
import io.restassured.specification.RequestSpecification;
import model.response.ClassResponse;
import model.response.JsonResponse;

import static io.restassured.RestAssured.given;
import static io.restassured.config.HttpClientConfig.httpClientConfig;
import static org.apache.http.params.CoreConnectionPNames.CONNECTION_TIMEOUT;
import static org.apache.http.params.CoreConnectionPNames.SO_TIMEOUT;
import static property.BaseProperties.BASE_URL;


public class ApiClient {

    static {
        RestAssured.baseURI = BASE_URL;
    }

    public static Response sendSimpleRequest(Method method, String address,
            List<RequestParam> paramsTable) {
        RequestSender request = createRequest(paramsTable);
        return request.request(method, address);
    }

    public static Response sendSimpleRequest(Method method, String address,
            RequestParam param) {
        return sendSimpleRequest(method, address, List.of(param));
    }

    public static Response sendSimpleRequest(Method method, String address,
            List<RequestParam> paramsTable, Object pojo) {
        RequestSender request = createRequestWithPojoAndParams(paramsTable, pojo);
        return request.request(method, address);
    }

    public static Response sendSimpleRequest(Method method, String address, Object pojo) {
        RequestSender request = createRequestWithPojo(pojo);
        return request.request(method, address);
    }

    public static Response sendRequestWithoutParams(Method method, String address) {
        return sendSimpleRequest(method, address, Collections.emptyList());
    }

    public static <T> ClassResponse<T> sendRequest(Method method, String address,
            List<RequestParam> paramsTable, Class<T> clazz) {
        RequestSender request = createRequest(paramsTable);
        return getResponseAnswer(request.request(method, address), clazz);
    }

    public static JsonResponse sendRequest(Method method, String address,
            List<RequestParam> paramsTable) {
        RequestSender request = createRequest(paramsTable);
        return getResponseAnswer(request.request(method, address));
    }

    /**
     * Создание запроса
     *
     * @param paramsTable массив с параметрами
     * @return сформированный запрос
     */
    public static RequestSender createRequest(List<RequestParam> paramsTable) {
        return requestParamsSetter(getRequestSpecification(), paramsTable);
    }

    public static RequestSender createRequestWithPojoAndParams(List<RequestParam> paramsTable, Object pojo) {
        return requestParamsSetter(getRequestSpecification(), paramsTable).body(pojo);
    }

    public static RequestSender createRequestWithPojo(Object pojo) {
        return getRequestSpecification().body(pojo);
    }

    private static RequestSpecification requestParamsSetter(RequestSpecification request, List<RequestParam> paramsTable) {
        for (RequestParam requestParam : paramsTable) {
            String name = requestParam.getName();
            String value = requestParam.getValue();

            switch (requestParam.getType()) {
                case COOKIE:
                    request.cookie(name, value);
                    break;
                case PARAMETER:
                    request.param(name, value);
                    break;
                case HEADER:
                    request.header(name, value);
                    break;
                case BODY:
                    request.body(value);
                    break;
                default:
                    throw new IllegalArgumentException(String.format("Некорректно задан тип %s для параметра запроса %s ", requestParam.getType(), name));
            }
            request.log().everything();
        }
        return request;
    }

    private static RequestSpecification getRequestSpecification() {
        return given().relaxedHTTPSValidation()
                .config(RestAssured.config().httpClient(httpClientConfig()
                        .setParam(CONNECTION_TIMEOUT, 5000)
                        .setParam(SO_TIMEOUT, 5000)));
    }


    private static <T> model.response.ClassResponse<T> getResponseAnswer(Response r, Class<T> clazz) {
        T returnObject;
        try {
            returnObject = r.then().extract().as(clazz);
        } catch (Exception e) {
            throw new RuntimeException("Cannot map params response to class " + clazz.getName() +
                    "\n Cause: " + e.getMessage());
        }
        return new ClassResponse<T>(returnObject, r.getHeaders(), r.getStatusCode());
    }

    private static JsonResponse getResponseAnswer(Response r) {
        String body = r.getBody().asPrettyString();
        if (body.length() == 0 || body.charAt(0) != '{') {
            return new JsonResponse(new JSONObject().put("answer", body), r.getHeaders(), r.getStatusCode());
        } else {
            JSONObject json = new JSONObject(body);
            return new JsonResponse(json, r.getHeaders(), r.getStatusCode());
        }
    }
}
