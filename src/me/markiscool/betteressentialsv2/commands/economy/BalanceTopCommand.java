package me.markiscool.betteressentialsv2.commands.economy;

import me.markiscool.betteressentialsv2.BetterEssentialsV2Plugin;
import me.markiscool.betteressentialsv2.constants.Perm;
import me.markiscool.betteressentialsv2.managers.BEEconomy;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Map;

public class BalanceTopCommand implements CommandExecutor {

    private BEEconomy eco;

    public BalanceTopCommand(final BetterEssentialsV2Plugin plugin) {
        this.eco = plugin.getBeEconomy();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender.hasPermission(Perm.BALANCE_TOP)) {

        } else {
            //TODO NO PERMISSION
        }
        return true;
    }

}
