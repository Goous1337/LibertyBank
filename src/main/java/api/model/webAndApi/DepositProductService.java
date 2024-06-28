package api.model.webAndApi;

import api.model.webAndApi.deposit.DepositProductFullInfo;
import api.model.webAndApi.deposit.DepositProductShortInfo;
import api.model.webAndApi.deposit.MyDepositProduct;
import io.restassured.RestAssured;
import lombok.Getter;
import lombok.Setter;
import web.constans.deposit.DepositsConstants;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DepositProductService {
    private MyDepositProduct myDepositProduct;
    private DepositProductFullInfo depositProductFullInfo;
    private List<DepositProductShortInfo> depositProductShortInfoList;
    private List<MyDepositProduct> myDepositProductsList;
    private List<DepositProductFullInfo> depositProductFullInfoList;

    public void getDepositsFromPage() {
        depositProductShortInfoList = RestAssured.given()
                .header("Authorization", DepositsConstants.VALID_ACCESS_TOKEN)
                .baseUri(DepositsConstants.BASE_URL_DEPOSIT_API)
                .when()
                .get("/deposits/api/v1/deposit-product")
                .then()
                .log()
                .all()
                .extract().body().jsonPath().getList(".", DepositProductShortInfo.class);
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

    public void getDepositProductFullInfo() {
        depositProductFullInfoList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            depositProductFullInfo = RestAssured.given()
                    .header("Authorization", DepositsConstants.VALID_ACCESS_TOKEN)
                    .baseUri(DepositsConstants.BASE_URL_DEPOSIT_API)
                    .when()
                    .get("/deposits/api/v1/deposit-product/" + i)
                    .as(DepositProductFullInfo.class);
            depositProductFullInfoList.add(depositProductFullInfo);
        }
    }
}
