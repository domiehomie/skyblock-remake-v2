package live.mufin.skyblock.items;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import live.mufin.skyblock.Main;

public class ItemDataManager {
	private Main plugin;
	private FileConfiguration playerDataConfig = null;
	private File configFile = null;

	public ItemDataManager(Main plugin) {
		this.plugin = plugin;
		this.saveDefaultConfig();
	}

	/**
	 * Reloads the file
	 */
	public void reloadConfig() {
		if (this.configFile == null)
			this.configFile = new File(this.plugin.getDataFolder(), "items.yml");

		this.playerDataConfig = YamlConfiguration.loadConfiguration(this.configFile);

		InputStream defaultStream = this.plugin.getResource("items.yml");
		if (defaultStream != null) {
			YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
			this.playerDataConfig.setDefaults(defaultConfig);
		}
	}

	/**
	 * @return the file
	 */
	public FileConfiguration getConfig() {
		if (playerDataConfig == null)
			this.reloadConfig();

		return this.playerDataConfig;
	}

	/**
	 * Saves the config.
	 */
	public void saveConfig() {
		if (this.playerDataConfig == null || this.configFile == null)
			return;

		try {
			this.getConfig().save(this.configFile);
		} catch (IOException e) {
			this.plugin.getLogger().warning("Error saving config.");
		}
	}

	/**
	 * Saves default config values
	 */
	public void saveDefaultConfig() {
		if (this.configFile == null)
			this.configFile = new File(this.plugin.getDataFolder(), "items.yml");

		if (!this.configFile.exists()) {
			this.plugin.saveResource("items.yml", false);
		}
	}

}
