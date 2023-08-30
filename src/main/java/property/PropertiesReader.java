package property;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

/**
 * Считыватель параметров из файла config.properties
 */
public final class PropertiesReader {

    private final static String CONFIG_PROPERTIES = "config.properties";
    private static Properties properties;
    private static PropertiesReader instance;
    private static String propsFileName;

    private PropertiesReader() {
    }

    public static PropertiesReader getInstance() {
        if (instance == null) {
            instance = new PropertiesReader();
        }
        return instance;
    }

    public static String getPropertyValue(String name) {
        return getPropertyValue(name, CONFIG_PROPERTIES);
    }

    public static Boolean getBooleanValue(String name, String propertiesFileName) {
        return Boolean.parseBoolean(PropertiesReader.getPropertyValue(name, propertiesFileName));
    }

    public static Boolean getBooleanValue(String name) {
        return Boolean.parseBoolean(PropertiesReader.getPropertyValue(name, CONFIG_PROPERTIES));
    }

    public static Long getLongValue(String name, String propertiesFileName) {
        return Long.parseLong(PropertiesReader.getPropertyValue(name, propertiesFileName));
    }

    public static Long getLongValue(String name) {
        return Long.parseLong(PropertiesReader.getPropertyValue(name, CONFIG_PROPERTIES));
    }

    public static int getIntValue(String name) {
        return Integer.parseInt(PropertiesReader.getPropertyValue(name, CONFIG_PROPERTIES));
    }

    private static String getPropertyValue(String name, String propertiesFileName) {
        if (System.getProperty(name) != null) {
            return System.getProperty(name);
        }
        return getInstance().getValueFromConfigFile(name, propertiesFileName);
    }

    private String getValueFromConfigFile(String key, String propertiesFileName) {
        if (properties == null || !propsFileName.equals(propertiesFileName)) {
            properties = loadConfigFile(propertiesFileName);
            propsFileName = propertiesFileName;
        }

        Object objFromFile = properties.getProperty(key);
        if (objFromFile != null) {
            return Objects.toString(objFromFile);
        } else {
            return null;
        }
    }

    private Properties loadConfigFile(String propertiesFileName) {
        try {
            Properties prop = new Properties();
            prop.load(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(propertiesFileName)));
            return prop;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}