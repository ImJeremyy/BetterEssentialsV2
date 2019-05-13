package me.markiscool.betteressentialsv2.managers;

import me.markiscool.betteressentialsv2.BetterEssentialsV2Plugin;
import me.markiscool.betteressentialsv2.Warp;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class WarpManager {

    private BetterEssentialsV2Plugin plugin;

    private File file;
    private FileConfiguration config;

    private Set<Warp> warps;

    public WarpManager(final BetterEssentialsV2Plugin plugin) {
        this.plugin = plugin;
        createFile();
        warps = new HashSet<>();
        pull();
    }

    public void push() {
        config.set("warps", null);
        config.createSection("warps");
        for(Warp w : warps) {
            w.push(config);
        }
        save();
    }

    public void pull() {
        warps.clear();
        final ConfigurationSection warpsSec = config.getConfigurationSection("warps");
        for(String warpName : warpsSec.getKeys(false)) {
            final ConfigurationSection sec = warpsSec.getConfigurationSection(warpName);
            Warp warp = new Warp(sec);
            add(warp);
        }
    }

    public Warp getWarp(String name) {
        for(Warp w : warps) {
            if(w.getName().equalsIgnoreCase(name)) {
                return w;
            }
        }
        return null;
    }

    public Set<Warp> getWarps() {
        return warps;
    }

    public void add(final Warp warp) {
        warps.add(warp);
    }

    public void remove(final Warp warp) {
        warps.remove(warp);
    }

    public boolean contains(final Warp warp) {
        return warps.contains(warp);
    }

    public boolean containsName(String warpName) {
        for(Warp w : warps) {
            if(w.getName().equalsIgnoreCase(warpName)) {
                return true;
            }
        }
        return false;
    }

    private void createFile() {
        file = new File(plugin.getDataFolder(), "warps.yml");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                plugin.getLogger().warning("warps.yml could not be created.");
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
        if(!config.contains("warps")) {
            config.createSection("warps");
            save();
        }
    }

    private void save() {
        try {
            config.save(file);
        } catch (IOException ex) {
            plugin.getLogger().warning("warps.yml could not be saved.");
        }
    }

}
