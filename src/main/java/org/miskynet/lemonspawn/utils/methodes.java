package org.miskynet.lemonspawn.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.miskynet.lemonspawn.Main;

public class methodes {

    public static void setSpawn(CommandSender commandSender) {

        if (!(commandSender.hasPermission("lemonspawn.setspawn"))) {
            commandSender.sendMessage(getString("noPermission"));
            return;
        }

        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(getString("senderNoPlayer"));
            return;
        }

        Player player = (Player) commandSender;

        Location location = player.getLocation();
        spawnConfig.get().set("spawn", location);
        spawnConfig.save();

        commandSender.sendMessage(getString("spawnSet"));
    }

    public static String getString(String path) {

        if (Main.getInstance().getConfig().getString(path) != null) {
            String string = Main.getInstance().getConfig().getString(path);
            string = string.replace("%lemonspawn%", "§x§F§F§C§7§0§0LemonSpawn");
            return string;
        }else {
            Bukkit.getLogger().severe(" ");
            Bukkit.getLogger().severe("An error occured while loading a string: " + path);
            Bukkit.getLogger().severe("This error occures when a string is not represented in the config.yml");
            return "An error occurred while loading a string";
        }
    }

}
