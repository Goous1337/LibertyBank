package api.model.webAndApi;

import api.model.webAndApi.credit.CreditProduct;
import api.model.webAndApi.credit.MoreCreditProduct;
import api.model.webAndApi.credit.MyCreditMoreInformation;
import io.restassured.RestAssured;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static constant.CreditServiceConstants.COUNT_CREDITS_PRODUCT;
import static property.BaseProperties.ACCESS_TOKEN_CUSTOMER_SERVICE;
import static web.constans.credit.CreditServiceConstants.BASE_URL_API;

@Getter
@Setter
public class CreditProductService {
    @Getter
    private MoreCreditProduct moreCreditProduct;
    @Getter
    private MyCreditMoreInformation myCreditMoreInformation;
    @Getter
    private List<MyCreditMoreInformation> myCreditMoreInformationList;
    @Getter
    private List<MoreCreditProduct> moreCreditProductList;
    @Getter
    private List<CreditProduct> creditProductsList;

    public void getMoreProductCredit() {
        moreCreditProductList = new ArrayList<MoreCreditProduct>();
        for (int i = 1; i <= COUNT_CREDITS_PRODUCT; i++) {
            moreCreditProduct = RestAssured.given()
                    .baseUri(BASE_URL_API)
                    .when()
                    .get("/credit/api/v1/products/" + i)
                    .as(MoreCreditProduct.class);
            moreCreditProductList.add(moreCreditProduct);
        }
    }

    public void getMoreInformationAboutMyCreditTwo() {
        myCreditMoreInformationList = RestAssured.given()
                .header("Authorization", ACCESS_TOKEN_CUSTOMER_SERVICE)
                .baseUri(BASE_URL_API)
                .when()
                .get("credits/api/v1/credit/current?id=2")
                .then()
                .log()
                .all()
                .extract().body().jsonPath().getList(".", MyCreditMoreInformation.class);
    }

    public void getMoreInformationAboutMyCredit() {
        myCreditMoreInformationList = new ArrayList<MyCreditMoreInformation>();
        myCreditMoreInformation = RestAssured.given()
                .header("Authorization", ACCESS_TOKEN_CUSTOMER_SERVICE)
                .baseUri(BASE_URL_API)
                .when()
                .get("credit/api/v1/credits/13")
                .as(MyCreditMoreInformation.class);
        myCreditMoreInformationList.add(myCreditMoreInformation);

    }

    public void getProductsCredit() {
        creditProductsList = RestAssured.given()
                .header("Authorization", ACCESS_TOKEN_CUSTOMER_SERVICE)
                .baseUri(BASE_URL_API)
                .when()
                .get("credit/api/v1/products")
                .then()
                .log()
                .all()
                .extract().body().jsonPath().getList(".", CreditProduct.class);
    }

}
