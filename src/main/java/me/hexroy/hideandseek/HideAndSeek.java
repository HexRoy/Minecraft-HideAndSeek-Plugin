package me.hexroy.hideandseek;

//Import Commands
import me.hexroy.hideandseek.commands.ClassCommand;
import me.hexroy.hideandseek.commands.StartGameEndGameCommands;

//Import Listeners
import me.hexroy.hideandseek.listeners.onBlockBreakEventListener;
import me.hexroy.hideandseek.listeners.onPlayerDeathEventListener;
import me.hexroy.hideandseek.listeners.onPlayerJoinListener;
import me.hexroy.hideandseek.listeners.onPlayerRespawnListener;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.java.JavaPlugin;


public final class HideAndSeek extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("Hide and seek enabled");

        // Config Default
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        // Resetting in_game variable
        if (getConfig().getBoolean("in_game")){
            getConfig().set("in_game", false);
            saveConfig();
        }

        // Event Listeners
        getServer().getPluginManager().registerEvents(new onPlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new onBlockBreakEventListener(), this);
        getServer().getPluginManager().registerEvents(new onPlayerDeathEventListener(), this);
        getServer().getPluginManager().registerEvents(new onPlayerRespawnListener(), this);

        // Commands
        getCommand("start_game").setExecutor(new StartGameEndGameCommands());
        getCommand("end_game").setExecutor(new StartGameEndGameCommands());
        getCommand("class").setExecutor(new ClassCommand());
    }


    @Override
    public void onDisable() {
        System.out.println("Hide and seek disabled");
    }
}
