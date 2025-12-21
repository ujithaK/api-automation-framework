package config;

import java.util.Properties;

public class ConfigManager {
    private static Properties props = new Properties();

    static {
        try {
            props.load(ConfigManager.class
                    .getClassLoader()
                    .getResourceAsStream("config.properties"));
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config");
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}
