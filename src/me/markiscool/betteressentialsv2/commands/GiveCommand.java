package me.markiscool.betteressentialsv2.commands;

import me.markiscool.betteressentialsv2.Util;
import me.markiscool.betteressentialsv2.constants.Lang;
import me.markiscool.betteressentialsv2.constants.Perm;
import me.markiscool.betteressentialsv2.constants.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveCommand implements CommandExecutor {

    // i [player] <item> <amount>
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender.hasPermission(Perm.GIVE_SELF)) {
            if (args.length == 2 || args.length == 3) {
                Player targetPlayer;
                Material material;
                int amount;
                if (args.length == 2) {
                    if (sender instanceof Player) {
                        targetPlayer = (Player) sender;
                    } else {
                        sender.sendMessage(Util.wrapMessage(Lang.NOT_A_PLAYER));
                        return true;
                    }
                } else {
                    if(sender.hasPermission(Perm.GIVE_OTHERS)) {
                        targetPlayer = Bukkit.getPlayer(args[0]);
                        if (targetPlayer == null) {
                            sender.sendMessage(Util.wrapMessage(Lang.PLAYER_NOT_FOUND));
                            return true;
                        }
                    } else {
                        sender.sendMessage(Util.wrapMessage(Lang.NO_PERMISSION));
                        return true;
                    }
                }
                try {
                    material = XMaterial.fromString(args[args.length == 2 ? 0 : 1]).parseMaterial();
                } catch (Exception ex) {
                    sender.sendMessage(Util.wrapMessage(Lang.INVALID_MATERIAL));
                    return true;
                }
                try {
                    amount = Integer.parseInt(args[args.length == 2 ? 1 : 2]);
                } catch (Exception ex) {
                    sender.sendMessage(Util.wrapMessage(Lang.INVALID_AMOUNT));
                    return true;
                }
                ItemStack item = new ItemStack(material, amount);
                if (targetPlayer.getInventory().firstEmpty() != - 1) {
                    targetPlayer.getInventory().addItem(item);
                } else {
                    targetPlayer.getLocation().getWorld().dropItemNaturally(targetPlayer.getLocation(), item);
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
