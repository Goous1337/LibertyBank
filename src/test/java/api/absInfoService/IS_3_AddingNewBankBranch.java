package api.absInfoService;

import api.BaseTest;
import dataBase.requests.AbsInfoServiceDataBaseRequest;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pojo.absInfoService.AbsInfoServiceDataBankBranch;

import static org.apache.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.*;
import static property.BaseProperties.ABS_INFO_SERVICE;

@DisplayName("Добавление нового филиала банка")
public class IS_3_AddingNewBankBranch extends BaseTest {

    {
        RestAssured.baseURI = ABS_INFO_SERVICE;
    }

    @DisplayName("[IS-3] [STATUS CODE 200] (POST)")
    @Description("Данный тест-кейс направлен на проверку [IS-3] [STATUS CODE 200] (POST) Успешное добавление нового филиала")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB4-926")
    @Test
    public void successfulAddingNewBankBranch() {
        String bankBranchNumber = "1234";
        AbsInfoServiceDataBaseRequest.deleteNewBankBranch(bankBranchNumber);
        AbsInfoServiceDataBankBranch absInfoServiceDataBankBranch = new AbsInfoServiceDataBankBranch(bankBranchNumber, "Россия",
                "Московская область", "Балашиха", "Вернадского", "51", "117996", "55.696224, 37.544534", true, "+74955554455",
                false, "00:01", "23:59", true, true, true, true, true, true, true, false, true, "141414141", "142458873",
                "4212121214", "40702810562000000000", "30101810400000000225", "АО «Liberty Bank»", "0961047777",
                "1027700057413", "LIBBRUMM007");
        Response response = absInfoService.checkAddNewBankBranch(absInfoServiceDataBankBranch);
        String uuid = AbsInfoServiceDataBaseRequest.getNewBankBranchId(bankBranchNumber);

        assertAll(
                () -> assertEquals(SC_OK,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),

                () -> assertEquals(response.jsonPath().get("message"), ("Филиал c UUID {" + uuid + "} добавлен"),
                        "Сообщение ответа не соответствует ожидаемому"),
                () -> assertEquals(AbsInfoServiceDataBaseRequest.getNewBankBranchUuid(uuid), uuid,
                        "Филиал с таким uuid ненайден в БД ")
        );
    }

    @DisplayName("[IS-3] [STATUS CODE 400] (POST) Добавление уже существующего филиала банка")
    @Description("Данный тест-кейс направлен на проверку [IS-3] [STATUS CODE 400] (POST) Добавление уже существующего филиала банка")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB4-927")
    @Test
    public void unsuccessfulAddingNewBankBranch() {
        String bankBranchNumber = "1234";
        AbsInfoServiceDataBaseRequest.deleteNewBankBranch(bankBranchNumber);
        AbsInfoServiceDataBankBranch absInfoServiceDataBankBranch = new AbsInfoServiceDataBankBranch(bankBranchNumber, "Россия",
                "Московская область", "Балашиха", "Вернадского", "51", "117996", "55.696224, 37.544534", true, "+74955554455",
                false, "00:01", "23:59", true, true, true, true, true, true, true, false, true, "141414141", "142458873",
                "4212121214", "40702810562000000000", "30101810400000000225", "АО «Liberty Bank»", "0961047777",
                "1027700057413", "LIBBRUMM007");
        assertEquals(SC_OK, absInfoService.checkAddNewBankBranch(absInfoServiceDataBankBranch).statusCode(),
                "Код ответа не соответствует ожидаемому");
        Response response = absInfoService.checkAddNewBankBranch(absInfoServiceDataBankBranch);
        assertAll(
                () -> assertEquals(SC_BAD_REQUEST,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertEquals(response.jsonPath().get("message"), ("Филиал с номером:{" + bankBranchNumber + "} уже существует"),
                        "Сообщение ответа не соответствует ожидаемому")
        );
    }

    @DisplayName("[IS-3] [STATUS CODE 404] (POST) Ошибка в URL'e")
    @Description("Данный тест-кейс направлен на проверку [IS-3] [STATUS CODE 404] (POST) Ошибка в URL'e")
    @Tag("API")
    @TmsLink("https://jira.astondevs.ru/browse/LIB4-929")
    @Test
    public void unsuccessfulAddingNewBankBranchWithInvalidUrl() {
        AbsInfoServiceDataBankBranch absInfoServiceDataBankBranch = new AbsInfoServiceDataBankBranch("1234", "Россия",
                "Московская область", "Балашиха", "Вернадского", "51", "117996", "55.696224, 37.544534", true, "+74955554455",
                false, "00:01", "23:59", true, true, true, true, true, true, true, false, true, "141414141", "142458873",
                "4212121214", "40702810562000000000", "30101810400000000225", "АО «Liberty Bank»", "0961047777",
                "1027700057413", "LIBBRUMM007");
        Response response = absInfoService.checkInvalidURLAddNewBankBranch(absInfoServiceDataBankBranch);
        assertAll(
                () -> assertEquals(SC_NOT_FOUND,
                        response.statusCode(),
                        "Код ответа не соответствует ожидаемому"),
                () -> assertEquals(response.jsonPath().get("error"), ("Not Found"),
                        "Сообщение ответа не соответствует ожидаемому")
        );
    }
}