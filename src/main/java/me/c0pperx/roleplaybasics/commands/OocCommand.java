package me.c0pperx.roleplaybasics.commands;

import me.c0pperx.roleplaybasics.RolePlayBasics;
import me.c0pperx.roleplaybasics.functions.CommandHelper;
import me.c0pperx.roleplaybasics.utilities.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OocCommand implements CommandExecutor {
    private final RolePlayBasics plugin;

    public OocCommand(RolePlayBasics plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!sender.hasPermission("roleplaybasics.ooc")) {
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
            syntaxMessage = syntaxMessage.replace("%cmd%", "ooc");

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
        World playerWorld = player.getWorld();
        String playerName = StringUtils.getRolePlayName(player.getDisplayName());

        String oocColor = this.plugin.getConfig().getString("ooc-message-color");

        for (Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()) {
            if (onlinePlayer.getWorld().equals(playerWorld)) {
                String color = ChatColor.translateAlternateColorCodes('&', oocColor != null ? oocColor : "&d");
                onlinePlayer.sendMessage(color  + "[OOC]" + playerName + ": " + color + message);
            }
        }
        return true;
    }
}
