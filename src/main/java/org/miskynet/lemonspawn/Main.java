package org.miskynet.lemonspawn;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.miskynet.lemonspawn.commands.lemonSpawnManager;
import org.miskynet.lemonspawn.commands.spawn;
import org.miskynet.lemonspawn.commands.tabCompleter;
import org.miskynet.lemonspawn.utils.spawnConfig;

public class Main extends JavaPlugin {

    private static Main instance;
    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        getConfig().options().copyDefaults();
        saveDefaultConfig();
        spawnConfig.setup();

        getCommand("lemonspawn").setTabCompleter(new tabCompleter());
        getCommand("lemonspawn").setExecutor(new lemonSpawnManager());
        getCommand("spawn").setExecutor(new spawn());

        Bukkit.getLogger().info("[LemonSpawn] LemonSpawn loaded...");
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("[LemonSpawn] LemonSpawn unloaded...");
    }

}
