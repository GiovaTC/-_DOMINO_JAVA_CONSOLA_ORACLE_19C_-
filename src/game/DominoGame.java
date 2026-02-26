package game;

import db.GameDAO;
import java.util.*;

public class DominoGame {

    private Player human;
    private Player cpu;
    private List<DominoTile> pile = new ArrayList<>();

    public DominoGame(String playerName) {
        human = new Player(playerName);
        cpu = new Player("CPU");    
        initialize();   
    }

    private void initialize() {
    }

}
