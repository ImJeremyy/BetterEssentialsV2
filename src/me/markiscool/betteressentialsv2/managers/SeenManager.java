package me.markiscool.betteressentialsv2.managers;

import me.markiscool.betteressentialsv2.BetterEssentialsV2Plugin;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SeenManager {

    private BetterEssentialsV2Plugin plugin;

    private File file;
    private FileConfiguration config;

    private Map<OfflinePlayer, Long> players;

    public SeenManager(final BetterEssentialsV2Plugin plugin) {
        this.plugin = plugin;
        players = new HashMap<>();
        createFile();
        pull();
    }

    public OfflinePlayer getOfflinePlayer(final String username) {
        for(final Map.Entry<OfflinePlayer, Long> entry : players.entrySet()) {
            if(entry.getKey().getName().equalsIgnoreCase(username)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public long getTimeStamp(final OfflinePlayer player) {
        return players.get(player);
    }

    public void log(Player player) {
        players.remove(player);
        players.put(player, System.currentTimeMillis());
    }

    public void push() {
        config.set("seen", null);
        config.createSection("seen");
        final ConfigurationSection sec = config.getConfigurationSection("seen");
        for(final Map.Entry<OfflinePlayer, Long> entry : players.entrySet()) {
            sec.set(entry.getKey().getUniqueId().toString(), entry.getValue());
            sec.set(entry.getKey().getUniqueId().toString() + ".username", entry.getKey().getName());
        }
    }

    public void pull() {
        players.clear();
        final ConfigurationSection sec = config.getConfigurationSection("seen");
        for(String u : sec.getKeys(false)) {
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(UUID.fromString(u));
            long timestamp = sec.getLong(u);
            if(offlinePlayer != null) players.put(offlinePlayer, timestamp);
        }
    }

    private void createFile() {
        file = new File(plugin.getDataFolder(), "seen.yml");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch(IOException ex) {
                plugin.getLogger().warning("seen.yml could not be created");
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
        if(!config.contains("seen")) {
            config.createSection("seen");
            save();
        }
    }

    private void save() {
        try {
            config.save(file);
        } catch (IOException ex) {
            plugin.getLogger().warning("seen.yml could not be saved.");
        }
    }


}
