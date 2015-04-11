package nl.galaxias.youtubemc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by wdele on 10-04-15.
 */
public class LivestreamCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("livestream")) {
            if (sender.hasPermission("youtubemc.youtuber")) {
                if (args.length > 0) {
                    if (args[0].equals("start")) {
                        if (args.length == 2) {
                            String url = args[1];

                            if (!(YouTubeMC.livestreaming.containsKey(sender.getName()))) {
                                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', YouTubeMC.getPlugin().getConfig().getString("messages.start-livestream")).replaceAll("!youtuber!", sender.getName()).replaceAll("!url!", url));

                                YouTubeMC.livestreaming.put(sender.getName(), url);
                            }
                            else {
                                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', YouTubeMC.getPlugin().getConfig().getString("messages.already-recording")).replaceAll("!youtuber!", sender.getName()));
                            }
                        }
                        else if (args.length < 2) {
                            sender.sendMessage(ChatColor.RED + "/livestream start url");
                        }
                    }
                    else if (args[0].equals("stop")) {
                        if (args.length == 2) {
                            String url = args[1];

                            if (YouTubeMC.livestreaming.containsKey(sender.getName())) {
                                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', YouTubeMC.getPlugin().getConfig().getString("messages.stop-livestream")).replaceAll("!youtuber!", sender.getName()).replaceAll("!url!", url));

                                YouTubeMC.livestreaming.remove(sender.getName());
                            }
                            else {
                                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', YouTubeMC.getPlugin().getConfig().getString("messages.not-recording")).replaceAll("!youtuber!", sender.getName()));
                            }
                        }
                        else if (args.length < 2) {
                            sender.sendMessage(ChatColor.RED + "/livestream stop url");
                        }
                    }
                }
                else if (args.length == 0) {
                    sender.sendMessage(ChatColor.RED + "/" + cmd.getName() + " start/stop");
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