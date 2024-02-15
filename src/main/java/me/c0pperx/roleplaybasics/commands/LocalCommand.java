package me.c0pperx.roleplaybasics.commands;

import me.c0pperx.roleplaybasics.RolePlayBasics;
import me.c0pperx.roleplaybasics.functions.CommandHelper;
import me.c0pperx.roleplaybasics.utilities.StringUtils;
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

        if(CommandHelper.PlayerCheck(sender, this.plugin)) return true;

        if (args.length == 0) {
            String syntaxMessage = this.plugin.getConfig().getString("syntax-message");
            if(syntaxMessage == null || syntaxMessage.isEmpty()) syntaxMessage = "&c[RolePlayBasics]&r Syntax: /%cmd% <message>";
            syntaxMessage = syntaxMessage.replace("%cmd%", "local");

            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', syntaxMessage));
            return true;
        }

        StringBuilder messageBuilder = new StringBuilder();
        for (String arg : args) {
            messageBuilder.append(arg).append(" ");
        }
        String message = messageBuilder.toString().trim();

        boolean textFormatting = this.plugin.getConfig().getBoolean("text-formatting");
        if(textFormatting) message = StringUtils.textFormat(message, ".");

        Player player = (Player) sender;
        String playerName = StringUtils.getRolePlayName(player.getDisplayName());
        int messageDistance = this.plugin.getConfig().getInt("local-chat-distance");
        if(messageDistance <= 0) messageDistance = 15;

        String localColor = this.plugin.getConfig().getString("local-message-color");
        String localDistanceColor = this.plugin.getConfig().getString("local-distance-message-color");


        for(Player nearbyPlayer : player.getWorld().getPlayers()) {
            if(nearbyPlayer.getLocation().distance(player.getLocation()) <= Math.round((float) messageDistance / 2)) {
                String color = ChatColor.translateAlternateColorCodes('&', localColor != null ? localColor : "&f");
                nearbyPlayer.sendMessage(color  + "[L]" + playerName + ": " + color + message);
            } else if(nearbyPlayer.getLocation().distance(player.getLocation()) <= messageDistance){
                String distanceColor = ChatColor.translateAlternateColorCodes('&', localDistanceColor != null ? localDistanceColor : "&7");
                nearbyPlayer.sendMessage(distanceColor + "[L]" + playerName + ": "+ distanceColor + message);
            }
        }
        return true;
    }
}
