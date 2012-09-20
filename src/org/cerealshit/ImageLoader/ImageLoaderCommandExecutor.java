
package org.cerealshit.ImageLoader;

import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.Command;

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
}
