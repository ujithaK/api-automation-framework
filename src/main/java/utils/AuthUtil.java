package utils;

import config.ConfigManager;

public class AuthUtil {

    public static String getToken() {
        return ConfigManager.get("auth.token");
    }

    public static String[] basicAuth() {
        String username = ConfigManager.get("auth.username");
        String password = ConfigManager.get("auth.password");
        if (username != null && password != null) {
            return new String[]{username, password};
        }
        return null;
    }

}
