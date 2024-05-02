package api.insuranceService;

import api.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static constant.InsuranceServiceConstants.ACCIDENT_INSURANCE_SERVICE_PRODUCT_NAME;
import static constant.InsuranceServiceConstants.FLAT_INSURANCE_SERVICE_PRODUCT_NAME;
import static constant.InsuranceServiceConstants.HOME_INSURANCE_SERVICE_PRODUCT_NAME;
import static constant.InsuranceServiceConstants.MEDICINE_PREMIUM_INSURANCE_SERVICE_PRODUCT_NAME;
import static constant.InsuranceServiceConstants.MEDICINE_STANDART_INSURANCE_SERVICE_PRODUCT_NAME;
import static constant.InsuranceServiceConstants.MEDICINE_STANDART_PLUS_INSURANCE_SERVICE_PRODUCT_NAME;
import static constant.InsuranceServiceConstants.MEDICINE_VIP_INSURANCE_SERVICE_PRODUCT_NAME;
import static constant.InsuranceServiceConstants.THING_INSURANCE_SERVICE_PRODUCT_NAME;
import static constant.InsuranceServiceConstants.TRAVELING_INSURANCE_SERVICE_PRODUCT_NAME;
import static constant.InsuranceServiceConstants.TYPE_OF_INSURANCE_ACCIDENT;
import static constant.InsuranceServiceConstants.TYPE_OF_INSURANCE_CAR;
import static constant.InsuranceServiceConstants.TYPE_OF_INSURANCE_HEALTH;
import static constant.InsuranceServiceConstants.TYPE_OF_INSURANCE_PROPERTY;
import static constant.InsuranceServiceConstants.TYPE_OF_INSURANCE_TRAVELING;
import static constant.InsuranceServiceConstants.VEHICLE_KASKO_INSURANCE_SERVICE_PRODUCT_NAME;
import static constant.InsuranceServiceConstants.VEHICLE_OSAGO_INSURANCE_SERVICE_PRODUCT_NAME;
import static constant.Message.NOT_FOUND_ERROR_MESSAGE;
import static constant.Message.NOT_VALID_VALUE_INSURANCE_PRODUCT_ID_ERROR_MESSAGE;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.INSURANCE_SERVICE;

public class INS_1_GetInfoAboutInsuranceProducts extends BaseTest {
    {
        RestAssured.baseURI = INSURANCE_SERVICE;
    }

    @DisplayName("Успешное получение данных о продуктах страхования(Автострахование)")
    @Description("Тест направлен на проверку успешного получения данных о продуктах страхования(Автострахование)")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB5-415")
    @Test()
    public void successfulRequestGetInfoAboutCarInsurance() {

        String jsonSchemaPath = "schemas/insuranceService/checkTypesOfInsurances.json";
        Response response = insuranceService.checkGetInfoAboutInsuranceProducts(TYPE_OF_INSURANCE_CAR);
        List<Response> jsonList = response.jsonPath().getList("products.name");
        assertAll(
                () -> assertEquals(SC_OK, response.statusCode(), "Статус код ответа не соответствует ожидаемому"),
                () -> assertEquals(VEHICLE_OSAGO_INSURANCE_SERVICE_PRODUCT_NAME, jsonList.get(0), "Автострахование ОСАГО отсутствует"),
                () -> assertEquals(VEHICLE_KASKO_INSURANCE_SERVICE_PRODUCT_NAME, jsonList.get(1), "Автострахование КАСКО отсутствует"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Успешное получение данных о продуктах страхования(Добровольное медицинское страхование)")
    @Description("Тест направлен на проверку успешного получения данных о продуктах страхования(Добровольное медицинское страхование)")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB5-418")
    @Test()
    public void successfulRequestGetInfoAboutHealthInsurance() {

        String jsonSchemaPath = "schemas/insuranceService/checkTypesOfInsurances.json";
        Response response = insuranceService.checkGetInfoAboutInsuranceProducts(TYPE_OF_INSURANCE_HEALTH);
        List<Response> jsonList = response.jsonPath().getList("products.name");
        assertAll(
                () -> assertEquals(SC_OK, response.statusCode(), "Статус код ответа не соответствует ожидаемому"),
                () -> assertEquals(MEDICINE_VIP_INSURANCE_SERVICE_PRODUCT_NAME, jsonList.get(0), "Медецинское страхование VIP отсутствует"),
                () -> assertEquals(MEDICINE_STANDART_INSURANCE_SERVICE_PRODUCT_NAME, jsonList.get(1), "Медецинское страхование Standart отсутствует"),
                () -> assertEquals(MEDICINE_STANDART_PLUS_INSURANCE_SERVICE_PRODUCT_NAME, jsonList.get(2), "Медецинское страхование Standart+ отсутствует"),
                () -> assertEquals(MEDICINE_PREMIUM_INSURANCE_SERVICE_PRODUCT_NAME, jsonList.get(3), "Медецинское страхование Premium отсутствует"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Успешное получение данных о продуктах страхования(Страхование выезжающих за границу)")
    @Description("Тест направлен на проверку успешного получения данных о продуктах страхования(Страхование выезжающих за границу)")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB5-420")
    @Test()
    public void successfulRequestGetInfoAboutTravelInsurance() {

        String jsonSchemaPath = "schemas/insuranceService/checkTypesOfInsurances.json";
        Response response = insuranceService.checkGetInfoAboutInsuranceProducts(TYPE_OF_INSURANCE_TRAVELING);
        assertAll(
                () -> assertEquals(SC_OK, response.statusCode(), "Статус код ответа не соответствует ожидаемому"),
                () -> assertEquals(TRAVELING_INSURANCE_SERVICE_PRODUCT_NAME, response.jsonPath().getList("products.name").get(0), "Страхование выезжающих за границу отсутствует"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Успешное получение данных о продуктах страхования(Страхование имущества дома и квартиры)")
    @Description("Тест направлен на проверку успешного получения данных о продуктах страхования(Страхование имущества дома и квартиры)")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB5-417")
    @Test()
    public void successfulRequestGetInfoAboutPropertyInsurance() {

        String jsonSchemaPath = "schemas/insuranceService/checkTypesOfInsurances.json";
        Response response = insuranceService.checkGetInfoAboutInsuranceProducts(TYPE_OF_INSURANCE_PROPERTY);
        List<Response> jsonList = response.jsonPath().getList("products.name");
        assertAll(
                () -> assertEquals(SC_OK, response.statusCode(), "Статус код ответа не соответствует ожидаемому"),
                () -> assertEquals(HOME_INSURANCE_SERVICE_PRODUCT_NAME, jsonList.get(0), "Страхование дома отсутствует"),
                () -> assertEquals(THING_INSURANCE_SERVICE_PRODUCT_NAME, jsonList.get(1), "Страхование домашнего имущества отсутствует"),
                () -> assertEquals(FLAT_INSURANCE_SERVICE_PRODUCT_NAME, jsonList.get(2), "Страхование квартиры отсутствует"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Успешное получение данных о продуктах страхования(Страхование от несчастных случаев)")
    @Description("Тест направлен на проверку успешного получения данных о продуктах страхования(Страхование от несчастных случаев)")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB5-419")
    @Test()
    public void successfulRequestGetInfoAboutAccidentInsurance() {

        String jsonSchemaPath = "schemas/insuranceService/checkTypesOfInsurances.json";
        Response response = insuranceService.checkGetInfoAboutInsuranceProducts(TYPE_OF_INSURANCE_ACCIDENT);
        assertAll(
                () -> assertEquals(SC_OK, response.statusCode(), "Статус код ответа не соответствует ожидаемому"),
                () -> assertEquals(ACCIDENT_INSURANCE_SERVICE_PRODUCT_NAME, response.jsonPath().getList("products.name").get(0), "Страхование от несчастных случаев отсутствует"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Проверка отправки невалидных значений productId")
    @Description("Тест направлен на проверку отправки невалидных значений productId")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB5-1721")
    @Test()
    public void requestForInvalidValues() {

        Response response = insuranceService.checkGetInfoAboutInsuranceProducts("1.5");
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.statusCode(), "Статус код ответа не соответствует ожидаемому"),
                () -> assertEquals(NOT_VALID_VALUE_INSURANCE_PRODUCT_ID_ERROR_MESSAGE, response.jsonPath().get("message").toString()),
                () -> assertEquals(NOT_VALID_VALUE_INSURANCE_PRODUCT_ID_ERROR_MESSAGE, response.jsonPath().get("message").toString())
        );
    }

    @DisplayName("Проверка отправки значений выходящих за диапазон productId")
    @Description("Тест направлен на проверку отправки значений выходящих за диапазон productId")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB5-1731")
    @Test()
    public void requestForValuesGreaterThanAcceptableValues() {

        Response response = insuranceService.checkGetInfoAboutInsuranceProducts("9999");
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST, response.statusCode(), "Статус код ответа не соответствует ожидаемому"),
                () -> assertEquals(NOT_VALID_VALUE_INSURANCE_PRODUCT_ID_ERROR_MESSAGE, response.jsonPath().get("message").toString())
        );
    }

    @DisplayName("Проверка отправки пустого значения productId")
    @Description("Тест направлен на проверку отправки пустого значения productId")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB5-426")
    @Test()
    public void requestEmptyValue() {

        Response response = insuranceService.checkGetInfoAboutInsuranceProducts("");
        assertAll(
                () -> assertEquals(SC_NOT_FOUND, response.statusCode(), "Статус код ответа не соответствует ожидаемому"),
                () -> assertEquals(NOT_FOUND_ERROR_MESSAGE, response.jsonPath().get("error").toString())
        );
    }
}