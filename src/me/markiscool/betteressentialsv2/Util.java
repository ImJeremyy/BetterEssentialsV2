package me.markiscool.betteressentialsv2;

import me.markiscool.betteressentialsv2.constants.Lang;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Util {

    public static String colourize(final String m) {
        return ChatColor.translateAlternateColorCodes('&', m);
    }

    public static List<String> colourize(final List<String> m) {
        final List<String> t = new ArrayList<>();
        for(final String s : m) {
            t.add(colourize(s));
        }
        return t;
    }

    public static String strip(final String m) {
        return ChatColor.stripColor(m);
    }

    public static List<String> strip(final List<String> m) {
        final List<String> t = new ArrayList<>();
        for(String s : m) {
            t.add(strip(s));
        }
        return t;
    }

    public static String wrapMessage(String text) {
        return colourize(Lang.PREFIX + " " + text);
    }

    public static Player getPlayer(String partName) {
        for(Player player : Bukkit.getOnlinePlayers()) {
            if(player.getName().toLowerCase().contains(partName.toLowerCase())) {
                return player;
            }
        }
        return null;
    }

}
