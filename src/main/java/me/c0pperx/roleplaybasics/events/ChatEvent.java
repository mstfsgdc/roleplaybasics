package me.c0pperx.roleplaybasics.events;

import me.c0pperx.roleplaybasics.RolePlayBasics;
import me.c0pperx.roleplaybasics.utilities.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {
    private final RolePlayBasics plugin;

    public ChatEvent(RolePlayBasics plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if(plugin.getConfig().getBoolean("local-chat-mode")) {
            Player player = event.getPlayer();
            String playerName = StringUtils.getRolePlayName(player.getDisplayName());
            String message = event.getMessage();

            boolean AutoPunctuation = this.plugin.getConfig().getBoolean("auto-punctuation");
            char lastChar = message.charAt(message.length() - 1);
            if(AutoPunctuation && lastChar != '.' && lastChar != ',' && lastChar != '!' && lastChar != '?') message += '.';

            int messageDistance = this.plugin.getConfig().getInt("local-chat-distance");
            if(messageDistance <= 0) messageDistance = 15;

            String localColor = this.plugin.getConfig().getString("local-message-color");
            String localDistanceColor = this.plugin.getConfig().getString("local-distance-message-color");


            for(Player nearbyPlayer : player.getWorld().getPlayers()) { // TODO: Message color fix
                if(nearbyPlayer.getLocation().distance(player.getLocation()) <= Math.round((float) messageDistance / 2)) {
                    String color = ChatColor.translateAlternateColorCodes('&', localColor != null ? localColor : "&f");
                    nearbyPlayer.sendMessage(color  + playerName + ": " + color + message);
                } else if(nearbyPlayer.getLocation().distance(player.getLocation()) <= messageDistance){
                    String distanceColor = ChatColor.translateAlternateColorCodes('&', localDistanceColor != null ? localDistanceColor : "&7");
                    nearbyPlayer.sendMessage(distanceColor + playerName + ": "+ distanceColor + message);
                }
            }
            event.setCancelled(true);
        }
    }

}
