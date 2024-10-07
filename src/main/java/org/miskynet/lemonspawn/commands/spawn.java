package org.miskynet.lemonspawn.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.miskynet.lemonspawn.Main;
import org.miskynet.lemonspawn.utils.methodes;
import org.miskynet.lemonspawn.utils.spawnConfig;

import java.util.HashMap;

public class spawn implements CommandExecutor {

    static HashMap<Player, Integer> list = new HashMap<>();

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (!(commandSender.hasPermission("lemonspawn.spawn"))) {
            commandSender.sendMessage(methodes.getString("noPermission"));
            return true;
        }

        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(methodes.getString("senderNoPlayer"));
            return true;
        }

        Player player = (Player) commandSender;

        if (!(list.containsKey(player))) {
            list.put(player, Main.getInstance().getConfig().getInt("teleportTime"));
            count(player);
        }else {
            player.sendMessage(Main.getInstance().getConfig().getString("teleportCooldownMessage"));
        }

        return true;
    }

    public static void count(Player player) {

        new BukkitRunnable() {
            @Override
            public void run() {
                Integer countdown = list.get(player);
                if (countdown != null) {
                    if (countdown != 0) {
                        if (spawnConfig.get().get("spawn") == null) {
                            player.sendMessage(methodes.getString("noSpawnSet"));
                            cancel();
                        }else {
                            if (Main.getInstance().getConfig().getList("teleportMessages").contains(countdown)) {
                                player.sendMessage(Main.getInstance().getConfig().getString("teleportInMessage").replace("%time%", countdown.toString()));
                            }
                            list.put(player, countdown - 1);
                        }
                    } else {
                        list.remove(player);
                        player.teleport(spawnConfig.get().getLocation("spawn"));
                        player.sendMessage(methodes.getString("teleportMessage"));
                        cancel();
                    }
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(Main.getInstance(), 0, 20);
    }
}
