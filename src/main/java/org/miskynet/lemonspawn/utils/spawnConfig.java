package org.miskynet.lemonspawn.utils;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.miskynet.lemonspawn.Main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class spawnConfig {

    private static File file;
    private static FileConfiguration customFile;

    private static String pluginName = Main.getInstance().getName();
    private static String filePath = "spawn.yml";
    public static void setup() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin(pluginName).getDataFolder(), filePath);

        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
                Bukkit.getServer().getPluginManager().getPlugin(pluginName).saveResource(filePath, true);
            } catch (IOException exception) {
                errorOccurred(exception, "while creating the file");
            }
        }
        customFile = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get() {
        return customFile;
    }

    public static void save() {
        try {
            customFile.save(file);
        } catch (IOException exception) {
            errorOccurred(exception, "while saving the file");
        }
    }

    public static void reload() {
        customFile = YamlConfiguration.loadConfiguration(file);
    }

    private static void errorOccurred(IOException exception, String string) {
        Bukkit.getLogger().warning(" ");
        Bukkit.getLogger().warning("An error occurred " + string + ":");
        exception.printStackTrace();
        Bukkit.getLogger().warning(" ");
    }

}
