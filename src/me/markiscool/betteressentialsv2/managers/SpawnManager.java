package me.markiscool.betteressentialsv2.managers;

import me.markiscool.betteressentialsv2.BetterEssentialsV2Plugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class SpawnManager {

    private BetterEssentialsV2Plugin plugin;

    private File file;
    private FileConfiguration config;

    private Location spawn;

    public SpawnManager(final BetterEssentialsV2Plugin plugin) {
        this.plugin = plugin;
        createFile();
        pull();
    }

    public Location getSpawn() {
        return spawn;
    }

    public void setSpawn(Location location) {
        this.spawn = location;
    }

    public void push() {
        final String world = spawn.getWorld().getName();
        final double x = spawn.getX();
        final double y = spawn.getY();
        final double z = spawn.getZ();
        final float yaw = spawn.getYaw();
        final float pitch = spawn.getPitch();
        final ConfigurationSection sec = config.getConfigurationSection("spawn");
        sec.set("world", world);
        sec.set("x", x);
        sec.set("y", y);
        sec.set("z", z);
        sec.set("yaw", yaw);
        sec.set("pitch", pitch);
    }

    public void pull() {
        final ConfigurationSection sec = config.getConfigurationSection("spawn");
        if(sec.contains("x")) {
            final World world = Bukkit.getWorld(sec.getString("world"));
            final double x = sec.getDouble("x");
            final double y = sec.getDouble("y");
            final double z = sec.getDouble("z");
            final float yaw = (float) sec.getDouble("yaw");
            final float pitch = (float) sec.getDouble("pitch");
            spawn = new Location(world, x, y, z, yaw, pitch);
        }

    }

    private void createFile() {
        file = new File(plugin.getDataFolder(), "spawn.yml");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                plugin.getLogger().warning("spawn.yml could not be created.");
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
        if(!config.contains("spawn")) {
            config.createSection("spawn");
            save();
        }
    }

    private void save() {
        try {
            config.save(file);
        } catch (IOException ex) {
            plugin.getLogger().warning("spawn.yml could not be saved");
        }
    }

}
