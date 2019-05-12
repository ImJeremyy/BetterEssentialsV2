package me.markiscool.betteressentialsv2.commands;

import me.markiscool.betteressentialsv2.BetterEssentialsV2Plugin;
import me.markiscool.betteressentialsv2.Util;
import me.markiscool.betteressentialsv2.constants.Lang;
import me.markiscool.betteressentialsv2.constants.Perm;
import me.markiscool.betteressentialsv2.managers.SeenManager;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Date;

public class SeenCommand implements CommandExecutor {

    private SeenManager sm;

    public SeenCommand(final BetterEssentialsV2Plugin plugin) {
        this.sm = plugin.getSeenManager();
    }

    // seen <player>
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender.hasPermission(Perm.SEEN)) {
            if (args.length == 1) {
                OfflinePlayer targetPlayer = sm.getOfflinePlayer(args[0]);
                if(targetPlayer != null) {
                    if(!targetPlayer.isOnline()) {
                        sender.sendMessage(Util.wrapMessage("&a" + targetPlayer.getName() + " &6was last seen on &a" + new Date(sm.getTimeStamp(targetPlayer)) + "&6."));
                    } else {
                        sender.sendMessage(Util.wrapMessage("&a" + targetPlayer.getName() + " &6is &aonline&6."));
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
