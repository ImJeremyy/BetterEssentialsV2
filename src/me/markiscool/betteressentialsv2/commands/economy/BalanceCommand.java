package me.markiscool.betteressentialsv2.commands.economy;

import me.markiscool.betteressentialsv2.BetterEssentialsV2Plugin;
import me.markiscool.betteressentialsv2.managers.BEEconomy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BalanceCommand implements CommandExecutor {

    private BEEconomy eco;

    public BalanceCommand(final BetterEssentialsV2Plugin plugin) {
        this.eco = plugin.getBeEconomy();
    }

    // bal [player]
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        return true;
    }
}
