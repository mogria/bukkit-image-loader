package org.cerealshit.ImageLoader;

import org.bukkit.plugin.java.JavaPlugin;

public class ImageLoader extends JavaPlugin {
    @Override
    public void onEnable(){
        getLogger().info("ImageLoader has been enabled.");
    }
 
    @Override
    public void onDisable() {
        getLogger().info("ImageLoader has been disabled.");
    }
}
