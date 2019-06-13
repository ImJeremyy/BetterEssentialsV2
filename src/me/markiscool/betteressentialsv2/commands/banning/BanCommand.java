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

public class BanCommand implements CommandExecutor {

    private UsersAPI uapi;
    private BanManager bm;

    public BanCommand(final BetterEssentialsV2Plugin plugin) {
        uapi = UsersAPIPlugin.getInstance();
        bm = plugin.getBanManager();
    }

    // /ban <player> [reason]
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender.hasPermission(Perm.BAN)) {
            if(args.length >= 1) {
                final OfflinePlayer targetPlayer = uapi.getOfflinePlayer(args[0]);
                if(targetPlayer != null) {
                    String reason = "certain reasons...";
                    if(args.length > 1) {
                        reason = "";
                        for(int i = 1; i < args.length; i++) {
                            reason+= args[i] + " ";
                        }
                    }
                    if(targetPlayer.isOnline()) {
                        final Player online = (Player) targetPlayer;
                        online.kickPlayer(Util.colourize("&cYou were banned for &6" + reason + "&c."));
                    }
                    bm.ban(targetPlayer.getUniqueId(), reason);
                    sender.sendMessage(Util.colourize("&aYou banned &6" + targetPlayer.getName() + " &afor &6" + reason));
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
