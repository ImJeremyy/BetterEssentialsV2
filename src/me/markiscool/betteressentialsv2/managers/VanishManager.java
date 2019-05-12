package me.markiscool.betteressentialsv2.managers;

import me.markiscool.betteressentialsv2.BetterEssentialsV2Plugin;
import me.markiscool.betteressentialsv2.constants.Perm;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class VanishManager {

    private BetterEssentialsV2Plugin plugin;

    private Set<Player> vanished;

    public VanishManager(final BetterEssentialsV2Plugin plugin) {
        this.plugin = plugin;
        vanished = new HashSet<>();
    }

    public void vanish(final Player playerToVanish) {
        vanished.add(playerToVanish);
        for(Player player : Bukkit.getOnlinePlayers()) {
            if(!player.hasPermission(Perm.VANISH_SEE_VANISHED)) {
                player.hidePlayer(plugin, playerToVanish);
            }
        }
    }

    public void unvanish(final Player playerToUnvanish) {
        vanished.remove(playerToUnvanish);
        for(Player player : Bukkit.getOnlinePlayers()) {
            player.showPlayer(plugin, playerToUnvanish);
        }
    }

    public void updateInvisibility(final Player playerToUpdate) {
        if(!playerToUpdate.hasPermission(Perm.VANISH_SEE_VANISHED)) {
            for(Player vanishedPlayer : vanished) {
                playerToUpdate.hidePlayer(plugin, vanishedPlayer);
            }
        }
    }

    public Set<Player> getVanished() {
        return vanished;
    }

    public boolean isVanished(final Player playerToCheck) {
        return vanished.contains(playerToCheck);
    }

}
