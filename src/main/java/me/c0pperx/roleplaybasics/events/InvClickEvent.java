package me.c0pperx.roleplaybasics.events;

import me.c0pperx.roleplaybasics.RolePlayBasics;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InvClickEvent implements Listener {
    private final RolePlayBasics plugin;

    public InvClickEvent(RolePlayBasics plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void clickEvent(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();

        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "RolePlayBasics - GUI")) {
            if (e.getCurrentItem().getType() == Material.DIAMOND) {
                plugin.reloadConfig();
                player.sendMessage(ChatColor.RED + "[RolePlayBasics]" + ChatColor.RESET + " Plugin successfully reloaded.");
            }
            e.setCancelled(true);
        }
    }
}
