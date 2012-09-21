
package org.cerealshit.ImageLoader;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ImageLoaderCommandExecutor implements CommandExecutor {
    private ImageLoader plugin;
    
    public ImageLoaderCommandExecutor(ImageLoader plugin) {
        this.plugin = plugin;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
        sender.sendMessage("you just executed this the /" + cmd.getName() + " of the image Loader plugin :)");
        if(sender instanceof Player) {
            if(args.length >= 1) {
                BufferedImage img = null;
                try {
                    img = ImageIO.read(new File(args[0]));

                    loadImage(((Player)sender).getLocation(), convertImageToBlockIDs(img));

                } catch(IOException ex) {             
                }
            } else {
                return false;
            }
        } else {
            sender.sendMessage("you con't do this on the console");
        }
        return true;
    }
    
    public ArrayList<ArrayList<Integer>> convertImageToBlockIDs(BufferedImage img) {
        ArrayList<ArrayList<Integer>> blockidmap = new ArrayList<ArrayList<Integer>>();
                
        Color[] block_colors = {
            new Color(0xfcfcfc), //white
            new Color(0xe6792d), //orange
            new Color(0xc153cb), //lila 
            new Color(0x698bd2), //cyan
            new Color(0xd9cc28), //yellow
            new Color(0x36ab2b), //green
            new Color(0xd68098), //pink
            new Color(0x454545), //darkgrey
            new Color(0xa1a1a1), //grey
            new Color(0x287898), //blue
            new Color(0x893ecc), //violet
            new Color(0x27339a), //darkblue
            new Color(0x59351d), //brown
            new Color(0x3b521a), //darkgreen
            new Color(0xa72d29), //red
            new Color(0x171717)  // black
        };

        for(int y = 0; y < img.getHeight(); y++) {
            ArrayList<Integer> row = new ArrayList<Integer>();
            for(int x = 0; x < img.getWidth(); x++) {
                row.add(getIdOfClosestColor(block_colors, new Color(img.getRGB(x, y))));
            }
            blockidmap.add(row);
        }
        return blockidmap;
    }
    
    public int getIdOfClosestColor(Color[] colors, Color compare) {
        int mindiff = 0x4ff;
        int currentdiff = 0;
        int id = 0;
        for(int i = 0; i < colors.length; i++) {
            currentdiff = Math.abs(colors[i].getBlue() - compare.getBlue())
                    + Math.abs(colors[i].getGreen() - compare.getGreen())
                    + Math.abs(colors[i].getRed() - compare.getRed());
            if(currentdiff < mindiff) {
                mindiff = currentdiff;
                id = i;
            }
        }
        return id;
    }
    
    public void loadImage(Location location,ArrayList<ArrayList<Integer>> blockIds){
        
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        Location setLocation = new Location(location.getWorld(),x,y,z);
        
        for(ArrayList<Integer> lineBlockId: blockIds)
        {   
            setLocation.setY(setLocation.getY() + 1);
            setLocation.setX(x);
            for(Integer blockId:lineBlockId)
            {
                setLocation.setX(setLocation.getX() + 1);
                Block bleockli = setLocation.getBlock();
                bleockli.setTypeIdAndData(0x23, (byte)((int)blockId), false);
            }
        }
            
    }
}
