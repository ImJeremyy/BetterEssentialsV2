package me.markiscool.betteressentialsv2.managers;

import me.markiscool.betteressentialsv2.BetterEssentialsV2Plugin;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.OfflinePlayer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BEEconomy implements Economy {

    private Map<OfflinePlayer, Double> players;

    public BEEconomy(final BetterEssentialsV2Plugin plugin) {
        players = new HashMap<>();
    }

    public OfflinePlayer getOfflinePlayer(String name) {
        for(OfflinePlayer p : players.keySet()) {
            if(p.getName().equalsIgnoreCase(name)) return p;
        }
        return null;
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
            players.put(player, balance - amount);
            return new EconomyResponse(amount, balance, EconomyResponse.ResponseType.SUCCESS, "Could not withdraw " + amount + " from " + player.getName());
        }
        return new EconomyResponse(amount, balance, EconomyResponse.ResponseType.FAILURE, "Could not withdraw " + amount + " from " + player.getName());
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, double amount) {
        double balance = players.get(offlinePlayer);
        players.remove(offlinePlayer);
        players.put(offlinePlayer, balance - amount);
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
}
