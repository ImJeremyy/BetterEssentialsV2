package me.markiscool.betteressentialsv2;

import me.markiscool.betteressentialsv2.commands.*;
import me.markiscool.betteressentialsv2.commands.gamemode.GamemodeAdventureCommand;
import me.markiscool.betteressentialsv2.commands.gamemode.GamemodeCommand;
import me.markiscool.betteressentialsv2.commands.gamemode.GamemodeCreativeCommand;
import me.markiscool.betteressentialsv2.commands.gamemode.GamemodeSurvivalCommand;
import me.markiscool.betteressentialsv2.commands.warp.DeleteWarpCommand;
import me.markiscool.betteressentialsv2.commands.warp.SetWarpCommand;
import me.markiscool.betteressentialsv2.commands.warp.WarpCommand;
import me.markiscool.betteressentialsv2.commands.warp.WarpsCommand;
import me.markiscool.betteressentialsv2.listeners.SeenListeners;
import me.markiscool.betteressentialsv2.listeners.VanishListeners;
import me.markiscool.betteressentialsv2.managers.SeenManager;
import me.markiscool.betteressentialsv2.managers.VanishManager;
import me.markiscool.betteressentialsv2.managers.WarpManager;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class BetterEssentialsV2Plugin extends JavaPlugin {

    private WarpManager warpManager;
    private VanishManager vanishManager;
    private SeenManager seenManager;

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
        commands.put("heal", new HealCommand());
        commands.put("feed", new FeedCommand());
        commands.put("speed", new SpeedCommand());
        commands.put("seen", new SeenCommand(this));

        for(final Map.Entry<String, CommandExecutor> entry : commands.entrySet()) {
            getCommand(entry.getKey()).setExecutor(entry.getValue());
        }
    }

    private void registerListeners() {
        Listener[] listeners = {
                new VanishListeners(this),
                new SeenListeners(this),
                };

        for(Listener l : listeners) {
            getServer().getPluginManager().registerEvents(l, this);
        }
    }


}
