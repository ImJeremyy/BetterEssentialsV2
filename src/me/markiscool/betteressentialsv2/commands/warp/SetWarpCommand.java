package me.markiscool.betteressentialsv2.commands.warp;

import me.markiscool.betteressentialsv2.BetterEssentialsV2Plugin;
import me.markiscool.betteressentialsv2.Util;
import me.markiscool.betteressentialsv2.constants.Lang;
import me.markiscool.betteressentialsv2.objects.Warp;
import me.markiscool.betteressentialsv2.constants.Perm;
import me.markiscool.betteressentialsv2.managers.WarpManager;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetWarpCommand implements CommandExecutor {

    private WarpManager wm;

    public SetWarpCommand(final BetterEssentialsV2Plugin plugin) {
        this.wm = plugin.getWarpManager();
    }

    //setwarp <name>
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            final Player player = (Player) sender;
            if(player.hasPermission(Perm.WARP_SET)) {
                if (args.length == 1) {
                    final Location location = player.getLocation();
                    final Warp warp = new Warp(args[0], location);
                    wm.add(warp);
                    player.sendMessage(Util.wrapMessage("&6Set warp &a" + warp.getName()));
                } else {
                    player.sendMessage(Util.wrapMessage(Lang.INVALID_ARGUMENTS));
                }
            } else {
                sender.sendMessage(Util.wrapMessage(Lang.NO_PERMISSION));
            }
        } else {
            sender.sendMessage(Util.wrapMessage(Lang.NOT_A_PLAYER));
        }
        return true;
    }

}
