package me.markiscool.betteressentialsv2.commands.spawn;

import me.markiscool.betteressentialsv2.BetterEssentialsV2Plugin;
import me.markiscool.betteressentialsv2.Util;
import me.markiscool.betteressentialsv2.constants.Lang;
import me.markiscool.betteressentialsv2.constants.Perm;
import me.markiscool.betteressentialsv2.managers.SpawnManager;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {

    private SpawnManager sm;

    public SetSpawnCommand(final BetterEssentialsV2Plugin plugin) {
        sm = plugin.getSpawnManager();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            if(sender.hasPermission(Perm.SET_SPAWN)) {
                Location location = ((Player) sender).getLocation();
                sm.setSpawn(location);
                sender.sendMessage(Util.wrapMessage(Lang.SPAWN_SET));
            } else {
                sender.sendMessage(Util.wrapMessage(Lang.NO_PERMISSION));
            }
        } else {
            sender.sendMessage(Util.wrapMessage(Lang.NOT_A_PLAYER));
        }
        return true;
    }
}
