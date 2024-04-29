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

import static constant.InsuranceServiceConstants.*;
import static org.apache.http.HttpStatus.*;
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
                () -> assertEquals(INSURANCE_MEDICINE_VIP, jsonList.get(0), "Медецинское страхование VIP отсутствует"),
                () -> assertEquals(INSURANCE_MEDICINE_STANDART, jsonList.get(1), "Медецинское страхование Standart отсутствует"),
                () -> assertEquals(INSURANCE_MEDICINE_STANDART_PLUS, jsonList.get(2), "Медецинское страхование Standart+ отсутствует"),
                () -> assertEquals(INSURANCE_MEDICINE_PREMIUM, jsonList.get(3), "Медецинское страхование Premium отсутствует"),
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
                () -> assertEquals(INSURANCE_TRAVELING, response.jsonPath().getList("products.name").get(0), "Страхование выезжающих за границу отсутствует"),
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
                () -> assertEquals(INSURANCE_HOME, jsonList.get(0), "Страхование дома отсутствует"),
                () -> assertEquals(THING_INSURANCE_SERVICE_PRODUCT_NAME, jsonList.get(1), "Страхование домашнего имущества отсутствует"),
                () -> assertEquals(INSURANCE_FLAT, jsonList.get(2), "Страхование квартиры отсутствует"),
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
                () -> assertEquals(INSURANCE_ACCIDENT, response.jsonPath().getList("products.name").get(0), "Страхование от несчастных случаев отсутствует"),
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
                () -> assertEquals(SC_BAD_REQUEST, response.statusCode(), "Статус код ответа не соответствует ожидаемому")
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
                () -> assertEquals(SC_BAD_REQUEST, response.statusCode(), "Статус код ответа не соответствует ожидаемому")
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
                () -> assertEquals(SC_NOT_FOUND, response.statusCode(), "Статус код ответа не соответствует ожидаемому")
        );
    }
}
