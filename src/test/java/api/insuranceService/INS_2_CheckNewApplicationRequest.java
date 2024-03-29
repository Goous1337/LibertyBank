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
import pojo.insuranceService.*;

import java.util.ArrayList;
import java.util.Arrays;

import static constant.InsuranceServiceConstants.CLIENT_ID;
import static constant.InsuranceServiceConstants.INSURANCE_TYPE_VALUE;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.INSURANCE_SERVICE;

@DisplayName("InS-2 Подача заявки на договор страхования")
public class INS_2_CheckNewApplicationRequest extends BaseTest {

    {
        RestAssured.baseURI = INSURANCE_SERVICE;
    }

    @DisplayName("Заявка подана при всех заполненных полях валидными данными")
    @Description("Тест направлен на проверку подачи заявки при всех заполненных полях валидными данными согласно требованию")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB5-834")
    @Test()
    public void successfulRequestWithoutLicenseIssuingDate() {
        String jsonSchemaPath = "schemas/insuranceService/checkApplyingContract.json";
        CreateVehicleApplicationInsuranceRequest createVehicleApplicationInsuranceRequest = createPojoForVehicleApplication(
                "CAR", "Kalina", "LADA", 2021, "ПР256С-63", 1000000, 150, 1111111,
                "Михаил", "Попов", "2001-01-24", "12АА 125896", "2024-02-10", "РФ, г.Москва, ул. 1-й Армии, д. 35, кв. 124",
                1, 643, 15, false);
        Response response = insuranceService.checkMakeNewVehicleApplicationRequest(CLIENT_ID, createVehicleApplicationInsuranceRequest);
        assertAll(
                () -> assertEquals(SC_CREATED,
                        response.statusCode(),
                        "Код ответа соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Заявка не может быть подана если в данных об автомобиле значение mileage не валидное")
    @Description("Тест направлен на проверку невозможность подачи заявки при невалидном значении поля автомобиля mileage")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB5-898")
    @Test()
    public void unsuccessfulRequestInvalidMileage() {
        String jsonSchemaPath = "schemas/insuranceService/checkApplyingContractFail.json";
        CreateVehicleApplicationInsuranceRequest createVehicleApplicationInsuranceRequest = createPojoForVehicleApplication(
                "CAR", "Kalina", "LADA", 2021, "ПР256С-63", 1000000, 150, 11111111,
                "Михаил", "Попов", "2001-01-24", "12АА 125896", "2024-02-10", "РФ, г.Москва, ул. 1-й Армии, д. 35, кв. 124",
                1, 643, 15, false);
        Response response = insuranceService.checkMakeNewVehicleApplicationRequest(CLIENT_ID, createVehicleApplicationInsuranceRequest);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST,
                        response.statusCode(),
                        "Код ответа соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Заявка не может быть подана если в данных об автомобиле значение mileage пустое")
    @Description("Тест направлен на проверку невозможность подачи заявки при невалидном значении поля автомобиля mileage")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB5-897")
    @Test()
    public void unsuccessfulRequestWithoutMileage() {
        String jsonSchemaPath = "schemas/insuranceService/checkApplyingContractFail.json";
        CreateVehicleApplicationInsuranceRequest createVehicleApplicationInsuranceRequest = createPojoForVehicleApplication(
                "CAR", "Kalina", "LADA", 2021, "ПР256С-63", 1000000, 150, null,
                "Михаил", "Попов", "2001-01-24", "12АА 125896", "2024-02-10", "РФ, г.Москва, ул. 1-й Армии, д. 35, кв. 124",
                1, 643, 15, false);
        Response response = insuranceService.checkMakeNewVehicleApplicationRequest(CLIENT_ID, createVehicleApplicationInsuranceRequest);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST,
                        response.statusCode(),
                        "Код ответа соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Заявка не может быть подана если в данных об автомобиле значение поля power пустое")
    @Description("Тест направлен на проверку невозможность подачи заявки при отсутствии значения обязательного поля автомобиля power")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB5-896")
    @Test()
    public void unsuccessfulRequestWithoutPower() {
        String jsonSchemaPath = "schemas/insuranceService/checkApplyingContractFail.json";
        CreateVehicleApplicationInsuranceRequest createVehicleApplicationInsuranceRequest = createPojoForVehicleApplication(
                "CAR", "Kalina", "LADA", 2021, "ПР256С-63", 1000000, null, 1111111,
                "Михаил", "Попов", "2001-01-24", "12АА 125896", "2024-02-10", "РФ, г.Москва, ул. 1-й Армии, д. 35, кв. 124",
                1, 643, 15, false);
        Response response = insuranceService.checkMakeNewVehicleApplicationRequest(CLIENT_ID, createVehicleApplicationInsuranceRequest);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST,
                        response.statusCode(),
                        "Код ответа соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Заявка не может быть подана если в данных об автомобиле значение поля price невалидное")
    @Description("Тест направлен на проверку невозможность подачи заявки при невалидном значении поля автомобиля price")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB5-893")
    @Test()
    public void unsuccessfulRequestInvalidPrice() {
        String jsonSchemaPath = "schemas/insuranceService/checkApplyingContractFail.json";
        CreateVehicleApplicationInsuranceRequest createVehicleApplicationInsuranceRequest = createPojoForVehicleApplication(
                "CAR", "Kalina", "LADA", 2021, "ПР256С-63", -100000, 150, 1111111,
                "Михаил", "Попов", "2001-01-24", "12АА 125896", "2024-02-10", "РФ, г.Москва, ул. 1-й Армии, д. 35, кв. 124",
                1, 643, 15, false);
        Response response = insuranceService.checkMakeNewVehicleApplicationRequest(CLIENT_ID, createVehicleApplicationInsuranceRequest);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST,
                        response.statusCode(),
                        "Код ответа соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Заявка не может быть подана если в данных об автомобиле значение поля price пустое")
    @Description("Тест направлен на проверку невозможность подачи заявки при отстутствии значении поля автомобиля price")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB5-890")
    @Test()
    public void unsuccessfulRequestWithoutPrice() {
        String jsonSchemaPath = "schemas/insuranceService/checkApplyingContractFail.json";
        CreateVehicleApplicationInsuranceRequest createVehicleApplicationInsuranceRequest = createPojoForVehicleApplication(
                "CAR", "Kalina", "LADA", 2021, "ПР256С-63", null, 150, 1111111,
                "Михаил", "Попов", "2001-01-24", "12АА 125896", "2024-02-10", "РФ, г.Москва, ул. 1-й Армии, д. 35, кв. 124",
                1, 643, 15, false);
        Response response = insuranceService.checkMakeNewVehicleApplicationRequest(CLIENT_ID, createVehicleApplicationInsuranceRequest);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST,
                        response.statusCode(),
                        "Код ответа соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Заявка не может быть подана если в данных об автомобиле значение поля numberPlate пустое")
    @Description("Тест направлен на проверку невозможность подачи заявки при отсутствии значении поля автомобиля numberPlate")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB5-888")
    @Test()
    public void unsuccessfulRequestWithoutNumberPlate() {
        String jsonSchemaPath = "schemas/insuranceService/checkApplyingContractFail.json";
        CreateVehicleApplicationInsuranceRequest createVehicleApplicationInsuranceRequest = createPojoForVehicleApplication(
                "CAR", "Kalina", "LADA", 2021, null, 100000, 150, 1111111,
                "Михаил", "Попов", "2001-01-24", "12АА 125896", "2024-02-10", "РФ, г.Москва, ул. 1-й Армии, д. 35, кв. 124",
                1, 643, 15, false);
        Response response = insuranceService.checkMakeNewVehicleApplicationRequest(CLIENT_ID, createVehicleApplicationInsuranceRequest);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST,
                        response.statusCode(),
                        "Код ответа соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Заявка не может быть подана если в данных об автомобиле значение поля brand пустое")
    @Description("Тест направлен на проверку невозможность подачи заявки при отстутсвии значении поля автомобиля brand")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB5-884")
    @Test()
    public void unsuccessfulRequestWithoutBrand() {
        String jsonSchemaPath = "schemas/insuranceService/checkApplyingContractFail.json";
        CreateVehicleApplicationInsuranceRequest createVehicleApplicationInsuranceRequest = createPojoForVehicleApplication(
                "CAR", "Kalina", null, 2021, "ПР256С-63", 100000, 150, 1111111,
                "Михаил", "Попов", "2001-01-24", "12АА 125896", "2024-02-10", "РФ, г.Москва, ул. 1-й Армии, д. 35, кв. 124",
                1, 643, 15, false);
        Response response = insuranceService.checkMakeNewVehicleApplicationRequest(CLIENT_ID, createVehicleApplicationInsuranceRequest);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST,
                        response.statusCode(),
                        "Код ответа соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    @DisplayName("Заявка не может быть подана если в данных об автомобиле значение поля model пустое")
    @Description("Тест направлен на проверку невозможность подачи заявки отсутствует значение поля автомобиля model")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB5-881")
    @Test()
    public void unsuccessfulRequestWithoutModel() {
        String jsonSchemaPath = "schemas/insuranceService/checkApplyingContractFail.json";
        CreateVehicleApplicationInsuranceRequest createVehicleApplicationInsuranceRequest = createPojoForVehicleApplication(
                "CAR", null, "LADA", 2021, "ПР256С-63", 100000, 150, 1111111,
                "Михаил", "Попов", "2001-01-24", "12АА 125896", "2024-02-10", "РФ, г.Москва, ул. 1-й Армии, д. 35, кв. 124",
                1, 643, 15, false);
        Response response = insuranceService.checkMakeNewVehicleApplicationRequest(CLIENT_ID, createVehicleApplicationInsuranceRequest);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST,
                        response.statusCode(),
                        "Код ответа соответствует ожидаемому"),
                () -> response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonSchemaPath))
        );
    }

    public static CreateVehicleApplicationInsuranceRequest createPojoForVehicleApplication(
            String vehicleType, String model, String brand, Integer productionDate, String numberPlate, Integer price, Integer power, Integer mileage,
            String name, String surname, String birthdate, String licenseId, String licenseIssuingDate, String registrationAddress,
            Integer InsuranceProductId, Integer currencyNumberCode, Integer duration, Boolean insuranceOwner
    ) {
        Driver driver = new Driver(name, surname, "Станиславович", birthdate,
                licenseId, licenseIssuingDate, "2021-05-17");
        VehicleOwner vehicleOwner = new VehicleOwner("Иван", "Иванов", "Иванович",
                "1995-04-12", registrationAddress, "РФ, г.Москва, ул. 2-й Армии, д. 66, кв. 222");
        Vehicle vehicle = new Vehicle(new ArrayList<>(Arrays.asList(driver)), vehicleType, model, brand,
                productionDate, numberPlate, price, "W09xxxxxxxxYYYxxx",
                "hSHeJ7rD", "Pc-6LsPdL7", power, mileage);
        VehicleDocumentRequest vehicleDocumentRequest = new VehicleDocumentRequest(vehicleOwner, vehicle,
                "STS", "11 АА 111111", "2024-02-08");
        VehicleApplication vehicleApplication = new VehicleApplication(InsuranceProductId, currencyNumberCode, duration,
                insuranceOwner, vehicleDocumentRequest);
        return new CreateVehicleApplicationInsuranceRequest(INSURANCE_TYPE_VALUE, vehicleApplication);
    }
}

