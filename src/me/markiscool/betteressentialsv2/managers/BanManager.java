package me.markiscool.betteressentialsv2.managers;

import me.markiscool.betteressentialsv2.BetterEssentialsV2Plugin;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;

public class BanManager {

    private BetterEssentialsV2Plugin plugin;

    private File file;
    private FileConfiguration config;

    public BanManager(final BetterEssentialsV2Plugin plugin) {
        this.plugin = plugin;
    }

    private void createFile() {
        file = new File(plugin.getDataFolder(), "bans.yml");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                
            }
        }
    }

    private void save() {

    }

}
