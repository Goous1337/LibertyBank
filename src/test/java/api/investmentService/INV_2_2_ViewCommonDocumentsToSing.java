package api.investmentService;

import api.BaseTest;
import api.utils.PdfCompare;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pojo.investmentService.ErrorResponse;

import static api.utils.JsonParser.parseJson;
import static constant.InvestmentConstants.BROKERAGE_REGULATIONS;
import static constant.InvestmentConstants.RISK_DECLARATIONS;
import static org.apache.hc.core5.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.hc.core5.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static property.BaseProperties.ACCESS_TOKEN_INVESTMENT_SERVICE;
import static property.BaseProperties.INVESTMENT_SERVICE;
import static service.InvestmentService.getCommonDocsRequest;

@Tags({@Tag("API"), @Tag("2.0")})
@DisplayName("INV-2.2 Просматривать документы общие для всех клиентов")
public class INV_2_2_ViewCommonDocumentsToSing extends BaseTest {
    private static final String JSON_DOC_NOT_FOUND = "/jsons/investmentJsons/documentNotFound.json";
    private static final String PDF_BROKERAGE_REGULATIONS = "src/test/resources/pdf/investmentPdf/REGULATIONS_OF_BROKERAGE_SERVISES.pdf";
    private static final String PDF_RISK_DECLARATIONS = "src/test/resources/pdf/investmentPdf/RISK_DECLARATIONS.pdf";

    {
        RestAssured.baseURI = INVESTMENT_SERVICE;
    }

    @Test
    @TmsLink("LIB6-411")
    @DisplayName("Просматривать документы общие для всех клиентов, регламент брокерского обслуживания")
    @Description("Данный тест-кейс проверяет работу запроса данных пользователя для анкеты, при запросе данных с невалидным customerId")
    public void getBrokerageRegulations() {
        Response response = getCommonDocsRequest(BROKERAGE_REGULATIONS, ACCESS_TOKEN_INVESTMENT_SERVICE, SC_OK);
        boolean pdfEqual = PdfCompare.isCommonPdfEquals(response, PDF_BROKERAGE_REGULATIONS);
        assertTrue(pdfEqual, "PDF документ не соответствует ожидаемому");
    }

    @Test
    @TmsLink("LIB6-410")
    @DisplayName("Просматривать документы общие для всех клиентов, декларация о рисках")
    @Description("Данный тест-кейс проверяет возможность просмотра декларации о рисках")
    public void getRiskDeclarations() {
        Response response = getCommonDocsRequest(RISK_DECLARATIONS, ACCESS_TOKEN_INVESTMENT_SERVICE, SC_OK);
        boolean pdfEqual = PdfCompare.isCommonPdfEquals(response, PDF_RISK_DECLARATIONS);
        assertTrue(pdfEqual, "PDF документ не соответствует ожидаемому");
    }

    @ParameterizedTest
    @ValueSource(strings = {"Документ", "Document", "-_.~", "123456"})
    @TmsLink("LIB6-1132")
    @DisplayName("Просматривать документы с для всех пользователей, невалидный входной параметр")
    @Description("Данный тест-кейс проверяет просмотр документов для всех пользователей, с невалидным входным параметром")
    public void getCommonInvalidDoc(String docName) {
        ErrorResponse response = getCommonDocsRequest(docName, ACCESS_TOKEN_INVESTMENT_SERVICE, SC_NOT_FOUND)
                .as(ErrorResponse.class);
        ErrorResponse expectedData = parseJson(ErrorResponse.class, JSON_DOC_NOT_FOUND);
        assertAll(
                () -> assertEquals(response.getType(), expectedData.getType(), "Тип ответа не соответствует ожидаемому"),
                () -> assertEquals(response.getMessage(), expectedData.getMessage(), "Сообщение об ошибке не соответствует ожидаемому"));

    }
}