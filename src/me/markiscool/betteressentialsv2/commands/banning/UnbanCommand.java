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

import java.util.UUID;

public class UnbanCommand implements CommandExecutor {

    private BanManager bm;
    private UsersAPI uapi;

    public UnbanCommand(final BetterEssentialsV2Plugin plugin) {
        bm = plugin.getBanManager();
        uapi = UsersAPIPlugin.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender.hasPermission(Perm.UNBAN)) {
            if(args.length == 1) {
                final OfflinePlayer targetPlayer = uapi.getOfflinePlayer(args[0]);
                if(targetPlayer != null) {
                    final UUID uuid = targetPlayer.getUniqueId();
                    if (bm.contains(uuid)) {
                        bm.unban(uuid);
                    } else {
                        sender.sendMessage(Util.wrapMessage("&cThis player is not banned."));
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
