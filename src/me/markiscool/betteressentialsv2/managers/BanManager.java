package me.markiscool.betteressentialsv2.managers;

import me.markiscool.betteressentialsv2.BetterEssentialsV2Plugin;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class BanManager {

    private BetterEssentialsV2Plugin plugin;

    private File file;
    private FileConfiguration config;

    //master ban List<>
    private List<UUID> banned;
    private Map<UUID, String> reasons; //reasons

    //tempbanned
    private Map<UUID, Long> timestamps; //timestamps
    private Map<UUID, Long> lengths; //lengths

    public BanManager(final BetterEssentialsV2Plugin plugin) {
        this.plugin = plugin;
        banned = new ArrayList<>();
        reasons = new HashMap<>();
        timestamps = new HashMap<>();
        lengths = new HashMap<>();
        createFile();
        pull();
        registerTempbanScheduler();
    }

    public void ban(final UUID uuid, final String reason) {
        banned.add(uuid);
        reasons.put(uuid, reason);
    }

    public void unban(final UUID uuid) {
        banned.remove(uuid);
        reasons.remove(uuid);
        timestamps.remove(uuid);
        lengths.remove(uuid);
    }

    public boolean contains(final UUID uuid) {
        return banned.contains(uuid);
    }

    public boolean containsTempban(final UUID uuid) {
        return timestamps.containsKey(uuid) && lengths.containsKey(uuid);
    }

    public void tempban(final UUID uuid, final String reason, long length) {
        ban(uuid, reason);
        timestamps.put(uuid, System.currentTimeMillis());
        lengths.put(uuid, length);
    }

    public long getTempbanTimeLeft(final UUID uuid) {
        return lengths.get(uuid) - (System.currentTimeMillis() - timestamps.get(uuid));
    }

    public String getReason(final UUID uuid) {
        return reasons.get(uuid);
    }

    public void push() {
        config.set("bans", null);
        for(final UUID uuid : banned) {
            config.set("bans." + uuid.toString() + ".reason", reasons.get(uuid));
            if(containsTempban(uuid)) {
                config.set("bans." + uuid.toString() + ".tempban.timestamp", timestamps.get(uuid));
                config.set("bans." + uuid.toString() + ".tempban.length", lengths.get(uuid));
            }
        }
        save();
    }

    public void pull() {
        banned.clear();
        timestamps.clear();
        lengths.clear();
        for(final UUID uuid : config.getConfigurationSection("bans").getKeys(false).stream().map(s -> UUID.fromString(s)).collect(Collectors.toList())) {
            if(config.contains("bans." + uuid.toString() + ".tempban")) {
                pullTempban(uuid, config.getString("bans." + uuid.toString() + ".reason"), config.getLong("bans." + uuid.toString() + ".tempban.length"), config.getLong("bans." + uuid.toString() + ".tempban.timestamp"));
            } else {
                ban(uuid, config.getString("bans." + uuid.toString() + ".reason"));
            }
        }
    }

    private void registerTempbanScheduler() {
        Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {
            @Override
            public void run() {
                for(Map.Entry<UUID, Long> entry : lengths.entrySet()) {
                    final UUID uuid = entry.getKey();
                    final long length = entry.getValue();
                    if(getTempbanTimeLeft(uuid) <= 0) {
                        unban(uuid);
                    }
                }
            }
        },40, 20);
    }

    private void createFile() {
        file = new File(plugin.getDataFolder(), "bans.yml");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                plugin.getLogger().warning("bans.yml could not be created");
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
        if(!config.contains("bans")) {
            config.createSection("bans");
            save();
        }
    }

    private void save() {
        try {
            config.save(file);
        } catch(IOException ex) {
            plugin.getLogger().warning("bans.yml could not be saved");
        }
    }

    private void pullTempban(final UUID uuid, final String reason, final long length, final long timestamp) {
        ban(uuid, reason);
        timestamps.put(uuid, timestamp);
        lengths.put(uuid, length);
    }

}
