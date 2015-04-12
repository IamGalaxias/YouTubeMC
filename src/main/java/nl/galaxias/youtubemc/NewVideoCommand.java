package nl.galaxias.youtubemc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by wdele on 11-04-15.
 */
public class NewVideoCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("newvideo")) {
            if (sender.hasPermission("youtubemc.youtuber")) {
                if (args.length == 2) {
                    String videoname = args[0];
                    String url = args[1];

                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', YouTubeMC.getPlugin().getConfig().getString("messages.new-video")).replaceAll("!youtuber!", sender.getName()).replaceAll("!videoname!", videoname).replaceAll("!url", url));
                }
                else if (args.length == 0) {
                    sender.sendMessage(ChatColor.RED + "/" + cmd.getName() + " videoname url");
                }
            }
            else if (!(sender.hasPermission("youtubemc.youtuber"))) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', YouTubeMC.getPlugin().getConfig().getString("messages.no-permission")));
            }
            return true;
        }
        return false;
    }
}
