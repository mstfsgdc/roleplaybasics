package me.c0pperx.roleplaybasics.commands;

import me.c0pperx.roleplaybasics.RolePlayBasics;
import me.c0pperx.roleplaybasics.functions.CommandHelper;
import me.c0pperx.roleplaybasics.utilities.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffChatCommand implements CommandExecutor {
    private final RolePlayBasics plugin;

    public StaffChatCommand(RolePlayBasics plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!sender.hasPermission("roleplaybasics.staffchat")) {
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
            syntaxMessage = syntaxMessage.replace("%cmd%", "staffchat");

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

        String staffChatColor = this.plugin.getConfig().getString("staff-chat-message-color");


        for(Player staff : Bukkit.getOnlinePlayers()) {
            if(staff.hasPermission("roleplaybasics.staffchat")) {
                String color = ChatColor.translateAlternateColorCodes('&', staffChatColor != null ? staffChatColor : "&b");
                staff.sendMessage(color + "[SC]" + playerName + ": " + color + ChatColor.translateAlternateColorCodes('&', message));
            }
        }
        return true;
    }
}
