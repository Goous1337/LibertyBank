package property;

public class WebPropertiesReader {

    private static final String PATH_PROPERTIES = "web.properties";

    public static String getWebDriver() {
        return PropertiesReader.getPropertyValue("driver", PATH_PROPERTIES);
    }

    public static String getWebBaseUrl() {
        return PropertiesReader.getPropertyValue("base_url", PATH_PROPERTIES);
    }
}
