package me.markiscool.betteressentialsv2.commands;

import me.markiscool.betteressentialsv2.Util;
import me.markiscool.betteressentialsv2.constants.Lang;
import me.markiscool.betteressentialsv2.constants.Perm;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KillCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender.hasPermission(Perm.KILL_SELF)) {
            if(args.length == 0 || args.length == 1) {
                Player targetPlayer;
                if(args.length == 0) {
                    if(sender instanceof Player) {
                        targetPlayer = (Player) sender;
                    } else {
                        sender.sendMessage(Util.wrapMessage(Lang.NOT_A_PLAYER));
                        return true;
                    }
                } else {
                    if(sender.hasPermission(Perm.KILL_OTHERS)) {
                        targetPlayer = Bukkit.getPlayer(args[0]);
                        if (targetPlayer == null) {
                            sender.sendMessage(Util.wrapMessage(Lang.PLAYER_NOT_FOUND));
                            return true;
                        }
                    } else {
                        sender.sendMessage(Util.wrapMessage(Lang.NO_PERMISSION));
                        return true;
                    }
                    targetPlayer.kickPlayer(Util.wrapMessage("&cYou were killed by &6" + sender.getName()));
                }
                sender.sendMessage(Util.wrapMessage("&aKilled &6" + targetPlayer.getName() + "&a."));
            } else {
                sender.sendMessage(Util.wrapMessage(Lang.INVALID_ARGUMENTS));
            }
        } else {
            sender.sendMessage(Util.wrapMessage(Lang.NO_PERMISSION));
        }
        return true;
    }

}
