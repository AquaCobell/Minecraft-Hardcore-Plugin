package com.nico.hardcore;

import org.bukkit.plugin.java.JavaPlugin;
import com.nico.hardcore.Commands.Death;
import com.nico.hardcore.Events.OnDeath;

/**
 * Hello world!
 *
 */
public class App extends JavaPlugin
{

    public static String currentWorldname = "world";
    private static App instance;

    
    @Override
    public void onEnable() 
    {
        getLogger().info("[Hardcore-Multiplayer] Plugin enabled!");
        instance = this;

        this.getCommand("death").setExecutor(new Death());
        getServer().getPluginManager().registerEvents(new OnDeath(), this);

        this.getConfig().getString("current World");
    }
    @Override
    public void onDisable() 
    {
        getLogger().info("[Hardcore-Multiplayer] Plugin disabled!");
    }

    public static App getInstance() {
        return instance;
    }

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        

    }


    public static String getCurrentWorldname() {
        return currentWorldname;
    }
    
    public static void setCurrentWorldname(String currentWorldname) {
    App.currentWorldname = currentWorldname;
    }
    
}
