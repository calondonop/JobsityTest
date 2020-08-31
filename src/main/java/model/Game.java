package model;

import java.util.HashMap;
import java.util.Map;

public class Game {
    private Map<String, Player> players;
    private static Game game;

    private Game(Map<String, Player> players){
        this.players = players;
    }

    public static Game getInstance(){
        if(game == null){
            game = new Game(new HashMap<>());
        }
        return game;
    }

    public Map<String, Player> getPlayers() {
        return players;
    }

    public void setPlayers(Map<String, Player> players) {
        this.players = players;
    }
}
