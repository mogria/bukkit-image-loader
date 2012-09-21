
package org.cerealshit.ImageLoader;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.block.*;
import org.bukkit.block.Block;
import org.bukkit.Location;
import java.util.*;


public class ImageLoaderCommandExecutor implements CommandExecutor {
    private ImageLoader plugin;
    
    public ImageLoaderCommandExecutor(ImageLoader plugin) {
        this.plugin = plugin;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
        sender.sendMessage("you just executed this the /" + cmd.getName() + " of the image Loader plugin :)");
        return false;
    }
    
    public void loadImage(Location location,ArrayList<ArrayList<Block>> blocks){
        
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        Location setLocation = new Location(location.getWorld(),x,y,z);
        
        for(ArrayList<Block> lineBlock: blocks)
        {   
            setLocation.setY(setLocation.getY() + 1);
            setLocation.setX(x);
            for(Block block:lineBlock)
            {
                setLocation.setX(setLocation.getX() + 1);
                Block bleockli = setLocation.getBlock();
                bleockli.setTypeId(block.getTypeId());
                
            }
        }
            
    }


}
