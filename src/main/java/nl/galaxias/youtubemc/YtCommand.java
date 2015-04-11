package nl.galaxias.youtubemc;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

/**
 * Created by wdele on 10-04-15.
 */
public class YtCommand implements CommandExecutor, Listener {
    private YouTubersGui gui = new YouTubersGui();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("yt")) {
            gui.openGUI(player, Bukkit.getOnlinePlayers().toArray(new Player[Bukkit.getOnlinePlayers().size()]), 1, new ItemStack(Material.SKULL_ITEM, 1, (byte) 3));

            return true;
        }
        return false;
    }
}