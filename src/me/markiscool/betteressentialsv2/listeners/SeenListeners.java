package me.markiscool.betteressentialsv2.listeners;

import me.markiscool.betteressentialsv2.BetterEssentialsV2Plugin;
import me.markiscool.betteressentialsv2.managers.SeenManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class SeenListeners implements Listener {

    private SeenManager sm;

    public SeenListeners(final BetterEssentialsV2Plugin plugin) {
        this.sm = plugin.getSeenManager();
    }

    @EventHandler
    public void onPlayerQuit(final PlayerQuitEvent event) {
        Player player = event.getPlayer();
        sm.log(player);
    }

}
