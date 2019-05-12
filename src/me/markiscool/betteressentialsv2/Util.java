package me.markiscool.betteressentialsv2;

import me.markiscool.betteressentialsv2.constants.Lang;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class Util {

    public static String colourize(String m) {
        return ChatColor.translateAlternateColorCodes('&', m);
    }

    public static List<String> colourize(List<String> m) {
        List<String> t = new ArrayList<>();
        for(String s : m) {
            t.add(colourize(s));
        }
        return t;
    }

    public static String wrapMessage(String text) {
        return colourize(Lang.PREFIX + " " + text);
    }

}
