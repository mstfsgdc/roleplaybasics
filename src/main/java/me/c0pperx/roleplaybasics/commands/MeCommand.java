package me.c0pperx.roleplaybasics.commands;

import me.c0pperx.roleplaybasics.RolePlayBasics;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MeCommand implements CommandExecutor {
    private final RolePlayBasics plugin;

    public MeCommand(RolePlayBasics plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!sender.hasPermission("roleplaybasics.me")) {
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
            syntaxMessage = syntaxMessage.replace("%cmd%", "me");

            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', syntaxMessage));
            return true;
        }

        StringBuilder messageBuilder = new StringBuilder();
        for (String arg : args) {
            messageBuilder.append(arg).append(" ");
        }
        String message = messageBuilder.toString().trim();

        Player player = (Player) sender;
        String playerName = ChatColor.BOLD + player.getDisplayName() + ChatColor.RESET + ChatColor.DARK_PURPLE;
        int messageDistance = this.plugin.getConfig().getInt("emote-message-distance");
        if(messageDistance <= 0) messageDistance = 30;

        for(Player nearbyPlayer : player.getWorld().getPlayers()) {
            if(nearbyPlayer.getLocation().distance(player.getLocation()) <= messageDistance) {
                nearbyPlayer.sendMessage(ChatColor.DARK_PURPLE + "* " + playerName + " " + message);
            }
        }
        return true;
    }
}
