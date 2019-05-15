package me.markiscool.betteressentialsv2.listeners;

import me.markiscool.betteressentialsv2.BetterEssentialsV2Plugin;
import me.markiscool.betteressentialsv2.managers.BEEconomy;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class EconomyListeners implements Listener {

    private BEEconomy eco;

    public EconomyListeners(final BetterEssentialsV2Plugin plugin) {
        eco = plugin.getBeEconomy();
    }

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(!eco.hasAccount(player)) {
            eco.createPlayerAccount(player);
        }
    }

}
