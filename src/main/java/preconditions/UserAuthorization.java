package preconditions;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.customerService_2_0.UserAuthorizationByPhone;
import service.CustomerService_2_0;

import static property.BaseProperties.CUSTOMER_SERVICE_2_0;

public class UserAuthorization {
    {
        RestAssured.baseURI = CUSTOMER_SERVICE_2_0;
    }

    CustomerService_2_0 customerService_2_0 = new CustomerService_2_0();

    public String getAccessToken(String mobilePhone, String password, String type) {
        Response getAccessToken = customerService_2_0.userAuthorizationByMobilePhone
                (new UserAuthorizationByPhone(mobilePhone, password, type));
        return getAccessToken.jsonPath().get("accessToken");
    }

    public String getRefreshToken(String mobilePhone, String password, String type) {
        Response getRefreshToken = customerService_2_0.userAuthorizationByMobilePhone
                (new UserAuthorizationByPhone());
        return getRefreshToken.jsonPath().get("refreshToken");
    }
}
