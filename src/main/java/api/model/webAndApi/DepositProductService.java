package api.model.webAndApi;

import api.model.webAndApi.deposit.DepositProduct;

import api.model.webAndApi.deposit.MyDepositProduct;
import io.restassured.RestAssured;
import lombok.Getter;
import lombok.Setter;
import web.constans.DepositsConstants;

import java.util.List;

@Getter
@Setter
public class DepositProductService {
    private MyDepositProduct myDepositProduct;
    private List<DepositProduct> depositProductList;
    private List<MyDepositProduct> myDepositProductsList;

    public void getDepositsFromPage() {
        depositProductList = RestAssured.given()
                .header("Authorization", DepositsConstants.VALID_ACCESS_TOKEN)
                .baseUri(DepositsConstants.BASE_URL_DEPOSIT_API)
                .when()
                .get("/deposits/api/v1/deposit-product")
                .then()
                .log()
                .all()
                .extract().body().jsonPath().getList(".", DepositProduct.class);
    }

    public void getMyDepositsFromPage() {
        myDepositProductsList = RestAssured.given()
                .header("Authorization", DepositsConstants.VALID_ACCESS_TOKEN)
                .baseUri(DepositsConstants.BASE_URL_DEPOSIT_API)
                .when()
                .get("/deposits/api/v1/deposit")
                .then()
                .log()
                .all()
                .extract().body().jsonPath().getList(".", MyDepositProduct.class);
    }
}
