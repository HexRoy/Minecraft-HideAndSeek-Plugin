package me.hexroy.hideandseek.commands;

import me.hexroy.hideandseek.HideAndSeek;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

public class EndGameCommand implements CommandExecutor {

    private Plugin plugin = HideAndSeek.getPlugin(HideAndSeek.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {



        return false;
    }
}
