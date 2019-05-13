package me.markiscool.betteressentialsv2;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.permissions.Permission;


public class Warp {

    private String name;
    private Location location;
    private Permission permission;

    /**
     * For creating completely new warps, by users in game.
     * @param name Name of the warp
     * @param location Location of the warp
     */
    public Warp(String name, Location location) {
        this.name = name;
        this.location = location;
        this.permission = new Permission("betteressentialsv2.warp." + name.toLowerCase());
    }

    public Warp(final ConfigurationSection cfgsec) {
        this.name = cfgsec.getName();
        this.permission = new Permission("betteressentialsv2.warp." + name.toLowerCase());

        final ConfigurationSection locsec = cfgsec.getConfigurationSection("location");
        World world = Bukkit.getWorld(locsec.getString("world"));
        double x = locsec.getDouble("x");
        double y = locsec.getDouble("y");
        double z = locsec.getDouble("z");
        float yaw = (float) locsec.getDouble("yaw");
        float pitch = (float) locsec.getDouble("pitch");
        this.location = new Location(world, x, y, z, yaw, pitch);
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public Permission getPermission() {
        return permission;
    }

    public void push(final ConfigurationSection cfgsec) {
        cfgsec.set("permission", permission.getName());
        final ConfigurationSection sec = cfgsec.createSection(name + ".location");
        sec.set("world", location.getWorld());
        sec.set("x", location.getX());
        sec.set("y", location.getY());
        sec.set("z", location.getZ());
        sec.set("yaw", location.getYaw());
        sec.set("pitch", location.getPitch());
    }

}
