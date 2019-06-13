package me.markiscool.betteressentialsv2.commands.banning;

import me.markiscool.betteressentialsv2.Util;
import me.markiscool.betteressentialsv2.constants.Lang;
import me.markiscool.betteressentialsv2.constants.Perm;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KickCommand implements CommandExecutor {

    public KickCommand() {

    }

    // /kick <player> [reason]
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender.hasPermission(Perm.KICK)) {
            if(args.length >= 1) {
                final Player targetPlayer = Util.getPlayer(args[0]);
                if(targetPlayer != null) {
                    String reason = "";
                    if(args.length > 1) {
                        for(int i = 1; i < args.length; i++) {
                            reason += args[i] + " ";
                        }
                    } else {
                        reason = "&ccertain reasons..";
                    }
                    targetPlayer.kickPlayer(Util.colourize("&cYou were kicked for &6" + reason));
                    sender.sendMessage(Util.colourize("&aYou kicked &6" + targetPlayer.getName() + "&a for &6" + reason));
                } else {
                    sender.sendMessage(Util.wrapMessage(Lang.PLAYER_NOT_FOUND));
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
