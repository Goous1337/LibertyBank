import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LivCod {

    Steps steps = new Steps();

    @Test
    public void openPageTest(){
        int statusCode = steps.openPageTest().then().extract().statusCode();
        Assertions.assertEquals("200", statusCode);
    }

    @Test
    public void findIpTest() {
        int statusCode = steps.openPageTest().then().extract().statusCode();
        Assertions.assertEquals("200", statusCode);
    }
}
