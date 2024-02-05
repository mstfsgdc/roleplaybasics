package me.c0pperx.roleplaybasics.tabcompletion;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class RpbTabCompletion implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        if(args.length == 1) {
            List<String> arguments = new ArrayList<>();
            arguments.add("reload");

            return arguments;
        }
        return null;
    }
}
