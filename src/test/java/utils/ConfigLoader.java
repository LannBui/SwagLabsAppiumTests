package utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigLoader {
    private static Properties props;

    public static void loadConfig() {
        String env = System.getProperty("env");
        if (env == null || env.isBlank()) {
            String envVar = System.getenv("ENV");
            env = (envVar == null || envVar.isBlank()) ? "stage" : envVar;
        }
        props = new Properties();
        String configPath = "src/test/resources/config/" + env + ".properties";
        try (FileInputStream fis = new FileInputStream(new File(configPath))) {
            props.load(fis);
            System.out.println("Loaded configuration for environment: " + env);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config for environment: " + env + ". " + e.getMessage(), e);
        }
    }

    public static String getProperty(String key) {
        if (props == null) {
            loadConfig();
        }
        String value = props.getProperty(key);
        if (value == null) {
            throw new IllegalStateException("Missing required config property: " + key);
        }
        return value;
    }

    public static String getOptionalProperty(String key) {
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

    public static Long getAdbExecTimeoutMs() {
        String val = getOptionalProperty("adbExecTimeoutMs");
        if (val == null || val.isBlank()) return null;
        return Long.parseLong(val);
    }

    // App info parameters
    public static String getAppPath() {
        String appPath = getProperty("appPath");
        if (appPath.startsWith("http://") || appPath.startsWith("https://")) {
            return appPath;
        }
        // Resolve relative paths against project root
        Path path = Paths.get(appPath);
        if (!path.isAbsolute()) {
            path = Paths.get("").toAbsolutePath().resolve(appPath).normalize();
        }
        File apk = path.toFile();
        if (!apk.exists()) {
            throw new IllegalStateException("App file does not exist at resolved path: " + apk.getAbsolutePath());
        }
        return apk.getAbsolutePath();
    }

    public static String getPackageName() {
        return getProperty("packageName");
    }

    public static String getActivityName() {
        return getProperty("activityName");
    }

    public static String getUdid() {
        return getOptionalProperty("udid");
    }
    // Credentials parameters
    public static String getUsername() {
        String override = System.getProperty("username");
        return (override != null && !override.isBlank()) ? override : getProperty("username");
    }

    public static String getPassword() {
        String override = System.getProperty("password");
        return (override != null && !override.isBlank()) ? override : getProperty("password");
    }


}
