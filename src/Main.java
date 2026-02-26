import game.DominoGame;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("nombre del jugador: ");
        String name = sc.nextLine();

        DominoGame game = new DominoGame(name);
        game.play();
    }
}   