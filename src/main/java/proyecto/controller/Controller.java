package proyecto.controller;

import proyecto.model.board.Board;
import proyecto.model.entities.RecordPlayer;
import proyecto.model.game.Game;
import proyecto.ui.Menu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/*
 Esta clase gestiona el flujo de la partida, incluyendo:
 Manejo de estados del juego** (`GameState`): inicio, juego en curso, victoria y finalización.
 Turnos de los jugadores**: mantiene el índice del jugador actual y alterna entre ellos.
 Lanzamiento de dados y actualización de posiciones**: ejecuta el movimiento del jugador en el tablero.
 Validación de eventos** (serpientes y escaleras): ajusta la posición del jugador según los elementos del tablero.
 Detección de condición de victoria**: determina cuándo un jugador ha llegado a la meta y finaliza la partida.
 */

public class Controller {

    private final Game game;
    private final Menu menu;

    // Constructor: inicializa los valores por defecto y el estado del juego.
    public Controller(Game game, Board board , Menu menu) {
        this.game = game;
        this.menu = menu;
        game.setPlayers();
    }

    public void runGame() {
        Menu.MenuStatus opcion = Menu.MenuStatus.STARTGAME;

        while ( opcion != Menu.MenuStatus.ENDGAME ) {

            menu.displayMenu();

            opcion = menu.handleInput();

            if (opcion == Menu.MenuStatus.STARTGAME) {
                while (!game.isGameOver()) {
                    game.updateGameState();
                }
                game.resetGame();
            }

            if (opcion == Menu.MenuStatus.STATISTICS) {
                System.out.println("\n Aca se va a mostrar la tabla de puntajes");
                showWinners();
            }

            if (opcion == Menu.MenuStatus.ENDGAME) {
                System.out.println("se termino el juego");
            }
        }
    }

    public void showWinners() {

        String[] players = game.getPlayerNames();
        ArrayList<RecordPlayer> tabla = new ArrayList<RecordPlayer>();

        for (String player : players) {
            int puntaje = game.contWinner(player);
            RecordPlayer record = new RecordPlayer(player, puntaje);
            tabla.add(record);
        }

        tabla.sort(new Comparator<RecordPlayer>() {
            @Override
            public int compare(RecordPlayer player1, RecordPlayer player2) {
                return Integer.compare(player2.puntaje(), player1.puntaje());
            }
        });

        System.out.println("----------------------------");
        for(RecordPlayer recordPlayer: tabla) {
            System.out.println("Player :" + recordPlayer.nombre() + "  Puntaje :" + recordPlayer.puntaje());
        }

    }

}

