package codes.dreaming.fixstacc;

import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.yaml.NodeStyle;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Logger;

public class ConfigHelper {

    private final Logger logger;
    private final Path dataFolder;
    private ConfigurationNode configData;

    public ConfigHelper(Logger logger) {
        this.logger = logger;
        this.dataFolder = Path.of("plugins/fixstac");
    }

    public void loadConfiguration() {
        try {
            if (!Files.exists(dataFolder)) {
                Files.createDirectories(dataFolder);
            }

            Path configFile = dataFolder.resolve("config.yml");
            YamlConfigurationLoader loader =
                    YamlConfigurationLoader.builder()
                            .path(configFile)
                            .nodeStyle(NodeStyle.BLOCK)
                            .build();

            if (!Files.exists(configFile)) {
                try (InputStream defaultConfigStream = this.getClass().getResourceAsStream("/config.yml")) {
                    if (defaultConfigStream != null) {
                        Files.copy(defaultConfigStream, configFile);
                    } else {
                        throw new IOException("Default config not found in resources!");
                    }
                }
            }
            configData = loader.load();
        } catch (IOException e) {
            logger.warning("Failed to load config.yml: " + e.getMessage());
        }
    }

    public List<String> getStaccServers() throws SerializationException {
        return configData.node("staccServers").getList(String.class);
    }
}
