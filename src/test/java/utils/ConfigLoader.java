package utils;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    private static Properties props;

    public static void loadConfig() {
        String env = System.getProperty("env", "stage"); // default = stage
        props = new Properties();

        try {
            props.load(new FileInputStream("src/test/resources/config/" + env + ".properties"));
            System.out.println("Loaded configuration for environment: " + env);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config for environment: " + env, e);
        }
    }

    public static String getProperty(String key) {
        if (props == null) {
            loadConfig();
        }
        return props.getProperty(key);
    }

    // Environment parameters
    public static String getBaseUrl() {
        return getProperty("baseUrl");
    }
    public static String getDeviceName() {
        return getProperty("deviceName");
    }

    public static int getWaitTimeout() {
        return Integer.parseInt(getProperty("waitTimeout"));
    }

    // App info parameters
    public static String getAppPath() {
        return getProperty("appPath");
    }

    public static String getPackageName() {
        return getProperty("packageName");
    }

    public static String getActivityName() {
        return getProperty("activityName");
    }
    // Credentials parameters
    public static String getUsername() {
        return getProperty("username");
    }

    public static String getPassword() {
        return getProperty("password");
    }


}
