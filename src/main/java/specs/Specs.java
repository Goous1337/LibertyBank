package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;
import static org.apache.hc.core5.http.HttpStatus.*;

public class Specs {
    public static RequestSpecification requestSpec = buildRequestSpec();
    public static ResponseSpecification response201 = buildResponseSpec(SC_CREATED);
    public static ResponseSpecification response400 = buildResponseSpec(SC_BAD_REQUEST);
    public static ResponseSpecification response404 = buildResponseSpec(SC_NOT_FOUND);

    private static RequestSpecification buildRequestSpec() {
        return with()
                .log().all()
                .contentType(JSON);
    }

    private static ResponseSpecification buildResponseSpec(int statusCode) {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)
                .log(LogDetail.BODY)
                .expectStatusCode(statusCode)
                .build();
    }
}