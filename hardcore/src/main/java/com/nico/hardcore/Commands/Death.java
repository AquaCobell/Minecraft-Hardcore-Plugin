package com.nico.hardcore.Commands;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import com.nico.hardcore.App;

public class Death implements CommandExecutor 
{

    static boolean deathsentence = false;


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // TODO Auto-generated method stub

        sender.sendMessage("OK");
        death((Player) sender);
        return true;
    }

    public static void death(Player player)
    {

        if(deathsentence == false)
        {
            deathsentence = true;

            final World currentworld =  player.getWorld();

            for (Player players : Bukkit.getOnlinePlayers()) {
                players.sendTitle("§4" + player.getName() + "§f ist gestorben.", "§a Welt wird neu generiert", 1, 400, 100);
                players.playSound(player.getLocation(), Sound.ENTITY_WITHER_SPAWN, 0.5f, 0f);
                PotionEffect blindnessEffect = new PotionEffect(PotionEffectType.BLINDNESS, 500, 1);
                players.getInventory().clear();
                players.setExp(0);
                players.setHealth(players.getMaxHealth());
                players.addPotionEffect(blindnessEffect);
                
            }

            Bukkit.getScheduler().runTaskLater(App.getInstance(), new Runnable() 
            {
                
                @Override
                public void run() 
                {

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");


                    Date date = new Date();
                    String formatiertesDatum = dateFormat.format(date);
                    
                    Bukkit.createWorld(WorldCreator.name(formatiertesDatum));
                    
                    World world = Bukkit.getWorld(formatiertesDatum);


                    //Eventuell break
                    App.getInstance().getConfig().set("current World", formatiertesDatum);
                    App.setCurrentWorldname(formatiertesDatum);
                    for (Player players : Bukkit.getOnlinePlayers()) 
                    {
                        //players.setHealth(players.getMaxHealth());
                        //players.getInventory().clear();
                        //players.setExp(0);

                        players.setBedSpawnLocation(world.getSpawnLocation(), true);
                        players.teleport(world.getSpawnLocation());
                        players.getInventory().clear();
                        players.setFoodLevel(50);
                        players.setHealth(players.getMaxHealth());
                        if(players.hasPotionEffect(PotionEffectType.BLINDNESS))
                        {
                            players.removePotionEffect(PotionEffectType.BLINDNESS);
                        }
                        players.setFireTicks(0);

                    }


                    Bukkit.getServer().unloadWorld(currentworld, true);
                    deathsentence = false;
                    


                
                }
            }, 100L); 



        }
       
       
      
       
        
       
           /*  Bukkit.createWorld(WorldCreator.name("world2"));
                Bukkit.broadcastMessage("Finished Creating World");

                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) 
                {
                    onlinePlayer.teleport(Bukkit.getWorld("world2").getSpawnLocation());
                }
                Bukkit.broadcastMessage("Teleport finished");
         */   


    }

    
}
