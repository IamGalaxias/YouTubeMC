package nl.galaxias.youtubemc;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by wdele on 11-04-15.
 */
public class YouTubersGui implements Listener
{

    private Inventory youtubers;

    public void openGUI(Player p, Player[] players, int rows, ItemStack item)
    {
        ItemStack i = item;
        ItemMeta m = i.getItemMeta();
        youtubers = Bukkit.createInventory(p, rows * 9, "Click on a name");
        for(int z = 0; z < players.length; z++)
        {
            m.setDisplayName(players[z].getName());
            i.setItemMeta(m);
            youtubers.setItem(z, i);
        }
        p.openInventory(youtubers);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event)
    {
        if(event.getInventory().getTitle().equalsIgnoreCase("Click on a name"))
        {
            if(event.getCurrentItem() != null && event.getCurrentItem().getType() != null)
            {
                event.setCancelled(true);
                Player p = (Player) event.getWhoClicked();
                if(event.getCurrentItem().hasItemMeta() && event.getCurrentItem().getItemMeta().hasDisplayName())
                {
                    Player c = Bukkit.getPlayer(event.getCurrentItem().getItemMeta().getDisplayName().trim());
                    if(c != null)
                    {
                        Bukkit.dispatchCommand(p, "/tpa " + c);
                    }
                }
            }
        }
    }
}