package db;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class GameDAO {
    public static void saveGame(
            String player, String winner, int score) {

        String sql =
                "INSERT INTO DOMINO_GAME (PLAYER_NAME, WINNER, SCORE) VALUES (?, ?, ?)";

        try (Connection con = OracleConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, player);
            ps.setString(2, winner);
            ps.setInt(3, score);
            ps.executeUpdate();

            System.out.println("📁 Partida registrada en Oracle");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
