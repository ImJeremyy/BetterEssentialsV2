package me.markiscool.betteressentialsv2.commands;

import me.markiscool.betteressentialsv2.Util;
import me.markiscool.betteressentialsv2.constants.Lang;
import me.markiscool.betteressentialsv2.constants.Perm;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FeedCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender.hasPermission(Perm.FEED_SELF)) {
            if (args.length == 0 || args.length == 1) {
                Player targetPlayer;
                if (args.length == 0) { //self
                    if (sender instanceof Player) {
                        targetPlayer = (Player) sender;
                    } else {
                        sender.sendMessage(Util.wrapMessage(Lang.NOT_A_PLAYER));
                        return true;
                    }
                } else { //other
                    if (sender.hasPermission(Perm.FEED_OTHERS)) {
                        targetPlayer = Util.getPlayer(args[0]);
                    } else {
                        sender.sendMessage(Util.wrapMessage(Lang.NO_PERMISSION));
                        return true;
                    }
                }
                if (targetPlayer != null) {
                    targetPlayer.setFoodLevel(20);
                    sender.sendMessage(Util.wrapMessage(Lang.FEED_SUCCESSFUL));
                } else {
                    sender.sendMessage(Util.wrapMessage(Lang.PLAYER_NOT_FOUND));
                }
            } else {
                sender.sendMessage(Util.wrapMessage(Lang.INVALID_ARGUMENTS));
            }
        }
        return true;
    }
}
