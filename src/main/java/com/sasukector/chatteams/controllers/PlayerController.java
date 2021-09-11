package com.sasukector.chatteams.controllers;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

public class PlayerController {

    private static PlayerController instance = null;

    public static PlayerController getInstance() {
        if (instance == null) {
            instance = new PlayerController();
        }
        return instance;
    }

    public Team getPlayerTeam(Player player) {
        for (Team team : player.getScoreboard().getTeams()) {
            if (team.hasEntry(player.getName())) {
                return team;
            }
        }
        return null;
    }

}
