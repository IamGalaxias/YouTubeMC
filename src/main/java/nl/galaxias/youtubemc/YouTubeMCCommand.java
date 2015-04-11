package nl.galaxias.youtubemc;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by wdele on 10-04-15.
 */
public class YouTubeMCCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("youtubemc")) {
            sender.sendMessage(ChatColor.GOLD + "YouTubeMC version " + YouTubeMC.getVersion() + ", by " + ChatColor.GREEN + "Galaxias");
            return true;
        }
        return false;
    }
}