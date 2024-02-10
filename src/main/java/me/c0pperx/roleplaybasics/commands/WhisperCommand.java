package me.c0pperx.roleplaybasics.commands;

import me.c0pperx.roleplaybasics.RolePlayBasics;
import me.c0pperx.roleplaybasics.functions.CommandHelper;
import me.c0pperx.roleplaybasics.utilities.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WhisperCommand implements CommandExecutor {
    private final RolePlayBasics plugin;

    public WhisperCommand(RolePlayBasics plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!sender.hasPermission("roleplaybasics.whisper")) {
            String permMessage = this.plugin.getConfig().getString("permission-message");

            if(permMessage != null) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', permMessage));
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c[RolePlayBasics]&r You do not have the necessary permission to use this command."));
            }
            return true;
        }

        if(CommandHelper.PlayerCheck(sender, this.plugin)) return true;

        if (args.length == 0) {
            String syntaxMessage = this.plugin.getConfig().getString("syntax-message");
            if(syntaxMessage == null || syntaxMessage.isEmpty()) syntaxMessage = "&c[RolePlayBasics]&r Syntax: /%cmd% <message>";
            syntaxMessage = syntaxMessage.replace("%cmd%", "whisper");

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
        if(AutoPunctuation && lastChar != '.' && lastChar != ',' && lastChar != '!' && lastChar != '?') message += '.';

        Player player = (Player) sender;
        String playerName = StringUtils.getRolePlayName(player.getDisplayName());
        int messageDistance = this.plugin.getConfig().getInt("whisper-chat-distance");
        if(messageDistance <= 0) messageDistance = 3;

        String whisperColor = this.plugin.getConfig().getString("whisper-message-color");
        String whisperDistanceColor = this.plugin.getConfig().getString("whisper-distance-message-color");

        for(Player nearbyPlayer : player.getWorld().getPlayers()) {
            if(nearbyPlayer.getLocation().distance(player.getLocation()) <= Math.round((float) messageDistance / 2)) {
                String color = ChatColor.translateAlternateColorCodes('&', whisperColor != null ? whisperColor : "&7");
                nearbyPlayer.sendMessage(color + "[W]" + playerName + ": " + color + message);
            } else if(nearbyPlayer.getLocation().distance(player.getLocation()) <= messageDistance){
                String distanceColor = ChatColor.translateAlternateColorCodes('&', whisperDistanceColor != null ? whisperDistanceColor : "&8");
                nearbyPlayer.sendMessage( distanceColor + "[W]" + playerName + ": " + distanceColor + message);
            }
        }
        return true;
    }
}
