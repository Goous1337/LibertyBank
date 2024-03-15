package api.model.webAndApi;

import api.model.webAndApi.deposit.DepositProduct;
import api.model.webAndApi.deposit.DepositProductDetails;

import io.restassured.RestAssured;
import lombok.Getter;
import lombok.Setter;
import web.constans.DepositsConstants;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class DepositProductService {
    private DepositProduct depositProduct;
    private Map<DepositProduct, DepositProductDetails> map;
    private List<DepositProduct> depositProductList;

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
}
