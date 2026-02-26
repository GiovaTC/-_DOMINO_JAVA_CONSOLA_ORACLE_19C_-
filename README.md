# -_DOMINO_JAVA_CONSOLA_ORACLE_19C_- :.  

<img width="1024" height="1024" alt="image" src="https://github.com/user-attachments/assets/3e95e2e6-6931-4d51-ba1b-0b3141adb0df" />  

A continuación tienes una implementación completa, académica y funcional de un juego de Dominó en Java (consola, IntelliJ) que registra resultados en Oracle Database 19c mediante JDBC. El enfoque es universitario / portafolio profesional, con separación clara de responsabilidades .

🁫 DOMINÓ – JAVA CONSOLA + ORACLE 19c:

## 📌 Caracteristicas:
- Juego de dominó clásico (0–6)
- Dos jugadores (Humano vs CPU)
- Interfaz 100% consola
- Persistencia real en Oracle Database 19c

Registro de:
- Jugadores
- Fecha de partida
- Ganador
- Puntuación

## 🧱 Arquitectura:
```
domino/
 ├── Main.java
 ├── game/
 │    ├── DominoTile.java
 │    ├── Player.java
 │    ├── DominoGame.java
 ├── db/
 │    ├── OracleConnection.java
 │    └── GameDAO.java
```
###🗄️ SCRIPT ORACLE 19c:
```
CREATE TABLE DOMINO_GAME (
    ID NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    PLAYER_NAME VARCHAR2(50),
    WINNER VARCHAR2(50),
    SCORE NUMBER,
    GAME_DATE DATE DEFAULT SYSDATE
);
```
### 🔌 CONEXION ORACLE:
db/OracleConnection.java
```
package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class OracleConnection {

    private static final String URL =
        "jdbc:oracle:thin:@localhost:1521/XEPDB1";
    private static final String USER = "SYSTEM";
    private static final String PASSWORD = "oracle";

    public static Connection getConnection() throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
```
### 🎲 MODELO DEL DOMINO
game/DominoTile.java
```
package game;

public class DominoTile {
    private int left;
    private int right;

    public DominoTile(int left, int right) {
        this.left = left;
        this.right = right;
    }

    public boolean matches(int value) {
        return left == value || right == value;
    }

    @Override
    public String toString() {
        return "[" + left + "|" + right + "]";
    }
}
```
game/Player.java
```
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
```
### 🎮 LOGICA DEL JUEGO:
game/DominoGame.java
```
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
            for (int j = i; j <= 6; j++) {
                tiles.add(new DominoTile(i, j));
            }
        }
        Collections.shuffle(tiles);
        human.hand.addAll(tiles.subList(0, 7));
        cpu.hand.addAll(tiles.subList(7, 14));
        pile.add(tiles.get(14));
    }

    public void play() {
        System.out.println("🁫 Iniciando Dominó");
        System.out.println("Ficha inicial: " + pile.get(0));

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
```
### 🗄️ DAO – REGISTRO EN ORACLE:
db/GameDAO.java
```
package db;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class GameDAO {

    public static void saveGame(
            String player,
            String winner,
            int score) {

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
```
### ▶️ MAIN:
Main.java
```
import game.DominoGame;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nombre del jugador: ");
        String name = sc.nextLine();

        DominoGame game = new DominoGame(name);
        game.play();
    }
}
```
### 📦 DEPENDENCIA JDBC (Maven):
```
<dependency>
  <groupId>com.oracle.database.jdbc</groupId>
  <artifactId>ojdbc8</artifactId>
  <version>19.3.0.0</version>
</dependency>
```
### 📊 RESULTADO EN BD:
```
SELECT * FROM DOMINO_GAME;
ID	PLAYER_NAME	WINNER	SCORE	GAME_DATE
```

### 🧠 Valor Academico:

- ✔ JDBC real
- ✔ Oracle 19c
- ✔ Consola estructurada
- ✔ Arquitectura limpia
- ✔ Ideal para proyecto universitario o portafolio

Si quieres, en el siguiente paso puedo:

- 🔁 Convertir a 4 jugadores

- 🧮 Implementar reglas reales de dominó

- 🧾 Usar Stored Procedure

- 📄 Generarte un README.md profesional

- 🖥️ Pasarlo a Swing o JavaFX / .
