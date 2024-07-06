package api.model.webAndApi;

import api.model.webAndApi.deposit.DepositProductFullInfo;
import api.model.webAndApi.deposit.DepositProductShortInfo;
import api.model.webAndApi.deposit.MyDepositMoreInfo;
import api.model.webAndApi.deposit.MyDepositProduct;
import io.restassured.RestAssured;
import lombok.Getter;
import lombok.Setter;
import web.constans.deposit.DepositsConstants;

import java.util.ArrayList;
import java.util.List;

import static property.BaseProperties.ACCESS_TOKEN_CUSTOMER_SERVICE;
import static web.constans.credit.CreditServiceConstants.BASE_URL_API;

@Getter
@Setter
public class DepositProductService {
    @Getter
    private MyDepositProduct myDepositProduct;
    @Getter
    private MyDepositMoreInfo myDepositMoreInfo;
    @Getter
    private DepositProductFullInfo depositProductFullInfo;
    @Getter
    private List<DepositProductShortInfo> depositProductShortInfoList;
    @Getter
    private List<MyDepositProduct> myDepositProductsList;
    @Getter
    private List<MyDepositMoreInfo> myDepositMoreInfoList;
    @Getter
    private List<DepositProductFullInfo> depositProductFullInfoList;

    public void getDepositsFromPage() {
        depositProductShortInfoList = RestAssured.given()
                .header("Authorization", DepositsConstants.VALID_ACCESS_TOKEN)
                .baseUri(DepositsConstants.BASE_URL_DEPOSIT_API)
                .when()
                .get("/deposit/api/v1/products")
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
                .get("/deposit/api/v1/deposits")
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
                    .get("/deposit/api/v1/products/" + i)
                    .as(DepositProductFullInfo.class);
            depositProductFullInfoList.add(depositProductFullInfo);
        }
    }

    public void getMoreInfoAboutMyDeposit() {
        myDepositMoreInfoList = new ArrayList<MyDepositMoreInfo>();
        myDepositMoreInfo = RestAssured.given()
                .header("Authorization", ACCESS_TOKEN_CUSTOMER_SERVICE)
                .baseUri(BASE_URL_API)
                .when()
                .get("deposit/api/v1/deposits/info?depositId=1559")
                .as(MyDepositMoreInfo.class);
        myDepositMoreInfoList.add(myDepositMoreInfo);
    }
}
