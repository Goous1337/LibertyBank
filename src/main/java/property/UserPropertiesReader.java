package property;

public class UserPropertiesReader {

    private static final String PATH_PROPERTIES = "user.properties";

    public static final String USER_PHONE = PropertiesReader.getPropertyValue("user.phoneNum", PATH_PROPERTIES);

    public static final String USER_DOCUMENT_NUM = PropertiesReader.getPropertyValue("user.docNum", PATH_PROPERTIES);

    public static final String USER_PASSWORD = PropertiesReader.getPropertyValue("user.password", PATH_PROPERTIES);
    public static final String USER_PHONE_WITHOUT_BROKERAGE = PropertiesReader.getPropertyValue("user.phoneNumWithoutBrokerage", PATH_PROPERTIES);
}
