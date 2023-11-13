package me.hexroy.hideandseek.listeners;

import me.hexroy.hideandseek.HideAndSeek;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class onBlockBreakEventListener implements Listener {
    private Plugin plugin = HideAndSeek.getPlugin(HideAndSeek.class);

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        plugin.reloadConfig();
        String role = plugin.getConfig().getString("player_data." + event.getPlayer().getUniqueId() + ".role");
        if (Objects.equals(role, "hider")){
            event.setCancelled(true);
        }

    }
}
