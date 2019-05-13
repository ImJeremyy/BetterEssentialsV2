package me.markiscool.betteressentialsv2.commands.economy;

import me.markiscool.betteressentialsv2.BetterEssentialsV2Plugin;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class EconomyCommand implements CommandExecutor {

    private BetterEssentialsV2Plugin plugin;

    public EconomyCommand(final BetterEssentialsV2Plugin plugin) {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        return true;
    }
}
