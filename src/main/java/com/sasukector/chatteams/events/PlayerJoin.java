package com.sasukector.chatteams.events;

import com.sasukector.chatteams.controllers.BoardController;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        BoardController.getInstance().newPlayerBoard(event.getPlayer());
    }

}
