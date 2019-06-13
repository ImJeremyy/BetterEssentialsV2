package me.markiscool.betteressentialsv2;

import me.markiscool.betteressentialsv2.commands.*;
import me.markiscool.betteressentialsv2.commands.banning.BanCommand;
import me.markiscool.betteressentialsv2.commands.banning.TempBanCommand;
import me.markiscool.betteressentialsv2.commands.banning.UnbanCommand;
import me.markiscool.betteressentialsv2.commands.economy.BalanceCommand;
import me.markiscool.betteressentialsv2.commands.economy.BalanceTopCommand;
import me.markiscool.betteressentialsv2.commands.economy.EconomyCommand;
import me.markiscool.betteressentialsv2.commands.gamemode.*;
import me.markiscool.betteressentialsv2.commands.spawn.SetSpawnCommand;
import me.markiscool.betteressentialsv2.commands.spawn.SpawnCommand;
import me.markiscool.betteressentialsv2.commands.warp.DeleteWarpCommand;
import me.markiscool.betteressentialsv2.commands.warp.SetWarpCommand;
import me.markiscool.betteressentialsv2.commands.warp.WarpCommand;
import me.markiscool.betteressentialsv2.commands.warp.WarpsCommand;
import me.markiscool.betteressentialsv2.listeners.*;
import me.markiscool.betteressentialsv2.managers.*;
import net.milkbowl.vault.Vault;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class BetterEssentialsV2Plugin extends JavaPlugin {

    private WarpManager warpManager;
    private VanishManager vanishManager;
    private SeenManager seenManager;
    private SpawnManager spawnManager;
    private BEEconomy beEconomy;
    private BanManager banManager;

    @Override
    public void onEnable() {
        registerDataFolder();
        registerConfig();
        registerManagers();
        registerCommands();
        registerListeners();
    }

    @Override
    public void onDisable() {
        warpManager.push();
        seenManager.push();
        spawnManager.push();
        banManager.push();
    }

    public WarpManager getWarpManager() {
        return warpManager;
    }

    public VanishManager getVanishManager() {
        return vanishManager;
    }

    public SeenManager getSeenManager() {
        return seenManager;
    }

    public SpawnManager getSpawnManager() {
        return spawnManager;
    }

    public BEEconomy getBeEconomy() {
        return beEconomy;
    }

    public BanManager getBanManager() {
        return banManager;
    }

    private void registerDataFolder() {
        if(getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
    }

    private void registerConfig() {

    }

    private void registerManagers() {
        warpManager = new WarpManager(this);
        vanishManager = new VanishManager(this);
        seenManager = new SeenManager(this);
        spawnManager = new SpawnManager(this);
        beEconomy = new BEEconomy(this);
        banManager = new BanManager(this);
        getServer().getServicesManager().register(Economy.class, beEconomy, Bukkit.getPluginManager().getPlugin("Vault"), ServicePriority.Normal);
        final BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run() {
                warpManager.push();
                seenManager.push();
                spawnManager.push();
                beEconomy.push();
                banManager.push();
            }
        };
        getServer().getScheduler().runTaskTimer(this, (Runnable) runnable, 60,  40);

    }

    private void registerCommands() {
        final Map<String, CommandExecutor> commands = new HashMap<>();
        commands.put("deletewarp", new DeleteWarpCommand(this));
        commands.put("setwarp", new SetWarpCommand(this));
        commands.put("warp", new WarpCommand(this));
        commands.put("warps", new WarpsCommand(this));
        commands.put("clearinventory", new ClearInventoryCommand());
        commands.put("give", new GiveCommand());
        commands.put("vanish", new VanishCommand(this));
        commands.put("fly", new FlyCommand());
        commands.put("gamemode", new GamemodeCommand());
        commands.put("gamemodecreative", new GamemodeCreativeCommand());
        commands.put("gamemodesurvival", new GamemodeSurvivalCommand());
        commands.put("gamemodeadventure", new GamemodeAdventureCommand());
        commands.put("gamemodespectator", new GamemodeSpectatorCommand());
        commands.put("heal", new HealCommand());
        commands.put("feed", new FeedCommand());
        commands.put("speed", new SpeedCommand());
        commands.put("seen", new SeenCommand(this));
        commands.put("spawn", new SpawnCommand(this));
        commands.put("setspawn", new SetSpawnCommand(this));
        commands.put("balance", new BalanceCommand(this));
        commands.put("balancetop", new BalanceTopCommand(this));
        commands.put("economy", new EconomyCommand(this));;
        commands.put("kill", new KillCommand());
        commands.put("ban", new BanCommand(this));
        commands.put("unban", new UnbanCommand(this));
        commands.put("tempban", new TempBanCommand(this));

        for(final Map.Entry<String, CommandExecutor> entry : commands.entrySet()) {
            getCommand(entry.getKey()).setExecutor(entry.getValue());
        }
    }

    private void registerListeners() {
        Listener[] listeners = {
                new VanishListeners(this),
                new SeenListeners(this),
                new EconomyListeners(this),
                new BanListeners(this),
                new ChatColorListeners(this),
                };

        for(Listener l : listeners) {
            getServer().getPluginManager().registerEvents(l, this);
        }
    }


}
