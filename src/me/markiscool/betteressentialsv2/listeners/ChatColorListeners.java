package me.markiscool.betteressentialsv2.listeners;

import me.markiscool.betteressentialsv2.BetterEssentialsV2Plugin;
import me.markiscool.betteressentialsv2.Util;
import me.markiscool.betteressentialsv2.constants.Perm;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatColorListeners implements Listener {

    public ChatColorListeners(final BetterEssentialsV2Plugin plugin) {}

    @EventHandler
    public void onPlayerChat(final AsyncPlayerChatEvent event) {
        final Player player = event.getPlayer();
        if(player.hasPermission(Perm.COLOR_CHAT)) {
            event.setMessage(Util.colourize(event.getMessage()));
        }
    }

    @EventHandler
    public void onSignChange(final SignChangeEvent event) {
        final Player player = event.getPlayer();
        if(player.hasPermission(Perm.COLOR_SIGN)) {
            for(int i = 0; i < event.getLines().length; i++) {
                event.setLine(i, Util.colourize(event.getLine(i)));
            }
        }
    }

}
