package service;

import api.core.RequestParam;
import api.core.RequestParamType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import pojo.GetVerificationCode;
import pojo.Verification;

import java.util.Collections;
import java.util.List;

import static api.core.ApiClient.sendSimpleRequest;
import static api.core.RequestParamType.PARAMETER;
import static constant.ApiEndpoints.GENERATION_VERIFICATION_CODE;
import static constant.ApiEndpoints.VERIFICATION;
import static constant.UserServiceConstants.PARAMETER_MOBILE_PHONE;
import static io.restassured.RestAssured.given;
import static io.restassured.http.Method.PATCH;
import static io.restassured.http.Method.POST;

public class VerificationService {

    private static VerificationService instance;
    private final VerificationService request = VerificationService.getInstance();
    public static final VerificationService VERIFICATION_SERVICE = VerificationService.getInstance();

    public static VerificationService getInstance() {
        if (instance == null) {
            instance = new VerificationService();
        }
        return instance;
    }

    public String getVerificationCode(RequestParamType type, String mobilePhone) {
        Verification verification = new Verification();
        verification.setMobilePhone(mobilePhone);
        verification.setType(type.toString());
        GetVerificationCode response = request.getVerificationCodeResponse(verification)
                .extract().as(GetVerificationCode.class);
        return response.getVerificationCode();
    }

    public ValidatableResponse getVerificationCodeResponse(Verification request) {
        return given().log().body()
                .body(request)
                .when()
                .post(GENERATION_VERIFICATION_CODE.toString())
                .then().log().all();
    }

    public Response verification(String phoneNumber, String verificationCode) {
        List<RequestParam> params = Collections.singletonList(new RequestParam(PARAMETER, PARAMETER_MOBILE_PHONE, phoneNumber));
        return sendSimpleRequest(POST, VERIFICATION, params);
    }
}




