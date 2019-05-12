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

public class GamemodeCommand implements CommandExecutor {

    // gamemode <mode> [player]
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender.hasPermission(Perm.GAMEMODE_SELF)) {
            if (args.length == 1 || args.length == 2) {
                String mode = args[0].toLowerCase();
                Player targetPlayer = null;
                if(args.length == 1) {
                    if(sender instanceof Player) {
                        targetPlayer = (Player) sender;
                    } else {
                        sender.sendMessage(Util.wrapMessage(Lang.NOT_A_PLAYER));
                        return true;
                    }
                } else {
                    if(sender.hasPermission(Perm.GAMEMODE_OTHERS)) {
                        targetPlayer = Bukkit.getPlayer(args[1]);
                    } else {
                        targetPlayer.sendMessage(Util.wrapMessage(Lang.NO_PERMISSION));
                        return true;
                    }
                }
                if(targetPlayer != null) {
                    if (mode.equals("c") || mode.equals("creative") || mode.equals("crea")) {
                        targetPlayer.setGameMode(GameMode.CREATIVE);
                        sender.sendMessage(Util.wrapMessage(Lang.GAMEMODE_UPDATED));
                    } else if (mode.equals("s") || mode.equals("survival") || mode.equals("surv")) {
                        targetPlayer.setGameMode(GameMode.SURVIVAL);
                        sender.sendMessage(Util.wrapMessage(Lang.GAMEMODE_UPDATED));
                    } else if (mode.equals("a") || mode.equals("adventure") || mode.equals("adve")) {
                        targetPlayer.setGameMode(GameMode.ADVENTURE);
                        sender.sendMessage(Util.wrapMessage(Lang.GAMEMODE_UPDATED));
                    } else if (mode.equals("sp") || mode.equals("spectator") || mode.equals("spec")) {
                        targetPlayer.setGameMode(GameMode.SPECTATOR);
                        sender.sendMessage(Util.wrapMessage(Lang.GAMEMODE_UPDATED));
                    } else {
                        sender.sendMessage(Util.wrapMessage(Lang.INVALID_GAMEMODE));
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
