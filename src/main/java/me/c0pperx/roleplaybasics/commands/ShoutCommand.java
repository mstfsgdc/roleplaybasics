package me.c0pperx.roleplaybasics.commands;

import me.c0pperx.roleplaybasics.RolePlayBasics;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ShoutCommand implements CommandExecutor {
    private final RolePlayBasics plugin;

    public ShoutCommand(RolePlayBasics plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!sender.hasPermission("roleplaybasics.shout")) {
            String permMessage = this.plugin.getConfig().getString("permission-message");

            if(permMessage != null) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', permMessage));
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c[RolePlayBasics]&r You do not have the necessary permission to use this command."));
            }
            return true;
        }

        if(!(sender instanceof Player)) {
            String instanceMessage = this.plugin.getConfig().getString("player-instance-message");

            if(instanceMessage != null) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', instanceMessage));
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c[RolePlayBasics]&r This command can only be used by players."));
            }
            return true;
        }

        if (args.length == 0) {
            String syntaxMessage = this.plugin.getConfig().getString("syntax-message");
            if(syntaxMessage == null || syntaxMessage.length() <= 0) syntaxMessage = "&c[RolePlayBasics]&r Syntax: /%cmd% <message>";
            syntaxMessage = syntaxMessage.replace("%cmd%", "shout");

            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', syntaxMessage));
            return true;
        }

        StringBuilder messageBuilder = new StringBuilder();
        for (String arg : args) {
            messageBuilder.append(arg).append(" ");
        }
        String message = messageBuilder.toString().trim();

        boolean AutoPunctuation = this.plugin.getConfig().getBoolean("auto-punctuation");
        char lastChar = message.charAt(message.length() - 1);
        if(AutoPunctuation && lastChar != '.' && lastChar != ',' && lastChar != '!' && lastChar != '?') message += '!';

        Player player = (Player) sender;
        String playerName = player.getDisplayName();
        int messageDistance = this.plugin.getConfig().getInt("shout-chat-distance");
        if(messageDistance <= 0) messageDistance = 30;

        String shoutColor = this.plugin.getConfig().getString("shout-message-color");
        String shoutDistanceColor = this.plugin.getConfig().getString("shout-distance-message-color");

        for(Player nearbyPlayer : player.getWorld().getPlayers()) {
            if(nearbyPlayer.getLocation().distance(player.getLocation()) <= Math.round((float) messageDistance / 2)) {
                nearbyPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', shoutColor) + "[S]" + playerName + ": " + message);
            } else if(nearbyPlayer.getLocation().distance(player.getLocation()) <= messageDistance){
                nearbyPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', shoutDistanceColor) + "[S]" + playerName + ": " + message);
            }
        }
        return true;
    }
}
