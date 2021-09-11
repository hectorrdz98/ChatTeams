package com.sasukector.chatteams.commands;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddTeam implements CommandExecutor, TabExecutor {

    private static List<String> validTeams = new ArrayList<>();
    static {
        validTeams.add("Red");
        validTeams.add("Blue");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 1) {
                String teamName = args[0];
                if (validTeams.contains(teamName)) {
                    Team team = Bukkit.getScoreboardManager().getMainScoreboard().getTeam(teamName);
                    if (team == null) {
                        team = Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam(teamName);
                        team.prefix(Component.text("[" +
                                (teamName.equals("Red") ? "§4" : "§9") +
                                teamName + "§f] "));
                    }
                    team.addEntry(player.getName());
                    Bukkit.broadcast(Component.text(player.getName() + " joined team " + teamName));
                } else {
                    player.sendMessage(ChatColor.RED + "That team doesn't exist");
                }
            } else {
                player.sendMessage(ChatColor.RED + "You need to specify a team name");
            }
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();

        if(sender instanceof Player) {
            if (args.length == 1) {
                String partialItem = args[0];
                StringUtil.copyPartialMatches(partialItem, validTeams, completions);
            }
        }

        Collections.sort(completions);
        return completions;
    }

}
