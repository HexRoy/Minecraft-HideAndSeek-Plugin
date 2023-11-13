package me.hexroy.hideandseek.commands;

import me.hexroy.hideandseek.HideAndSeek;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

import javax.annotation.processing.Messager;

public class ClassCommand  implements CommandExecutor {

    private Plugin plugin = HideAndSeek.getPlugin(HideAndSeek.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("class")) {
            if (sender instanceof Player player) {
                player.sendMessage("Choosing a class will be implemented in V2.0");
            }
        }
        return false;
    }
}


