package me.hexroy.hideandseek.listeners;

import me.hexroy.hideandseek.HideAndSeek;
import me.hexroy.hideandseek.commands.EndGameCommand;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;


import java.net.http.WebSocket;



public class onPlayerDeathEventListener implements Listener {
    private Plugin plugin = HideAndSeek.getPlugin(HideAndSeek.class);

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity().getPlayer();
        String player_name = player.getName();
        player.setGameMode(GameMode.SPECTATOR);

        if (plugin.getConfig().getBoolean("in_game")) {
            event.setDeathMessage("§m§l§4" + player_name + "§r§c has been hunted!");

            // Checks to see number of players in game
            int players_left = 0;
            for (Player players : Bukkit.getOnlinePlayers()) {
                if (players.getGameMode() == GameMode.SURVIVAL) {
                    ++players_left;
                }
            }

            // If the hunter is the last person alive, end game
            if (players_left <= 1) {
                player.performCommand("eg");
            }
        }
    }
}
