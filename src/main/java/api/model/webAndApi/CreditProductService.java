package api.model.webAndApi;

import api.model.webAndApi.credit.CreditProduct;
import io.restassured.RestAssured;
import lombok.Getter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static constant.CreditServiceConstants.COUNT_CREDITS_PRODUCT;
import static web.constans.CreditServiceConstants.BASE_URL_API;

public class CreditProductService {

    @Getter
    private CreditProduct creditProduct;
    @Getter
    private List<CreditProduct> creditProductList;

    public void getUsersFromPage( ) {
        creditProductList = new ArrayList<CreditProduct>();

        for (int i = 1; i <= COUNT_CREDITS_PRODUCT; i++){
            creditProduct = RestAssured.given()
                    .baseUri(BASE_URL_API)
                    .when()
                    .get("/credits/api/v1/credit-product/" + i)
                    .as(CreditProduct.class);
            creditProductList.add(creditProduct);
        }
    }
}
