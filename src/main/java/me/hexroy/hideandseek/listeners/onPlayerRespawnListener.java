package me.hexroy.hideandseek.listeners;
import me.hexroy.hideandseek.HideAndSeek;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.Plugin;


public class onPlayerRespawnListener implements  Listener{
    private Plugin plugin = HideAndSeek.getPlugin(HideAndSeek.class);

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event){
        Player player = event.getPlayer();
        Location location = plugin.getConfig().getLocation("player_data." + player.getUniqueId() + (".location"));

        if (location != null) {
            player.teleport(location);
            player.sendMessage("You have been teleported back to your old location");
        }
        else {
            player.sendMessage("LOCATION IS NULL IN OnPlayerRespawnListener");
        }


    }


}







