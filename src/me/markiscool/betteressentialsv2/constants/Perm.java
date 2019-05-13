package me.markiscool.betteressentialsv2.constants;

import org.bukkit.permissions.Permission;

public class Perm {

    public static Permission CLEAR_INVENTORY;
    public static Permission CLEAR_INVENTORY_OTHERS;

    public static Permission WARP;
    public static Permission WARP_SET;
    public static Permission WARP_DELETE;
    public static Permission WARP_LIST;

    public static Permission FLY_SELF;
    public static Permission FLY_OTHERS;

    public static Permission SPEED_SELF;
    public static Permission SPEED_OTHERS;

    public static Permission HEAL_SELF;
    public static Permission HEAL_OTHERS;

    public static Permission FEED_SELF;
    public static Permission FEED_OTHERS;

    public static Permission VANISH_SELF;
    public static Permission VANISH_OTHERS;
    public static Permission VANISH_SEE_VANISHED;

    public static Permission GAMEMODE_SELF;
    public static Permission GAMEMODE_OTHERS;

    public static Permission GIVE_SELF;
    public static Permission GIVE_OTHERS;

    public static Permission SET_SPAWN;
    public static Permission SPAWN;

    public static Permission SEEN;

    public static Permission BALANCE_SELF;
    public static Permission BALANCE_OTHERS;

    public static Permission BALANCE_TOP;
    public static Permission ECONOMY;

    private final static String prefix = "betteressentialsv2.";

    static {
        CLEAR_INVENTORY = new Permission(prefix + "clearinventory");
        CLEAR_INVENTORY_OTHERS = new Permission(prefix + "clearinventory.others");

        WARP = new Permission(prefix + "warp");
        WARP_SET = new Permission(prefix + "warp.set");
        WARP_DELETE = new Permission(prefix + "warp.delete");
        WARP_LIST = new Permission(prefix + "warp.list");

        FLY_SELF = new Permission(prefix + "fly");
        FLY_OTHERS = new Permission(prefix + "fly.others");

        SPEED_SELF = new Permission(prefix + "speed");
        SPEED_OTHERS = new Permission(prefix + "speed.others");

        HEAL_SELF = new Permission(prefix + "heal");
        HEAL_OTHERS = new Permission(prefix + "heal.others");

        FEED_SELF = new Permission(prefix + "feed");
        FEED_OTHERS = new Permission(prefix + "feed.others");

        VANISH_SELF = new Permission(prefix + "vanish");
        VANISH_OTHERS = new Permission(prefix + "vanish.others");
        VANISH_SEE_VANISHED = new Permission(prefix + "vanish.seevanished");

        GAMEMODE_SELF = new Permission(prefix + "gamemode");
        GAMEMODE_OTHERS = new Permission(prefix + "gamemode.others");

        GIVE_SELF = new Permission(prefix + "give");
        GIVE_OTHERS = new Permission(prefix + "give.others");

        SEEN = new Permission(prefix + "seen");

        SET_SPAWN = new Permission(prefix + "spawn.set");
        SPAWN = new Permission(prefix + "spawn");

        BALANCE_SELF = new Permission(prefix + "balance");
        BALANCE_OTHERS = new Permission(prefix + "balance.others");

        BALANCE_TOP = new Permission(prefix + "balance.top");

        CLEAR_INVENTORY.addParent(CLEAR_INVENTORY_OTHERS, true);
        FLY_SELF.addParent(FLY_OTHERS, true);
        SPEED_SELF.addParent(SPEED_OTHERS, true);
        HEAL_SELF.addParent(HEAL_OTHERS, true);
        FEED_SELF.addParent(FEED_OTHERS, true);
        VANISH_SELF.addParent(VANISH_OTHERS, true);
        GAMEMODE_SELF.addParent(GAMEMODE_OTHERS, true);
        GIVE_SELF.addParent(GIVE_OTHERS, true);
        BALANCE_SELF.addParent(BALANCE_OTHERS, true);
    }

}
