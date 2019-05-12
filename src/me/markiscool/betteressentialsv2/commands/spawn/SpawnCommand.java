package me.markiscool.betteressentialsv2.commands.spawn;

import me.markiscool.betteressentialsv2.BetterEssentialsV2Plugin;
import me.markiscool.betteressentialsv2.Util;
import me.markiscool.betteressentialsv2.constants.Lang;
import me.markiscool.betteressentialsv2.constants.Perm;
import me.markiscool.betteressentialsv2.managers.SpawnManager;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    private SpawnManager sm;

    public SpawnCommand(final BetterEssentialsV2Plugin plugin) {
        sm = plugin.getSpawnManager();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission(Perm.SPAWN)) {
                player.teleport(sm.getSpawn());
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
            } else {
                sender.sendMessage(Util.wrapMessage(Lang.NO_PERMISSION));
            }
        } else {
            sender.sendMessage(Util.wrapMessage(Lang.NOT_A_PLAYER));
        }
        return true;
    }
}
