package me.c0pperx.roleplaybasics.commands;

import me.c0pperx.roleplaybasics.RolePlayBasics;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LocalCommand implements CommandExecutor {
    private final RolePlayBasics plugin;

    public LocalCommand(RolePlayBasics plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!sender.hasPermission("roleplaybasics.local")) {
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
            syntaxMessage = syntaxMessage.replace("%cmd%", "local");

            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', syntaxMessage));
            return true;
        }

        StringBuilder messageBuilder = new StringBuilder();
        for (String arg : args) {
            messageBuilder.append(arg).append(" ");
        }
        String message = messageBuilder.toString().trim();

        if(message.charAt(message.length() -1) != '.') message += '.';

        Player player = (Player) sender;
        String playerName = player.getDisplayName();
        int messageDistance = this.plugin.getConfig().getInt("chat-distance");
        if(messageDistance <= 0) messageDistance = 30;

        String localColor = this.plugin.getConfig().getString("local-message-color");
        String localDistanceColor = this.plugin.getConfig().getString("local-distance-message-color");

        for(Player nearbyPlayer : player.getWorld().getPlayers()) {
            if(nearbyPlayer.getLocation().distance(player.getLocation()) <= Math.round((float) messageDistance / 2)) {
                nearbyPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', localColor) + "[L]" + playerName + ": " + message);
            } else if(nearbyPlayer.getLocation().distance(player.getLocation()) <= messageDistance){
                nearbyPlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', localDistanceColor) + "[L]" + playerName + ": " + message);
            }
        }
        return true;
    }
}