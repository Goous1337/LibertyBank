import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.*;

public class test {

    public static void main(String[] args) {
        // Устанавливаем базовый URL для запросов
        RestAssured.baseURI = "https://www.onliner.by";

        // Выполняем GET-запрос к сайту
        ValidatableResponse response = given()
                .when()
                .queryParam("   query", "value1")
                .get("/sdapi/catalog.api/search/products") // Здесь указываем нужный путь к ресурсу на сайте
                .then()
                .statusCode(200);

        // Выводим содержимое ответа на экран
    }
}