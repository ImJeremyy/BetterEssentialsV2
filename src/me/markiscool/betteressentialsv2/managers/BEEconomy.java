package me.markiscool.betteressentialsv2.managers;

import me.markiscool.betteressentialsv2.BetterEssentialsV2Plugin;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class BEEconomy implements Economy {

    private BetterEssentialsV2Plugin plugin;

    private File file;
    private FileConfiguration config;

    private Map<OfflinePlayer, Double> players;

    public BEEconomy(final BetterEssentialsV2Plugin plugin) {
        this.plugin = plugin;
        createFile();
        players = new HashMap<>();
        pull();
    }

    public OfflinePlayer getOfflinePlayer(final String name) {
        for(OfflinePlayer p : players.keySet()) {
            if(p.getName().equalsIgnoreCase(name)) return p;
        }
        return null;
    }

    public void setBalance(final OfflinePlayer targetPlayer, final double amount) {
        final double amountToChange = getBalance(targetPlayer) - amount; //eg: set: 500. Balance = 1200. Amount to change = 1200 - 500
        if(amountToChange < 0) { //balance = 500, set to 2000. 500 - 2000 = -1500. add 1500
            depositPlayer(targetPlayer, Math.abs(amountToChange));
        } else { //balance = 2000, set to 500. Amount to change = 2000 - 500 = 1500. Remove 1500
            withdrawPlayer(targetPlayer, Math.abs(amountToChange));
        }
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return "BEEconomy";
    }

    @Override
    public boolean hasBankSupport() {
        return false;
    }

    @Override
    public int fractionalDigits() {
        return 2;
    }

    @Override
    public String format(double v) {
        return v + " coins";
    }

    @Override
    public String currencyNamePlural() {
        return "coins";
    }

    @Override
    public String currencyNameSingular() {
        return "coin";
    }

    @Override
    @Deprecated
    public boolean hasAccount(String playerName) {
        return getOfflinePlayer(playerName) != null;
    }

    @Override
    public boolean hasAccount(OfflinePlayer offlinePlayer) {
        return players.containsKey(offlinePlayer);
    }

    @Override
    @Deprecated
    public boolean hasAccount(String playerName, String worldName) {
        return hasAccount(playerName);
    }

    @Override
    public boolean hasAccount(OfflinePlayer offlinePlayer, String worldName) {
        return hasAccount(offlinePlayer);
    }

    @Override
    @Deprecated
    public double getBalance(String s) {
        return players.get(getOfflinePlayer(s));
    }

    @Override
    public double getBalance(OfflinePlayer offlinePlayer) {
        return players.get(offlinePlayer);
    }

    @Override
    @Deprecated
    public double getBalance(String playerName, String worldName) {
        return getBalance(getOfflinePlayer(playerName));
    }

    @Override
    @Deprecated
    public double getBalance(OfflinePlayer offlinePlayer, String worldName) {
        return getBalance(offlinePlayer);
    }

    @Override
    @Deprecated
    public boolean has(String playerName, double amount) {
        return players.get(getOfflinePlayer(playerName)) == amount;
    }

    @Override
    public boolean has(OfflinePlayer offlinePlayer, double amount) {
        return players.get(offlinePlayer) == amount;
    }

    @Override
    @Deprecated
    public boolean has(String playerName, String worldName, double amount) {
        return has(getOfflinePlayer(playerName), amount);
    }

    @Override
    @Deprecated
    public boolean has(OfflinePlayer offlinePlayer, String worldName, double amount) {
        return has(offlinePlayer, amount);
    }

    @Override
    @Deprecated
    public EconomyResponse withdrawPlayer(String playerName, double amount) {
        OfflinePlayer player = getOfflinePlayer(playerName);
        double balance = players.get(player);
        if(player != null) {
            players.remove(player);
            players.put(player, balance - amount);
            return new EconomyResponse(amount, balance, EconomyResponse.ResponseType.SUCCESS, "Could not withdraw " + amount + " from " + player.getName());
        }
        return new EconomyResponse(amount, balance, EconomyResponse.ResponseType.FAILURE, "Could not withdraw " + amount + " from " + player.getName());
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, double amount) {
        double balance = players.get(offlinePlayer);
        players.remove(offlinePlayer);
        players.put(offlinePlayer, balance - amount);
        return new EconomyResponse(amount, balance, EconomyResponse.ResponseType.SUCCESS, "Could not withdraw " + amount + " from " + offlinePlayer.getName());
    }

    @Override
    @Deprecated
    public EconomyResponse withdrawPlayer(String playerName, String worldName, double amount) {
        return withdrawPlayer(playerName, amount);
    }

    @Override
    @Deprecated
    public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, String worldName, double amount) {
        return withdrawPlayer(offlinePlayer, amount);
    }

    @Override
    @Deprecated
    public EconomyResponse depositPlayer(String playerName, double amount) {
        OfflinePlayer player = getOfflinePlayer(playerName);
        double balance = players.get(player);
        if(player != null) {
            players.remove(player);
            players.put(player, balance + amount);
            return new EconomyResponse(amount, balance, EconomyResponse.ResponseType.SUCCESS, "Could not withdraw " + amount + " from " + player.getName());
        }
        return new EconomyResponse(amount, balance, EconomyResponse.ResponseType.FAILURE, "Could not withdraw " + amount + " from " + player.getName());
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, double amount) {
        double balance = players.get(offlinePlayer);
        players.remove(offlinePlayer);
        players.put(offlinePlayer, balance + amount);
        return new EconomyResponse(amount, balance, EconomyResponse.ResponseType.SUCCESS, "Could not withdraw " + amount + " from " + offlinePlayer.getName());
    }

    @Override
    @Deprecated
    public EconomyResponse depositPlayer(String playerName, String worldName, double amount) {
        return depositPlayer(playerName, amount);
    }

    @Override
    @Deprecated
    public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, String s, double amount) {
        return depositPlayer(offlinePlayer, amount);
    }

    //redundant
    @Override
    @Deprecated
    public EconomyResponse createBank(String s, String s1) {
        return null;
    }

    //redundant
    @Override
    public EconomyResponse createBank(String s, OfflinePlayer offlinePlayer) {
        return null;
    }

    //redundant
    @Override
    public EconomyResponse deleteBank(String s) {
        return null;
    }

    //redundant
    @Override
    public EconomyResponse bankBalance(String s) {
        return null;
    }

    //redundant
    @Override
    public EconomyResponse bankHas(String s, double v) {
        return null;
    }

    //redundant
    @Override
    public EconomyResponse bankWithdraw(String s, double v) {
        return null;
    }

    //redundant
    @Override
    public EconomyResponse bankDeposit(String s, double v) {
        return null;
    }

    //redundant
    @Override
    @Deprecated
    public EconomyResponse isBankOwner(String s, String s1) {
        return null;
    }

    //redundant
    @Override
    public EconomyResponse isBankOwner(String s, OfflinePlayer offlinePlayer) {
        return null;
    }

    //redundant
    @Override
    @Deprecated
    public EconomyResponse isBankMember(String s, String s1) {
        return null;
    }

    //redundant
    @Override
    public EconomyResponse isBankMember(String s, OfflinePlayer offlinePlayer) {
        return null;
    }

    //redundant
    @Override
    public List<String> getBanks() {
        return null;
    }

    @Override
    @Deprecated
    public boolean createPlayerAccount(String s) {
        if(players.containsKey(getOfflinePlayer(s))) {
            return false;
        }
        players.put(getOfflinePlayer(s), 0.0);
        return true;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer offlinePlayer) {
        if(players.containsKey(offlinePlayer)) {
            return false;
        }
        players.put(offlinePlayer, 0.0);
        return true;
    }

    @Override
    @Deprecated
    public boolean createPlayerAccount(String playerName, String worldName) {
        return createPlayerAccount(playerName);
    }

    @Override
    @Deprecated
    public boolean createPlayerAccount(OfflinePlayer offlinePlayer, String worldName) {
        return createPlayerAccount(offlinePlayer);
    }

    public void push() {
        config.set("economy", null);
        config.createSection("economy");
        for(Map.Entry<OfflinePlayer, Double> entry : players.entrySet()) {
            UUID uuid = entry.getKey().getUniqueId();
            double balance = entry.getValue();
            config.set("economy." + uuid.toString() + ".balance", balance);
        }
        save();
    }

    private void pull() {
        players.clear();
        for(String u : config.getConfigurationSection("economy").getKeys(false)) {
            UUID uuid = UUID.fromString(u);
            double balance = config.getDouble("economy." + u + ".balance");
            players.put(Bukkit.getOfflinePlayer(uuid), balance);
        }
    }

    private void createFile() {
        file = new File(plugin.getDataFolder(), "economy.yml");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                plugin.getLogger().warning("Could not create economy.yml");
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
        if(!config.contains("economy")) {
            config.createSection("economy");
            save();
        }
    }

    private void save() {
        try {
            config.save(file);
        } catch (IOException ex) {
            plugin.getLogger().warning("Could not save economy.yml");
        }
    }
}
