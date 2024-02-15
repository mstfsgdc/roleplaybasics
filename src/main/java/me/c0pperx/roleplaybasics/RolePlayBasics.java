package me.c0pperx.roleplaybasics;

import me.c0pperx.roleplaybasics.commands.*;
import me.c0pperx.roleplaybasics.events.ChatEvent;
import me.c0pperx.roleplaybasics.events.InvClickEvent;
import me.c0pperx.roleplaybasics.tabcompletion.RpbTabCompletion;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class RolePlayBasics extends JavaPlugin {

    @Override
    public void onEnable() {
        // Enabling Log
        getLogger().log(Level.INFO, "Role Play Basics v" + this.getDescription().getVersion() + " is now enabled.");

        // Configs
        saveDefaultConfig();

        // Commands
        getCommand("me").setExecutor(new MeCommand(this));
        getCommand("do").setExecutor(new DoCommand(this));
        getCommand("roleplaybasics").setExecutor(new RpbCommand(this));
        getCommand("local").setExecutor(new LocalCommand(this));
        getCommand("shout").setExecutor(new ShoutCommand(this));
        getCommand("whisper").setExecutor(new WhisperCommand(this));
        getCommand("localooc").setExecutor(new LocalOocCommand(this));
        getCommand("ooc").setExecutor(new OocCommand(this));
        getCommand("staffchat").setExecutor(new StaffChatCommand(this));

        // Tab Completions
        getCommand("roleplaybasics").setTabCompleter(new RpbTabCompletion());

        // Events
        getServer().getPluginManager().registerEvents(new ChatEvent(this), this);
        getServer().getPluginManager().registerEvents(new InvClickEvent(this), this);
    }

    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "Role Play Basics v" + this.getDescription().getVersion() + " is now disabled.");
    }
}
