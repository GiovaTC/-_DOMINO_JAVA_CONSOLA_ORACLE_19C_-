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
        List<DominoTile> tiles = new ArrayList<>();
        for (int i = 0; i <= 6; i++) {
            for ( int j = i; j <= 6; j++) {
                tiles.add(new DominoTile(i, j));
            }
        }
        Collections.shuffle(tiles);
        human.hand.addAll(tiles.subList(0, 7));
        cpu.hand.addAll(tiles.subList(7, 14));
        pile.add(tiles.get(14));
    }

    public void play() {
        System.out.println("Iniciando domino");
        System.out.println("ficha inicial: " + pile.get(0));

        Random r = new Random();
        while (!human.hand.isEmpty() && !cpu.hand.isEmpty()) {
            if (!human.hand.isEmpty()) human.hand.remove(0);
            if (!cpu.hand.isEmpty()) cpu.hand.remove(0);
        }

        Player winner = human.hand.isEmpty() ? human : cpu;
        System.out.println("🏆 Ganador: " + winner.name);

        GameDAO.saveGame(
                human.name,
                winner.name,
                winner.getScore()
        );
    }
}
