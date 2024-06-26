package api.investmentService;

import api.BaseTest;
import api.utils.PdfCompare;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pojo.investmentService.ErrorResponse;

import static api.utils.JsonParser.parseJson;
import static constant.InvestmentConstants.*;
import static org.apache.hc.core5.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.hc.core5.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.*;
import static property.BaseProperties.ACCESS_TOKEN_INVESTMENT_SERVICE;
import static property.BaseProperties.INVESTMENT_SERVICE;
import static service.InvestmentService.*;

@Tags({@Tag("API"), @Tag("2.0")})
@DisplayName("INV-2.1 Просматривать документы для подписания")
public class INV_2_1_ViewPersonalDocumentsToSign extends BaseTest {
    private static final String JSON_DOC_NOT_FOUND = "/jsons/investmentJsons/documentNotFound.json";
    private static final String JSON_CUSTOMER_NOT_FOUND = "/jsons/investmentJsons/invalidCustomerId.json";
    private static final String PDF_APPLICATION_FOR_BROKERAGE_SERVICE = "src/test/resources/pdf/investmentPdf/APPLICATION_FOR_BROKERAGE_SERVICE.pdf";
    private static final String PDF_DEPOSIT_AGREEMENT = "src/test/resources/pdf/investmentPdf/DEPOSIT_AGREEMENT.pdf";
    private static final String PDF_PROCESSING_OF_PERSONAL_DATA = "src/test/resources/pdf/investmentPdf/PROCESSING_OF_PERSONAL_DATA.pdf";

    {
        RestAssured.baseURI = INVESTMENT_SERVICE;
    }

    @Test
    @TmsLink("LIB6-1135")
    @DisplayName("Просматривать заявление на брокерское обслуживание с данными клиента")
    @Description("Данный тест-кейс проверяет просмотр заявления на брокерское обслуживание с данными клиента")
    public void getBrokerageService() {
        Response response = getPersonalDocsRequest(APPLICATION_FOR_BROKERAGE_SERVICE, ACCESS_TOKEN_INVESTMENT_SERVICE, SC_OK);
        boolean pdfEqual = PdfCompare.isPdfEqualsIgnoringDates(response, PDF_APPLICATION_FOR_BROKERAGE_SERVICE);
        assertTrue(pdfEqual, "PDF документ не соответствует ожидаемому");
    }

    @Test
    @TmsLink("LIB6-1136")
    @DisplayName("Просматривать депозитарный договор с данными клиента")
    @Description("Данный тест-кейс проверяет просмотр депозитарного договора с данными клиента")
    public void getDepositAgreement() {
        Response response = getPersonalDocsRequest(APPLICATION_FOR_DEPOSIT_AGREEMENT, ACCESS_TOKEN_INVESTMENT_SERVICE, SC_OK);
        boolean pdfEqual = PdfCompare.isPdfEqualsIgnoringDates(response, PDF_DEPOSIT_AGREEMENT);
        assertTrue(pdfEqual, "PDF документ не соответствует ожидаемому");
    }

    @Test
    @TmsLink("LIB6-1137")
    @DisplayName("Просматривать согласие на обработку персональных данных с данными клиента")
    @Description("Данный тест-кейс проверяет просмотр согласия на обработку персональных данных с данными клиента")
    public void getProcessingOfPersonalData() {
        Response response = getPersonalDocsRequest(CONSENT_TO_THE_PROCESSING_OF_PERSONAL_DATA, ACCESS_TOKEN_INVESTMENT_SERVICE, SC_OK);
        boolean pdfEqual = PdfCompare.isPdfEqualsIgnoringDates(response, PDF_PROCESSING_OF_PERSONAL_DATA);
        assertTrue(pdfEqual, "PDF документ не соответствует ожидаемому");
    }

    @ParameterizedTest
    @ValueSource(strings = {"Документ", "Document", "-_.~", "123456"})
    @TmsLink("LIB6-1132")
    @DisplayName("Просматривать документы с данными клиента, невалидный входной параметр")
    @Description("Данный тест-кейс проверяет просмотр документов с данными клиента, с невалидным входным параметром")
    public void getPersonalInvalidDoc(String docName) {
        ErrorResponse response = getPersonalDocsRequest(docName, ACCESS_TOKEN_INVESTMENT_SERVICE, SC_NOT_FOUND)
                .as(ErrorResponse.class);
        ErrorResponse expectedData = parseJson(ErrorResponse.class, JSON_DOC_NOT_FOUND);
        assertAll(
                () -> assertEquals(response.getType(), expectedData.getType(), "Тип ответа не соответствует ожидаемому"),
                () -> assertEquals(response.getMessage(), expectedData.getMessage(), "Сообщение об ошибке не соответствует ожидаемому"));

    }

    @ParameterizedTest
    @ValueSource(strings = {APPLICATION_FOR_BROKERAGE_SERVICE, APPLICATION_FOR_DEPOSIT_AGREEMENT, CONSENT_TO_THE_PROCESSING_OF_PERSONAL_DATA})
    @TmsLink("LIB6-1125")
    @DisplayName("Просматривать документы с данными клиента, не найден customerId")
    @Description("Данный тест-кейс проверяет просмотр документов с данными клиента, при запросе данных с невалидным customerId")
    public void getPersonalDocInvalidCustomerId(String docName) {
        ErrorResponse response = getPersonalDocsRequest(docName, ACCESS_TOKEN_INVALID_CUSTOMER_ID, SC_NOT_FOUND)
                .as(ErrorResponse.class);
        ErrorResponse expectedData = parseJson(ErrorResponse.class, JSON_CUSTOMER_NOT_FOUND);
        assertAll(
                () -> assertEquals(response.getType(), expectedData.getType(), "Тип ответа не соответствует ожидаемому"),
                () -> assertEquals(response.getMessage(), expectedData.getMessage(), "Сообщение об ошибке не соответствует ожидаемому"));
    }
}