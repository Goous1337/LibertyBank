package property;

import static property.PropertiesReader.getPropertyValue;

public final class BaseProperties {

    public static String BASE_URL = getPropertyValue("base_url");
    public static String URL_USER_ACCOUNT_SERVICE = getPropertyValue("user_account_service");
}