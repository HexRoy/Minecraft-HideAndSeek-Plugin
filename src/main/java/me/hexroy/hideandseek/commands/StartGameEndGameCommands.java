package me.hexroy.hideandseek.commands;

import me.hexroy.hideandseek.HideAndSeek;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ChestedHorse;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.NotNull;

import javax.sound.midi.SoundbankResource;
import java.awt.print.Book;
import java.util.BitSet;
import java.util.Random;
import java.util.UUID;

public class StartGameEndGameCommands implements CommandExecutor {
    private Plugin plugin = HideAndSeek.getPlugin(HideAndSeek.class);
    int time;
    int taskID;


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // Start Game Command
        if (command.getName().equalsIgnoreCase("start_game")){
            if (plugin.getConfig().getBoolean("in_game")) {
                sender.sendMessage("There is already a game of hide and seek. Please wait for it to end or use /eg");
            }
            else {
                if (sender instanceof Player p) {
                    Bukkit.broadcastMessage("§4§n§l" + p.getName() + "§c has started a game of hide and seek");

                    plugin.reloadConfig();
                    plugin.getConfig().set("in_game", true);
                    plugin.saveConfig();

                    // Position Check (Flying in air, or stuck in ground)
                    Location teleport_location = checkPosition(p);

                    for (Player players : Bukkit.getOnlinePlayers()) {
                        Location player_location = players.getLocation();
                        plugin.reloadConfig();
                        plugin.getConfig().set("player_data."+players.getUniqueId()+".location", player_location);
                        plugin.saveConfig();
                        players.sendMessage("Your old location has been saved, teleporting to: " + ((Player) sender).getName());
                        players.setGameMode(GameMode.SURVIVAL);
                        players.teleport(teleport_location);
                        players.setFoodLevel(200);
                    }

                    // Creating the arena
                    Location pos = p.getLocation();
                    double radius = plugin.getConfig().getInt("Arena_Size");
                    World world = p.getWorld();
                    world.getWorldBorder().setSize(radius);
                    world.getWorldBorder().setCenter(pos);

                    plugin.reloadConfig();
                    int duration = plugin.getConfig().getInt("Seeker_Delay");

                    // Selecting the hunter
                    Random rand = new Random();
                    int max_random = Bukkit.getOnlinePlayers().size();
                    int random_int = rand.nextInt(max_random);
                    int current = 0;

                    for (Player player : Bukkit.getOnlinePlayers()) {

                        // Setting Hunter Attributes
                        if (current == random_int) {

                            player.sendMessage("§4 You have been chosen as the Hunter!");

                            // Set the players role to hunter
                            plugin.reloadConfig();
                            plugin.getConfig().set("player_data." + player.getUniqueId()+".role", "hunter");
                            plugin.saveConfig();

                            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(100.0);
                            player.setHealth(100.0);

                            ItemStack netherite_sword = new ItemStack(Material.NETHERITE_SWORD);
                            player.getInventory().setItem(0, netherite_sword);

                            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, duration, 255));
                            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, duration, 250));
                            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, duration, 255));
                            player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, duration, 200));
                            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, duration, 200));
                        }
                        // Setting Hiders Attributes
                        else {
                            player.sendMessage("§2 You have been chosen as a Hider!");

                            // Set the players role to hider
                            plugin.reloadConfig();
                            plugin.getConfig().set("player_data." + player.getUniqueId()+".role", "hider");
                            plugin.saveConfig();

                            player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, duration, 1));
                            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(10.0);
                            player.setHealth(10.0);
                        }
                        ++current;
                    }

                    // Setting up timer variables
                    int ticks = plugin.getConfig().getInt("Game_Length");
                    setTimer(ticks);
                    startTimer();

                }
            }
        }

        // End Game Command
        if (command.getName().equalsIgnoreCase("end_game")) {
            if (!plugin.getConfig().getBoolean("in_game")) {
                sender.sendMessage("There is currently no game of hide and seek. Please use /sg to begin");
            }
            else {
                for (Player player : Bukkit.getOnlinePlayers()) {

                    // Reset world border
                    World world = player.getWorld();
                    world.getWorldBorder().reset();

                    // Set the players role to none
                    plugin.reloadConfig();
                    plugin.getConfig().set("player_data." + player.getUniqueId()+".role", "none");

                    // Resetting Players Attributes
                    player.getInventory().clear();
                    player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
                    player.setGameMode(GameMode.SPECTATOR);
                    player.removePotionEffect(PotionEffectType.SLOW);
                    player.removePotionEffect(PotionEffectType.JUMP);
                    player.removePotionEffect(PotionEffectType.BLINDNESS);
                    player.removePotionEffect(PotionEffectType.WEAKNESS);
                    player.removePotionEffect(PotionEffectType.SLOW_DIGGING);

                    // Resetting Players Location
                    Location old_location = plugin.getConfig().getLocation("player_data." + player.getUniqueId() +".location");
                    if (old_location != null) {
                        player.teleport(old_location);
                    }
                    else {
                        player.sendMessage("LOCATION IS NULL IN EndGameCommand");
                    }
                }
                plugin.reloadConfig();
                plugin.getConfig().set("in_game", false);
                plugin.saveConfig();
                Bukkit.broadcastMessage("§eGame Over");
                Bukkit.getScheduler().cancelTasks(plugin);
            }
        }
        return false;
    }

    @NotNull
    private static Location checkPosition(Player p) {
        Location teleport_location = p.getLocation();
        Location temp = p.getLocation();
        Location temp2 = p.getLocation();
        boolean air = false;
        boolean ground= false;

        // Checks for position in the air
        temp.setY(temp.getY()-1);
        if (temp.getBlock().getType().equals(Material.AIR)){
            air = true;
        }
        while (air){
            temp.setY(temp.getY()-1);
            if (!temp.getBlock().getType().equals(Material.AIR)){
                teleport_location = temp;
                air = false;
                return teleport_location;
            }
        }

        // Checks to see if player is inside of blocks
        temp = teleport_location;
        temp2.setY(temp2.getY() + 1);
        if ((!temp.getBlock().getType().equals(Material.AIR)) || (!temp2.getBlock().getType().equals(Material.AIR))){
            ground = true;
        }
        while (ground){
            temp = temp2;
            temp2.setY(temp2.getY() + 1);
            if ((temp.getBlock().getType().equals(Material.AIR)) || (temp2.getBlock().getType().equals(Material.AIR))){
                teleport_location = temp;
                ground = false;
                return teleport_location;
            }
        }
        return teleport_location;
    }


    public void setTimer(int amount) {
        time = amount;
    }

    public void startTimer() {
        int delay = plugin.getConfig().getInt("Seeker_Delay");
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        taskID = scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                if(time == 0) {
                    Bukkit.broadcastMessage(ChatColor.RED + "Time is up!");
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "eg");
                    stopTimer();
                    return;
                }
                if(time % plugin.getConfig().getInt("Timer_Denominator") == 0) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendTitle(ChatColor.DARK_GREEN + "Time: " + time + " seconds", "", 5, 40, 5);
                    }
                }
                if(time < 10) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendTitle(ChatColor.RED + "Time: " + time + " seconds", "", 5, 20, 5);
                    }
                }
                time = time - 1;
            }
        }, delay, 20L);
    }

    public void stopTimer() {
        Bukkit.getScheduler().cancelTask(taskID);
    }
}