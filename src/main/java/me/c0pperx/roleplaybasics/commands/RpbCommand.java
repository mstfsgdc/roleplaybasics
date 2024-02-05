package me.c0pperx.roleplaybasics.commands;

import me.c0pperx.roleplaybasics.RolePlayBasics;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RpbCommand implements CommandExecutor {
    private final RolePlayBasics plugin;

    public RpbCommand(RolePlayBasics plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c[RolePlayBasics]&r /rpb reload -> Reloads the plugin."));
            return true;
        }
        if(args[0].equalsIgnoreCase("reload")) {
            this.plugin.reloadConfig();
            sender.sendMessage(ChatColor.RED + "[RolePlayBasics]" + ChatColor.RESET + " Plugin successfully reloaded.");
        }
        return true;
    }
}
