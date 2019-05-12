package me.markiscool.betteressentialsv2.commands.warp;

import me.markiscool.betteressentialsv2.BetterEssentialsV2Plugin;
import me.markiscool.betteressentialsv2.Util;
import me.markiscool.betteressentialsv2.constants.Lang;
import me.markiscool.betteressentialsv2.objects.Warp;
import me.markiscool.betteressentialsv2.constants.Perm;
import me.markiscool.betteressentialsv2.managers.WarpManager;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCommand implements CommandExecutor {

    private WarpManager wm;

    public WarpCommand(final BetterEssentialsV2Plugin plugin) {
        wm = plugin.getWarpManager();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            final Player player = (Player) sender;
            if(player.hasPermission(Perm.WARP)) {
                if(args.length == 1) {
                    final Warp warp = wm.getWarp(args[0].toLowerCase());
                    if(warp != null) {
                        player.teleport(warp.getLocation());
                        player.playSound(player.getLocation(), Sound.ENTITY_ENDER_PEARL_THROW, 1f, 1f);
                    } else {
                        sender.sendMessage(Util.wrapMessage(Lang.WARP_NOT_FOUND));
                    }
                } else {
                    sender.sendMessage(Util.wrapMessage(Lang.INVALID_ARGUMENTS));
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
