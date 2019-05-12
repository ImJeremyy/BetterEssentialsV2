package me.markiscool.betteressentialsv2.commands;

import me.markiscool.betteressentialsv2.BetterEssentialsV2Plugin;
import me.markiscool.betteressentialsv2.Util;
import me.markiscool.betteressentialsv2.constants.Lang;
import me.markiscool.betteressentialsv2.constants.Perm;
import me.markiscool.betteressentialsv2.managers.SeenManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SeenCommand implements CommandExecutor {

    private SeenManager sm;

    public SeenCommand(final BetterEssentialsV2Plugin plugin) {
        this.sm = plugin.getSeenManager();
    }

    // seen <player>
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender.hasPermission(Perm.SEEN)) {
            if (args.length == 1) {
                OfflinePlayer targetPlayer = sm.getOfflinePlayer(args[0]);
                if(targetPlayer != null) {
                    if(!targetPlayer.isOnline()) {
                        sender.sendMessage(Util.wrapMessage("&a" + targetPlayer.getName() + " &6was last seen " + getTimeAway(sm.getTimeStamp(targetPlayer))));
                    } else {
                        sender.sendMessage(Util.wrapMessage("&a" + Bukkit.getPlayer(args[0]).getName() + " &6is &aonline&6."));
                    }
                } else {
                    sender.sendMessage(Util.wrapMessage(Lang.PLAYER_NOT_FOUND));
                }
            } else {
                sender.sendMessage(Util.wrapMessage(Lang.INVALID_ARGUMENTS));
            }
        }
        return true;
    }

    private String getTimeAway(long timestamp) {
        long difference = System.currentTimeMillis() - timestamp;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(difference);
        long minutes = TimeUnit.SECONDS.toMinutes(seconds);
        long hours = TimeUnit.MINUTES.toHours(minutes);
        long days = TimeUnit.HOURS.toDays(hours);
        String timeAway = "";

        if(days <= 0) {
            //no days
            if(hours <= 0) {
                //no hours
                if(minutes <= 0) {
                    //no minutes
                    if(seconds <= 0) {
                        //should never reach here
                    } else {
                        //seconds
                        timeAway = "&a" + seconds + " &6seconds ago.";
                    }
                } else {
                    //minutes & seconds
                    seconds %= 60;
                    timeAway = "&a" + minutes + " &6minutes, &a" + seconds + " &6seconds ago.";
                }
            } else {
                //hours, minutes & seconds
                minutes %= 60;
                seconds %= 60;
                timeAway = "&a" + hours + " &6hours, &a" + minutes + " &6minutes, &a" + seconds + " &6seconds ago.";
            }
        } else {
            //days, hours, minutes & seconds
            hours %= 24;
            minutes %= 60;
            seconds %= 60;
            timeAway = "&a" + days + " &6days, &a" + hours + " &6hours, &a" + minutes + " &6minutes, &a" + seconds + " &6seconds ago.";
        }
        return timeAway;
    }
}
