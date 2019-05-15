package me.markiscool.betteressentialsv2.commands.economy;

import me.markiscool.betteressentialsv2.BetterEssentialsV2Plugin;
import me.markiscool.betteressentialsv2.Util;
import me.markiscool.betteressentialsv2.constants.Lang;
import me.markiscool.betteressentialsv2.constants.Perm;
import me.markiscool.betteressentialsv2.managers.BEEconomy;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BalanceCommand implements CommandExecutor {

    private BEEconomy eco;

    public BalanceCommand(final BetterEssentialsV2Plugin plugin) {
        this.eco = plugin.getBeEconomy();
    }

    // bal [player]
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender.hasPermission(Perm.BALANCE_SELF)) {
            if (args.length == 0 || args.length == 1) {
                OfflinePlayer targetPlayer = null;
                if (args.length == 0) { //self
                    if (sender instanceof Player) {
                        targetPlayer = (Player) sender;
                    } else {
                        sender.sendMessage(Util.wrapMessage(Lang.NOT_A_PLAYER));
                        return true;
                    }
                } else { //other
                    if (sender.hasPermission(Perm.BALANCE_OTHERS)) {
                        final Player potentialOnlinePlayer = Util.getPlayer(args[0]);
                        if(potentialOnlinePlayer != null) {
                            targetPlayer = potentialOnlinePlayer;
                        } else {
                            targetPlayer = eco.getOfflinePlayer(args[0]);
                        }
                    } else {
                        sender.sendMessage(Util.wrapMessage(Lang.NO_PERMISSION));
                        return true;
                    }
                }
                if(targetPlayer != null) {
                    sender.sendMessage(Util.wrapMessage("&a" + targetPlayer.getName() + "&6's balance: &a" + eco.getBalance(targetPlayer)));
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
