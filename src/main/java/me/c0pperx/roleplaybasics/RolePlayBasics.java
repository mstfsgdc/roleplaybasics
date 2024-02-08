package me.c0pperx.roleplaybasics;

import me.c0pperx.roleplaybasics.commands.*;
import me.c0pperx.roleplaybasics.tabcompletion.RpbTabCompletion;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class RolePlayBasics extends JavaPlugin {

    @Override
    public void onEnable() {
        // Enabling Log
        getLogger().log(Level.INFO, "Role Play Basics is now enabled.");

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

        // Tab Completions
        getCommand("roleplaybasics").setTabCompleter(new RpbTabCompletion());
    }

    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "Role Play Basics is now disabled.");
    }
}
