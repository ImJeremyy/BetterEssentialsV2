package me.markiscool.betteressentialsv2.commands.gamemode;

import me.markiscool.betteressentialsv2.Util;
import me.markiscool.betteressentialsv2.constants.Lang;
import me.markiscool.betteressentialsv2.constants.Perm;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeAdventureCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender.hasPermission(Perm.GAMEMODE_SELF)) {
            if (args.length == 0 || args.length == 1) {
                Player targetPlayer;
                if (args.length == 0) {
                    if (sender instanceof Player) {
                        targetPlayer = (Player) sender;
                    } else {
                        sender.sendMessage(Util.wrapMessage(Lang.NOT_A_PLAYER));
                        return true;
                    }
                } else {
                    if (sender.hasPermission(Perm.GAMEMODE_OTHERS)) {
                        targetPlayer = Bukkit.getPlayer(args[0]);
                    } else {
                        sender.sendMessage(Util.wrapMessage(Lang.NO_PERMISSION));
                        return true;
                    }

                }
                if (targetPlayer != null) {
                    targetPlayer.setGameMode(GameMode.ADVENTURE);
                    sender.sendMessage(Util.wrapMessage("&a" + targetPlayer.getName() + "&6's " + Lang.GAMEMODE_UPDATED + " &aAdventure"));
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
