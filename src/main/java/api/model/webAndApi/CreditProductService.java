package api.model.webAndApi;

import api.model.webAndApi.credit.CreditProduct;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static web.constans.CreditServiceConstants.BASE_URL_API;

public class CreditProductService {

    public CreditProduct creditProduct;

    public void getUsersFromPage( ) {
        creditProduct = RestAssured.given()
                .baseUri(BASE_URL_API) //""
                .when()
                .get("/credits/api/v1/credit-product/1")
                .as(CreditProduct.class);
    }
}
