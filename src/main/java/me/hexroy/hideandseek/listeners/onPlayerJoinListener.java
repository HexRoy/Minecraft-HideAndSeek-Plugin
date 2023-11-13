package me.hexroy.hideandseek.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class onPlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        String player_name = player.getName();
        event.setJoinMessage("§l§d" + player_name + "§r§o explore for a good area to play hide and seek" + "   §l§d" + "Use /sg" + "§r§o to start a game" + "   §l§d" + "Use /eg" + "§r§o to start a game");
        player.setGameMode(GameMode.SPECTATOR);

    }


}
