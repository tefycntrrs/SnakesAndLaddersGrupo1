package com.grupo10.proyecto.controller;

import com.grupo10.proyecto.model.bard.Board;
import com.grupo10.proyecto.model.entities.RecordPlayer;
import com.grupo10.proyecto.model.game.Game;
import com.grupo10.proyecto.ui.Menu;

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

    private Game game;
    private Board board;
    private Menu menu;

    // Definición de una enumeración para representar los estados del juego
    private enum GameState {
        START,
        PLAY,
        WIN,
        END;

    }

    private GameState currentState;
    private int currentPlayerIndex;
    private int turnsPlayed;
    private Scanner scanner;

    // Constructor: inicializa los valores por defecto y el estado del juego.
    public Controller(Game game, Board board , Menu menu) {
        this.currentState = GameState.START;
        this.currentPlayerIndex = 0;
        this.turnsPlayed = 0;
        this.scanner = new Scanner(System.in);
        this.game = game;
        this.board = board;
        this.menu = menu;

    }

    public void runGame() {
        int opcion = 0;

        while ( opcion != 3 ) {

            menu.displayMenu();

            opcion = menu.handleInput();

            if (opcion == 1) {
                currentState = GameState.START;
                while (!isGameOver()) {
                    updateGameState();
                }

                resetGame();
            }

            if (opcion == 2) {
                System.out.println("\n Aca se va a mostrar la tabla de puntajes");
                showWinners();
            }

            if (opcion == 3) {
                System.out.println("se termino el juego");
            }
        }
    }

    /*
    Actualiza el estado del juego y ejecuta la acción correspondiente según
    el estado actual, utilizando switches para determinar el flujo.
    */
    public void updateGameState() {
        switch (currentState) {
            case START:
                System.out.println("Iniciando el juego...");
                currentState = GameState.PLAY;
                break;

            case PLAY:
                this.play();
                break;

            case WIN:
                // El jugador actual ha ganado
                System.out.println("\n \uD83C\uDFC6 -->¡El jugador " + game.getPlayerNames()[currentPlayerIndex] + " ha ganado el juego!");
                currentState = GameState.END;
                break;

            case END:
                System.out.println("\n ⚔\uFE0F -->El juego ha finalizado tras " + turnsPlayed + " turnos.");
                break;

            default:
                break;
        }

    }

    public boolean isGameOver() {
        return currentState == GameState.END;
    }

    public void resetGame() { //Reinicia el estado del juego.
        this.currentState = GameState.START;
        this.currentPlayerIndex = 0;
        this.turnsPlayed = 0;
        this.game.resetPositions();
    }

    private void play() {

        // Mostrar de quién es el turno y esperar interacción

        String input;
        do{//Bucle do-while para preguntar otra vez al usuario en caso de que no precione Enter.
            System.out.println("Presione Enter para lanzar el dado... turno del jugador : "  +  game.getPlayerNames()[currentPlayerIndex]);
            input = scanner.nextLine();
        } while (!input.isEmpty());

        // Se lanza el dado y se muestra el resultado
        int diceRoll = game.rollDice();
        System.out.println(" \uD83C\uDFB2 -->El dado mostró: " + diceRoll);

        // Obtener la posición actual del jugador y calcular la nueva posición
        int currentPos = game.getPlayers()[currentPlayerIndex];
        int newPos = board.getNewPosition(currentPos, diceRoll);

        // Detectar el tipo de evento mediante comparación:
        // Asumimos que en un movimiento normal, newPos == currentPos + diceRoll.
        // Si newPos < currentPos, se activó una serpiente;
        // si newPos > currentPos + diceRoll, se activó una escalera.
        String eventType;
        if (newPos < currentPos) {
            eventType = "SNAKE";
        } else if (newPos > currentPos + diceRoll) {
            eventType = "LADDER";
        } else {
            eventType = "NORMAL";
        }

        // Segundo switch: manejo del evento específico
        switch (eventType) {
            case "SNAKE":
                System.out.println("\uD83D\uDC0D -->¡Oh no! Caíste en una serpiente y desciendes a la posición " + newPos);
                break;
            case "LADDER":
                System.out.println("\uD83E\uDE9C -->¡Bien! Subes por una escalera y avanzas hasta la \uD83C\uDFAF posición " + newPos);
                break;
            case "NORMAL":
                System.out.println("\uD83C\uDFAF Te mueves a la posición " + newPos + " \uD83C\uDFAF");
                break;
        }

        // Actualizamos la posición del jugador en Game
        int[] playersPositions = game.getPlayers();
        playersPositions[currentPlayerIndex] = newPos;
        turnsPlayed++;

        // Verificar condición de victoria. Suponemos que la posición máxima
        // es board.getSize() * board.getSize() - 1.
        int boardMaxPos = board.getSize() * board.getSize() - 1;
        if (newPos >= boardMaxPos) {
            currentState = GameState.WIN;
            game.setWinner(game.getPlayerNames()[currentPlayerIndex]);
        } else {
            // Cambiar al siguiente jugador (turno cíclico)
            currentPlayerIndex = (currentPlayerIndex + 1) % game.getNumPlayers();
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

