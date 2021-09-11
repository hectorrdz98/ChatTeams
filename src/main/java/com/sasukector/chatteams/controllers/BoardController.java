package com.sasukector.chatteams.controllers;

import com.sasukector.chatteams.ChatTeams;
import com.sasukector.chatteams.helpers.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BoardController {

    private static BoardController instance = null;
    private final Map<UUID, FastBoard> boards = new HashMap<>();

    public BoardController() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(ChatTeams.getInstance(), () -> {
            updateBoards();
        }, 0L, 20L);
    }

    public static BoardController getInstance() {
        if (instance == null) {
            instance = new BoardController();
        }
        return instance;
    }

    public void newPlayerBoard(Player player) {
        FastBoard board = new FastBoard(player);
        this.boards.put(player.getUniqueId(), board);
    }

    public void removePlayerBoard(Player player) {
        FastBoard board = this.boards.remove(player.getUniqueId());
        if (board != null) {
            board.delete();
        }
    }

    public void updateBoards() {
        boards.forEach((uuid, board) -> {
            Player player = Bukkit.getPlayer(uuid);
            Team playerTeam = PlayerController.getInstance().getPlayerTeam(player);
            board.updateTitle("§5§lMy §d§lBoard");
            board.updateLines(
                "",
                "Player: §6" + player.getName(),
                "Team: " + ( playerTeam != null ? playerTeam.getName() : "§cYou don't have a team" ),
                "",
                "Online players: §3" + Bukkit.getOnlinePlayers().size()
            );
        });
    }

}
