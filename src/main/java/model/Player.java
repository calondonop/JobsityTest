package model;

import java.util.HashMap;
import java.util.Map;

public class Player {
    private String name;
    private Map<Integer, Turn> turns;

    public Player(String name, int frame, Turn turn) {
        this.name = name;
        this.turns = new HashMap<>();
        this.turns.put(frame, turn);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Integer, Turn> getTurns() {
        return turns;
    }

    public void setTurns(Map<Integer, Turn> turns) {
        this.turns = turns;
    }
}

