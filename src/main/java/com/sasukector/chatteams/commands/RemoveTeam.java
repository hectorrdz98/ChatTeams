package com.sasukector.chatteams.commands;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;

public class RemoveTeam implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            for (Team team : player.getScoreboard().getTeams()) {
                team.removeEntry(player.getName());
                Bukkit.broadcast(Component.text(player.getName() + " left team " + team.getName()));
            }
        }
        return true;
    }

}
