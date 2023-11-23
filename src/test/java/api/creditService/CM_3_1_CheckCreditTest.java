package api.creditService;

import io.restassured.RestAssured;

import static property.BaseProperties.CREDIT_SERVICE;


public class CM_3_1_CheckCreditTest {
    {
        RestAssured.baseURI = CREDIT_SERVICE;
    }
}
