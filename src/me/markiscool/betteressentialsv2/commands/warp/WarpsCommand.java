package me.markiscool.betteressentialsv2.commands.warp;

import me.markiscool.betteressentialsv2.BetterEssentialsV2Plugin;
import me.markiscool.betteressentialsv2.Util;
import me.markiscool.betteressentialsv2.constants.Lang;
import me.markiscool.betteressentialsv2.Warp;
import me.markiscool.betteressentialsv2.constants.Perm;
import me.markiscool.betteressentialsv2.managers.WarpManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class WarpsCommand implements CommandExecutor {

    private WarpManager wm;

    public WarpsCommand(final BetterEssentialsV2Plugin plugin) {
        wm = plugin.getWarpManager();
    }

    // /warps
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender.hasPermission(Perm.WARP_LIST)) {
            String message = "";
            if(!wm.getWarps().isEmpty()) {
                for (Warp w : wm.getWarps()) {
                    String name = w.getName();
                    message = message + name + "&6,&f";
                }
            } else {
                message = "None";
            }
            sender.sendMessage(Util.colourize("&6Warps: &f" + message));
        } else {
            sender.sendMessage(Util.wrapMessage(Lang.NO_PERMISSION));
        }
        return true;
    }
}
