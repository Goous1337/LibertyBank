package service;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import pojo.PhoneVerificationRequest;
import pojo.Verification;

import static api.core.ApiClient.sendSimpleRequest;
import static constant.ApiEndpoints.GENERATION_VERIFICATION_CODE;
import static constant.ApiEndpoints.VERIFICATION;
import static io.restassured.RestAssured.given;
import static io.restassured.http.Method.POST;

public class VerificationService {

    public String getVerificationCode(String mobilePhone) {
        Response response = sendSimpleRequest(POST, GENERATION_VERIFICATION_CODE, new Verification(mobilePhone));
        return response.jsonPath().get("verificationCode");
    }

    public ValidatableResponse getVerificationCodeResponse(Verification request) {
        return given().log().body()
                .body(request)
                .when()
                .post(GENERATION_VERIFICATION_CODE)
                .then().log().all();
    }

    public Response verification(String phoneNumber, String verificationCode) {
        return sendSimpleRequest(POST, VERIFICATION,
                new PhoneVerificationRequest(phoneNumber, verificationCode));

//        Либо так
//        String body = GsonHelper.createBody(Map.of(
//                PARAMETER_MOBILE_PHONE, phoneNumber,
//                "code", verificationCode
//        ));
//        List<RequestParam> params = Collections.singletonList(new RequestParam(BODY, EMPTY, body));
//        return sendSimpleRequest(POST, VERIFICATION, params);
    }
}




