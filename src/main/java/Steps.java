import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Steps {

        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON).build();

        public Response openPageTest(){
            return given()
                    .spec(requestSpecification)
                    .when()
                    .get("https://www.onliner.by/")
                    .then()
                    .statusCode(200)
                    .extract().response();
        }

        public Response findIpTest(){
            return given()
                    .spec(requestSpecification)
                    .when()
                    .queryParam("query", "ip")
                    .get("https://www.onliner.by/sdapi/catalog.api/search/schemas")
                    .then()
                    .statusCode(200)
                    .extract().response();
        }
    }

