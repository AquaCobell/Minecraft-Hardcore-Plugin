package com.nico.hardcore.Events;

import java.net.http.WebSocket.Listener;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import com.nico.hardcore.App;
import com.nico.hardcore.Commands.Death;

public class OnDeath implements Listener, org.bukkit.event.Listener
{
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event)
    {
        Player died =  event.getEntity();
        Death.death(died);
    }

    
    //Eventuell Break
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        if(event.getPlayer().getWorld().getName() != App.currentWorldname)
        {
            World currentWorld = Bukkit.getWorld(App.currentWorldname);
            if(currentWorld != null)
            {
                event.getPlayer().getInventory().clear();
                event.getPlayer().setExp(0);
                event.getPlayer().setHealth( event.getPlayer().getMaxHealth());
                event.getPlayer().teleport(currentWorld.getSpawnLocation());

            }
          
        }
    }




}
