package api.model.webAndApi;

import api.model.webAndApi.credit.CreditProduct;
import api.model.webAndApi.credit.MoreCreditProduct;
import io.restassured.RestAssured;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static constant.CreditServiceConstants.COUNT_CREDITS_PRODUCT;
import static web.constans.CreditServiceConstants.BASE_URL_API;

@Getter
public class CreditProductService {
    private MoreCreditProduct moreCreditProduct;
    private List<MoreCreditProduct> moreCreditProductList;
    private List<CreditProduct> creditProductsList;

    public void getUsersFromPage() {
        moreCreditProductList = new ArrayList<MoreCreditProduct>();
        for (int i = 1; i <= COUNT_CREDITS_PRODUCT; i++) {
            moreCreditProduct = RestAssured.given()
                    .baseUri(BASE_URL_API)
                    .when()
                    .get("/credits/api/v1/credit-product/" + i)
                    .as(MoreCreditProduct.class);
            moreCreditProductList.add(moreCreditProduct);
        }
    }

    public void getProductCredit() {
        creditProductsList = RestAssured.given()
                .baseUri(BASE_URL_API)
                .when()
                .get("credits/api/v1/credit-product")
                .then()
                .log()
                .all()
                .extract().body().jsonPath().getList(".", CreditProduct.class);

    }

}
