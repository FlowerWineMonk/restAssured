package config;

import org.apache.logging.log4j.Logger;
import utils.LoggerHelper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static final Logger logger = LoggerHelper.getLogger(ConfigReader.class);
    private static final Properties properties = new Properties();
    private static final String CONFIG_FILE_PATH = "src/test/resources/config.properties";

    static {
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE_PATH)) {
            properties.load(fis);
            logger.info("✅ Loaded configuration from {}", CONFIG_FILE_PATH);
        } catch (IOException e) {
            logger.error("❌ Failed to load configuration file: {}", CONFIG_FILE_PATH, e);
            throw new RuntimeException("Could not load config file: " + CONFIG_FILE_PATH, e);
        }
    }

    public static String get(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            logger.warn("⚠️ Missing configuration key: {}", key);
        } else {
            logger.debug("Loaded config: {} = {}", key, value);
        }
        return value;
    }
}
