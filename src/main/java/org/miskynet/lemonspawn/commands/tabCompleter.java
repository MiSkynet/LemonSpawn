package org.miskynet.lemonspawn.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class tabCompleter implements TabCompleter {

    static List<String> list = new ArrayList<>();

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (strings.length == 1) {
            createTab("reload", strings);
            createTab("setspawn", strings);
        }

        return list;
    }

    private static void createTab(String string, String[] strings) {
        if (string.startsWith(strings[0])) {
            list.add(string);
        }

    }

}
