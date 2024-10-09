package org.miskynet.lemonspawn.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.miskynet.lemonspawn.Main;
import org.miskynet.lemonspawn.utils.methodes;
import org.miskynet.lemonspawn.utils.spawnConfig;

public class lemonSpawnManager implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (strings.length != 1) {
            commandSender.sendMessage("§cThis is not how the command works. Try /ls <action>");
            return true;
        }

        switch (strings[0].toLowerCase()) {
            case "reload":
                if (!(commandSender.hasPermission("lemonspawn.reload"))) {
                    commandSender.sendMessage(methodes.getString("noPermission"));
                    return true;
                }

                spawnConfig.reload();
                Main.getInstance().getConfig().options().copyDefaults();
                Main.getInstance().saveDefaultConfig();
                Main.getInstance().reloadConfig();
                commandSender.sendMessage(methodes.getString("reload"));
                break;

            case "setspawn":
                methodes.setSpawn(commandSender);
                break;
        }

        return false;
    }
}
