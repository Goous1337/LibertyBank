package property;

import static property.PropertiesReader.getPropertyValue;

public final class BaseProperties {

    public static String BASE_URL = getPropertyValue("base_url");
}