package nl.galaxias.youtubemc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by wdele on 10-04-15.
 */
public class RecordCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("record")) {
            if (sender.hasPermission("youtubemc.youtuber")) {
                if (args.length > 0) {
                    if (args[0].equals("start")) {
                        if (!(YouTubeMC.recording.containsKey(sender.getName()))) {
                            if (args.length == 2) {
                                String videoname = args[1];

                                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', YouTubeMC.getPlugin().getConfig().getString("messages.start-recording")).replaceAll("!youtuber!", sender.getName()).replaceAll("!videoname!", videoname));

                                if (YouTubeMC.getPlugin().getConfig().getBoolean("general.enable-titles")) {
                                    Title title = new Title(ChatColor.translateAlternateColorCodes('&', YouTubeMC.getPlugin().getConfig().getString("messages.start-recording")).replaceAll("!youtuber!", sender.getName()).replaceAll("!videoname!", videoname));
                                    title.setTitleColor(ChatColor.GREEN);

                                    title.broadcast();
                                }
                            }
                            else if (args.length == 1) {
                                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', YouTubeMC.getPlugin().getConfig().getString("messages.start-recording")).replaceAll("!youtuber!", sender.getName()));

                                if (YouTubeMC.getPlugin().getConfig().getBoolean("general.enable-titles")) {
                                    Title title = new Title(ChatColor.translateAlternateColorCodes('&', YouTubeMC.getPlugin().getConfig().getString("messages.start-recording")).replaceAll("!youtuber!", sender.getName()));
                                    title.setTitleColor(ChatColor.GREEN);

                                    title.broadcast();
                                }
                            }

                            YouTubeMC.recording.put(sender.getName(), true);
                        }
                        else {
                            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', YouTubeMC.getPlugin().getConfig().getString("messages.already-recording")).replaceAll("!youtuber!", sender.getName()));
                        }
                    }
                    else if (args[0].equals("stop")) {

                        if (YouTubeMC.recording.containsKey(sender.getName())) {
                            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', YouTubeMC.getPlugin().getConfig().getString("messages.stop-recording")).replaceAll("!youtuber!", sender.getName()));

                            if (YouTubeMC.getPlugin().getConfig().getBoolean("general.enable-titles")) {
                                Title title = new Title(ChatColor.translateAlternateColorCodes('&', YouTubeMC.getPlugin().getConfig().getString("messages.stop-recording")).replaceAll("!youtuber!", sender.getName()));
                                title.setTitleColor(ChatColor.RED);

                                title.broadcast();
                            }

                            YouTubeMC.recording.remove(sender.getName());
                        }
                        else {
                            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', YouTubeMC.getPlugin().getConfig().getString("messages.not-recording")).replaceAll("!youtuber!", sender.getName()));
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