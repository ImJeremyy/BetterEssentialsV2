package me.markiscool.betteressentialsv2.commands;

import me.markiscool.betteressentialsv2.Util;
import me.markiscool.betteressentialsv2.constants.Lang;
import me.markiscool.betteressentialsv2.constants.Perm;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {

    // /fly [player]
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender.hasPermission(Perm.FLY_SELF)) {
            if (args.length == 0 || args.length == 1) {
                Player targetPlayer = null;
                if (args.length == 0) { //self
                    if (sender instanceof Player) {
                        targetPlayer = (Player) sender;
                    } else {
                        sender.sendMessage(Util.wrapMessage(Lang.NOT_A_PLAYER));
                        return true;
                    }
                } else { //other
                    if (sender.hasPermission(Perm.FLY_OTHERS)) {
                        targetPlayer = Bukkit.getPlayer(args[0]);
                    } else {
                        sender.sendMessage(Util.wrapMessage(Lang.NO_PERMISSION));
                        return true;
                    }
                }
                if (targetPlayer != null) {
                    final boolean flightOn = targetPlayer.getAllowFlight();
                    if (flightOn) {
                        //turn off
                        targetPlayer.setAllowFlight(false);
                        targetPlayer.setFlying(false);
                        sender.sendMessage(Util.wrapMessage(Lang.FLIGHT_DISABLED));
                    } else {
                        //turn on
                        targetPlayer.setAllowFlight(true);
                        targetPlayer.setFlying(true);
                        sender.sendMessage(Util.wrapMessage(Lang.FLIGHT_ENABLED));
                    }
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
