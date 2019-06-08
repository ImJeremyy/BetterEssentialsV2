package me.markiscool.betteressentialsv2.commands.banning;

import me.markiscool.betteressentialsv2.BetterEssentialsV2Plugin;
import me.markiscool.betteressentialsv2.Util;
import me.markiscool.betteressentialsv2.constants.Lang;
import me.markiscool.betteressentialsv2.constants.Perm;
import me.markiscool.betteressentialsv2.managers.BanManager;
import me.markiscool.usersapi.UsersAPI;
import me.markiscool.usersapi.UsersAPIPlugin;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;

public class TempBanCommand implements CommandExecutor {

    private BanManager bm;
    private UsersAPI uapi;

    public TempBanCommand(final BetterEssentialsV2Plugin plugin) {
        bm = plugin.getBanManager();
        uapi = UsersAPIPlugin.getInstance();
    }

    // /tempban <player> <duration_seconds> [reason]
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender.hasPermission(Perm.TEMP_BAN)) {
            if(args.length >= 2) {
                final OfflinePlayer targetPlayer = uapi.getOfflinePlayer(args[0]);
                if(targetPlayer != null) {
                    long duration;
                    try {
                        duration = Long.parseLong(args[1]);
                    } catch (NumberFormatException ex) {
                        sender.sendMessage(Util.wrapMessage("&cInvalid amount of seconds."));
                        return true;
                    }
                    String reason = "certain reasons...";
                    if(args.length > 2) {
                        reason = "";
                        for(int i = 2; i < args.length; i++) {
                            reason += args[i] + " ";
                        }
                    }
                    bm.tempban(targetPlayer.getUniqueId(), reason, TimeUnit.SECONDS.toMillis(duration));
                    if(targetPlayer.isOnline()) {
                        Player online = (Player) targetPlayer;
                        online.kickPlayer(Util.colourize("&cYou were tempbanned for &6" + reason + "&c. Time left: &6" + TimeUnit.MILLISECONDS.toSeconds(bm.getTempbanTimeLeft(targetPlayer.getUniqueId())) + " &cseconds."));
                    }
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
