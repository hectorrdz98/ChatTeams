package com.sasukector.chatteams.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;

public class CheckTeams implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            for (Team team : player.getScoreboard().getTeams()) {
                if (team.hasEntry(player.getName())) {
                    Bukkit.getLogger().info("§3Joined team: " + team.getName() + " - " + team);
                } else {
                    Bukkit.getLogger().info("§3Registered team but not joined: " + team.getName() + " - " + team);
                }
            }
        }
        return true;
    }

}
