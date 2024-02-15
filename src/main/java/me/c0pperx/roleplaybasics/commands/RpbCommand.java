package me.c0pperx.roleplaybasics.commands;

import me.c0pperx.roleplaybasics.RolePlayBasics;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class RpbCommand implements CommandExecutor {
    private final RolePlayBasics plugin;

    public RpbCommand(RolePlayBasics plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;

            Inventory gui = Bukkit.createInventory(player, 9, ChatColor.RED + "RolePlayBasics - GUI");

            ItemStack distances = new ItemStack(Material.ARROW);
            ItemStack settings = new ItemStack(Material.WHITE_BANNER);
            ItemStack messageColors = new ItemStack(Material.RED_BANNER);
            ItemStack reload = new ItemStack(Material.DIAMOND);

            String emoteMessageDistance = plugin.getConfig().getString("emote-message-distance");
            String localChatDistance = plugin.getConfig().getString("local-chat-distance");
            String shoutMessageDistance = plugin.getConfig().getString("shout-chat-distance");
            String whisperMessageDistance = plugin.getConfig().getString("whisper-chat-distance");
            ItemMeta distancesMeta = distances.getItemMeta();
            distancesMeta.setDisplayName(ChatColor.GOLD + "Message Distances");
            ArrayList<String> distances_lore = new ArrayList<>();
            distances_lore.add(ChatColor.DARK_PURPLE + "Emote message distance: " + ChatColor.WHITE + emoteMessageDistance + " blocks");
            distances_lore.add(ChatColor.GRAY + "Local chat distance: " + ChatColor.WHITE + localChatDistance + " blocks");
            distances_lore.add(ChatColor.AQUA + "Shout chat distance: " + ChatColor.WHITE + shoutMessageDistance + " blocks");
            distances_lore.add(ChatColor.DARK_GRAY + "Whisper chat distance: " + ChatColor.WHITE + whisperMessageDistance + " blocks");
            distancesMeta.setLore(distances_lore);
            distances.setItemMeta(distancesMeta);

            String localMessageColor = plugin.getConfig().getString("local-message-color");
            String localDistanceMessageColor = plugin.getConfig().getString("local-distance-message-color");
            String shoutMessageColor = plugin.getConfig().getString("shout-message-color");
            String shoutDistanceMessageColor = plugin.getConfig().getString("shout-distance-message-color");
            String whisperMessageColor = plugin.getConfig().getString("whisper-message-color");
            String whisperDistanceMessageColor = plugin.getConfig().getString("whisper-distance-message-color");
            String localOocMessageColor = plugin.getConfig().getString("localooc-message-color");
            String oocMessageColor = plugin.getConfig().getString("ooc-message-color");
            String staffChatMessageColor = plugin.getConfig().getString("staff-chat-message-color");
            ItemMeta msgColorsMeta = messageColors.getItemMeta();
            msgColorsMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Message Colors");
            ArrayList<String> msgColors_lore = new ArrayList<>();
            msgColors_lore.add(ChatColor.WHITE + "Local message color: " + ChatColor.translateAlternateColorCodes('&', localMessageColor != null ? localMessageColor : "&f") + localMessageColor);
            msgColors_lore.add(ChatColor.GRAY + "Local distance message color: " + ChatColor.translateAlternateColorCodes('&', localDistanceMessageColor != null ? localDistanceMessageColor : "&7") + localDistanceMessageColor);
            msgColors_lore.add(ChatColor.DARK_AQUA + "Shout message color: " + ChatColor.translateAlternateColorCodes('&', shoutMessageColor != null ? shoutMessageColor : "&3") + shoutMessageColor);
            msgColors_lore.add(ChatColor.BLUE + "Shout distance message color: " + ChatColor.translateAlternateColorCodes('&', shoutDistanceMessageColor != null ? shoutDistanceMessageColor : "&9") + shoutDistanceMessageColor);
            msgColors_lore.add(ChatColor.GRAY + "Whisper message color: " + ChatColor.translateAlternateColorCodes('&', whisperMessageColor != null ? whisperMessageColor : "&7") + whisperMessageColor);
            msgColors_lore.add(ChatColor.DARK_GRAY + "Whisper distance message color: " + ChatColor.translateAlternateColorCodes('&', whisperDistanceMessageColor != null ? whisperDistanceMessageColor : "&8") + whisperDistanceMessageColor);
            msgColors_lore.add(ChatColor.DARK_PURPLE + "Local OOC message color: " + ChatColor.translateAlternateColorCodes('&', localOocMessageColor != null ? localOocMessageColor : "&5") + localOocMessageColor);
            msgColors_lore.add(ChatColor.LIGHT_PURPLE + "OOC message color: " + ChatColor.translateAlternateColorCodes('&', oocMessageColor != null ? oocMessageColor : "&d") + oocMessageColor);
            msgColors_lore.add(ChatColor.AQUA + "Staff Chat message color: " + ChatColor.translateAlternateColorCodes('&', staffChatMessageColor != null ? staffChatMessageColor : "&b") + staffChatMessageColor);
            msgColorsMeta.setLore(msgColors_lore);
            messageColors.setItemMeta(msgColorsMeta);

            boolean localChatMode = plugin.getConfig().getBoolean("local-chat-mode");
            boolean formatMode = plugin.getConfig().getBoolean("text-formatting");
            ItemMeta settingsMeta = settings.getItemMeta();
            settingsMeta.setDisplayName(ChatColor.AQUA + "Settings");
            ArrayList<String> settings_lore = new ArrayList<>();
            settings_lore.add(ChatColor.GRAY + "Local chat mode: " + (localChatMode ? ChatColor.GREEN : ChatColor.RED) + localChatMode);
            settings_lore.add(ChatColor.BLUE + "Text format: " + (formatMode ? ChatColor.GREEN : ChatColor.RED) + formatMode);
            settingsMeta.setLore(settings_lore);
            settings.setItemMeta(settingsMeta);

            ItemMeta reloadMeta = reload.getItemMeta();
            reloadMeta.setDisplayName(ChatColor.DARK_AQUA + "Reload Configs");
            ArrayList<String> reload_lore = new ArrayList<>();
            reload_lore.add(ChatColor.WHITE + "Do you want to reload the plugin configs?");
            reload_lore.add(ChatColor.GREEN + "Just click me, it's that easy.");
            reloadMeta.setLore(reload_lore);
            reload.setItemMeta(reloadMeta);

            ItemStack[] menu_items = {distances, settings, messageColors, reload};
            gui.setContents(menu_items);

            player.openInventory(gui);
        }
        return true;
    }
}
