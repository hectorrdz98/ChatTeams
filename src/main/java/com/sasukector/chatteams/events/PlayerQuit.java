package com.sasukector.chatteams.events;

import com.sasukector.chatteams.controllers.BoardController;
import com.sasukector.chatteams.controllers.PlayerController;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.Team;

public class PlayerQuit implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Team team = PlayerController.getInstance().getPlayerTeam(player);
        team.removeEntry(player.getName());
        BoardController.getInstance().removePlayerBoard(player);
    }

}
