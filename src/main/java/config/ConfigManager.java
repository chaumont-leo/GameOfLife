package config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;

public class ConfigManager {
    private static ConfigManager instance;
    private Configuration configuration;

    private ConfigManager() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            this.configuration = mapper.readValue(new File("config.yaml"), Configuration.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public static ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }
}
