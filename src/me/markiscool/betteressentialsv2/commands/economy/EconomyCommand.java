package me.markiscool.betteressentialsv2.commands.economy;

import me.markiscool.betteressentialsv2.BetterEssentialsV2Plugin;
import me.markiscool.betteressentialsv2.Util;
import me.markiscool.betteressentialsv2.constants.Lang;
import me.markiscool.betteressentialsv2.constants.Perm;
import me.markiscool.betteressentialsv2.managers.BEEconomy;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.IOException;

public class EconomyCommand implements CommandExecutor {

    private BEEconomy eco;

    public EconomyCommand(final BetterEssentialsV2Plugin plugin) {
        this.eco = plugin.getBeEconomy();
    }

    // /eco <set/reset/add/remove> <player> [amount]
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender.hasPermission(Perm.ECONOMY)) {
            if(args.length == 2 || args.length == 3) {
                String arg1 = args[0].toLowerCase();
                OfflinePlayer targetPlayer = eco.getOfflinePlayer(args[1]);
                if(targetPlayer != null) {
                    if (args.length == 2) {
                        if (arg1.equals("reset")) {
                            eco.setBalance(targetPlayer, 0);
                            sender.sendMessage(Util.colourize("&a" + targetPlayer.getName() + "&6's balance was reset to &a0.0"));
                        } else {
                            sender.sendMessage(Util.wrapMessage(Lang.INVALID_ARGUMENTS));
                        }
                    } else {
                        double amount;
                        try {
                            amount = Double.parseDouble(args[2]);
                        } catch (Exception ex) {
                            sender.sendMessage(Util.wrapMessage(Lang.INVALID_AMOUNT));
                            return true;
                        }
                        if (arg1.equals("set")) {
                            eco.setBalance(targetPlayer, amount);
                            sender.sendMessage(Util.colourize("&a" + targetPlayer.getName() + "&6's balance was set to &a" + amount));
                        } else if (arg1.equals("add")) {
                            eco.depositPlayer(targetPlayer, amount);
                            sender.sendMessage(Util.colourize("&6Deposited &a" + amount + " &6from &a" + targetPlayer.getName() + "&6's balance"));
                        } else if (arg1.equals("remove")) {
                            eco.withdrawPlayer(targetPlayer, amount);
                            sender.sendMessage(Util.colourize("&6Withdrew &a" + amount + " &6from &a" + targetPlayer.getName() + "&6's balance"));
                        } else {
                            sender.sendMessage(Util.wrapMessage(Lang.INVALID_ARGUMENTS));
                        }
                    }
                } else {
                    sender.sendMessage(Util.wrapMessage(Lang.PLAYER_NOT_FOUND));
                }
            } else {
                sender.sendMessage(Util.wrapMessage(Lang.INVALID_ARGUMENTS));
            }
        } else {
            //TODO NO PERMISSION
        }
        return true;
    }
}
