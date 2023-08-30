import java.util.ArrayList;
import java.util.List;

import api.core.RequestParam;
import lombok.Data;

/**
 * Базовый тестовый класс
 */
@Data
public class BaseTest {
    protected List<RequestParam> params;

    public BaseTest() {
        params = new ArrayList<>();
    }

//    public void addParams(String key, String value) {
//        params.add(key, value);
//    }
//
//    public void addHeaders(String key, String value) {
//        headers.add(key, value);
//    }
}