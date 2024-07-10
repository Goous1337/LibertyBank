package api.model.webAndApi;

import api.model.webAndApi.credit.CreditApplications;
import api.model.webAndApi.credit.CreditProduct;
import io.restassured.RestAssured;
import lombok.Getter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static property.BaseProperties.ACCESS_TOKEN_CUSTOMER_SERVICE;
import static web.constans.credit.CreditServiceConstants.BASE_URL_API;

public class ApplicationsService {
    @Getter
    private List<CreditApplications> creditApplicationsList;


    public void getCreditApplications() {
     //   creditApplicationsList = new ArrayList<CreditApplications>();
        creditApplicationsList = RestAssured.given()
                .header("Authorization", ACCESS_TOKEN_CUSTOMER_SERVICE)
                .baseUri(BASE_URL_API)
                .when()
                .get("credit/api/v1/orders")
                .then()
                .log()
                .all()
                .extract().body().jsonPath().getList(".", CreditApplications.class);

    }
}
