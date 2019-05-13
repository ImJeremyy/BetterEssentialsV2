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

public class DeleteWarpCommand implements CommandExecutor {

    private WarpManager wm;

    public DeleteWarpCommand(final BetterEssentialsV2Plugin plugin) {
        this.wm = plugin.getWarpManager();
    }

    //delwarp <name>
    @Override
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if(sender.hasPermission(Perm.WARP_DELETE)) {
            if (args.length == 1) {
                final String name = args[0].toLowerCase();
                final Warp warp = wm.getWarp(name);
                if (warp != null) {
                    wm.remove(warp);
                    sender.sendMessage(Util.wrapMessage("&6Deleted warp &a" + warp.getName()));
                } else {
                    sender.sendMessage(Util.wrapMessage(Lang.WARP_NOT_FOUND));
                }
            } else {
                sender.sendMessage(Util.wrapMessage(Lang.INVALID_ARGUMENTS));
            }
        } else {
            sender.sendMessage(Util.wrapMessage(Lang.NO_PERMISSION));
        }
        return true;
    }
}
