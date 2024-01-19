package web.constans;

import property.WebPropertiesReader;

public class UrlConfig {

    public static final String BASE_URL = WebPropertiesReader.getWebBaseUrl();
    public static final String ACCOUNTS_URL = "/my-bills";
}
