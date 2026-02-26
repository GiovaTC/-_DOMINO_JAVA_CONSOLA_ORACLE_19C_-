package game;

import java.util.ArrayList;
import java.util.List;

public class Player {
    public String name;
    public List<DominoTile> hand = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public int getScore() {
        return hand.size();
    }
}
