package me.markiscool.betteressentialsv2.commands;

import me.markiscool.betteressentialsv2.BetterEssentialsV2Plugin;
import me.markiscool.betteressentialsv2.Util;
import me.markiscool.betteressentialsv2.constants.Lang;
import me.markiscool.betteressentialsv2.constants.Perm;
import me.markiscool.betteressentialsv2.managers.VanishManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor {

    private BetterEssentialsV2Plugin plugin;
    private VanishManager vm;

    public VanishCommand(final BetterEssentialsV2Plugin plugin) {
        this.plugin = plugin;
        vm = plugin.getVanishManager();
    }

    // v [player]
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission(Perm.VANISH_SELF)) {
            if (args.length == 0 || args.length == 1) {
                Player targetPlayer = null;
                if (args.length == 0) { //self
                    if(sender instanceof Player) {
                        targetPlayer = (Player) sender;
                    } else {
                        sender.sendMessage(Util.wrapMessage(Lang.NOT_A_PLAYER));
                        return true;
                    }
                } else { //others
                    if (sender.hasPermission(Perm.VANISH_OTHERS)) {
                        targetPlayer = Bukkit.getPlayer(args[0]);
                    } else {
                        sender.sendMessage(Util.wrapMessage(Lang.NO_PERMISSION));
                        return true;
                    }
                }
                if(targetPlayer != null) {
                    if(!vm.isVanished(targetPlayer)) {
                        vm.vanish(targetPlayer);
                        sender.sendMessage(Util.wrapMessage("&6Vanished " + targetPlayer.getName()));
                    } else {
                        vm.unvanish(targetPlayer);
                        sender.sendMessage(Util.wrapMessage("&6Unvanished " + targetPlayer.getName()));
                    }
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
