package api.creditService;

import api.BaseTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;

import static property.BaseProperties.CREDIT_SERVICE;

@DisplayName("CM 3.6 Получение подробной информации по кредитному продукту банка")
public class CM_3_6_CheckObtainingInformationOnBanksLoanProductTest extends BaseTest {
    {
        RestAssured.baseURI = CREDIT_SERVICE;
    }
}
