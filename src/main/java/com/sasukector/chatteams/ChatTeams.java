package com.sasukector.chatteams;

import com.sasukector.chatteams.commands.AddTeam;
import com.sasukector.chatteams.commands.CheckTeams;
import com.sasukector.chatteams.commands.RemoveTeam;
import com.sasukector.chatteams.events.PlayerJoin;
import com.sasukector.chatteams.events.PlayerQuit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class ChatTeams extends JavaPlugin {

    private static ChatTeams instance = null;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info(ChatColor.DARK_PURPLE + "ChatTeams startup!");
        instance = this;

        // Register events
        this.getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerQuit(), this);

        // Register commands
        Objects.requireNonNull(ChatTeams.getInstance().getCommand("checkTeams")).setExecutor(new CheckTeams());
        Objects.requireNonNull(ChatTeams.getInstance().getCommand("addTeam")).setExecutor(new AddTeam());
        Objects.requireNonNull(ChatTeams.getInstance().getCommand("removeTeam")).setExecutor(new RemoveTeam());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info(ChatColor.DARK_PURPLE + "ChatTeams shutdown!");
    }

    public static ChatTeams getInstance() {
        return instance;
    }
}
