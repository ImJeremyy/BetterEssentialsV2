package me.markiscool.betteressentialsv2.commands;

import me.markiscool.betteressentialsv2.Util;
import me.markiscool.betteressentialsv2.constants.Lang;
import me.markiscool.betteressentialsv2.constants.Perm;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpeedCommand implements CommandExecutor {

    //speed [player] <number>
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender.hasPermission(Perm.SPEED_SELF)) {
            if (args.length == 1 || args.length == 2) { //self
                float speed;
                try {
                    speed = Float.parseFloat(args[args.length == 1 ? 0 : 1]);
                } catch (Exception ex) {
                    sender.sendMessage(Util.wrapMessage(Lang.INVALID_AMOUNT));
                    return true;
                }
                if(speed < 0 || speed > 10) {
                    sender.sendMessage(Util.wrapMessage(Lang.AMOUNT_OUT_OF_BOUNDS));
                    return true;
                }
                Player targetPlayer = null;
                if(args.length == 1) {
                    if(sender instanceof Player) {

                    } else {
                        sender.sendMessage(Util.wrapMessage(Lang.NOT_A_PLAYER));
                        return true;
                    }
                } else { //other
                    if(sender.hasPermission(Perm.SPEED_OTHERS)) {
                        targetPlayer = Bukkit.getPlayer(args[0]);
                    } else {
                        sender.sendMessage(Util.wrapMessage(Lang.NO_PERMISSION));
                    }
                }
                if(targetPlayer != null) {
                    targetPlayer.setWalkSpeed(speed);
                    targetPlayer.setFlySpeed(speed);
                    sender.sendMessage(Util.wrapMessage("&6Walk & Fly speeds have been set to &a" + speed + "&6."));
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
