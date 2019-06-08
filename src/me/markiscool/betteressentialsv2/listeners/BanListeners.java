package me.markiscool.betteressentialsv2.listeners;

import me.markiscool.betteressentialsv2.BetterEssentialsV2Plugin;
import me.markiscool.betteressentialsv2.Util;
import me.markiscool.betteressentialsv2.managers.BanManager;
import org.bukkit.BanList;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class BanListeners implements Listener {

    private BanManager bm;

    private BanListeners(final BetterEssentialsV2Plugin plugin) {
        bm = plugin.getBanManager();
    }

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        final UUID uuid = player.getUniqueId();
        if(bm.containsTempban(uuid)) {
            player.kickPlayer(Util.colourize("&cYou are tempbanned for &6" + TimeUnit.MILLISECONDS.toSeconds(bm.getTempbanTimeLeft(uuid)) + " &cseconds for &6" + bm.getReason(uuid)));
        } else if(bm.contains(uuid)) {
            player.kickPlayer(Util.colourize("&cYou are permanently banned for &6" + bm.getReason(uuid)));
        }
    }

}
