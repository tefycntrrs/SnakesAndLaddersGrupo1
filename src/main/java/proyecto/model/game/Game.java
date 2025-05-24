package proyecto.model.game;

import proyecto.controller.Controller;
import proyecto.model.board.Board;

import java.io.*;
import java.util.*;

import java.util.Scanner;

public class Game {

    //Representa el juego de "Snakes and Ladders".

    // Gestiona el número de jugadores, sus posiciones en el tablero y el ganador de la partida.

    private int[] players;          // Posición de cada jugador en el tablero
    private int numPlayers;         // Número total de jugadores en la partida
    private String[] playerNames;   // Nombres de los jugadores
    private String winner = null;   // Almacena el nombre del jugador ganador (null si aún no hay ganador)
    private ArrayList<String> winners;

    private GameState currentState;
    private int currentPlayerIndex;
    private int turnsPlayed;
    private final Scanner scanner;
    private final Board board;

    private enum GameState {
        START,
        PLAY,
        WIN,
        END;

    }

    public Game(int numPlayers, Board board, Scanner scanner) {
        this.numPlayers = numPlayers; // Inicializa el número de jugadores
        this.players = new int[numPlayers]; // Crea un array para almacenar la posición de cada jugador
        this.playerNames = new String[numPlayers]; // Crea un array para los nombres de los jugadores
        this.winners = new ArrayList<>();
        this.currentState = GameState.START;
        this.currentPlayerIndex = 0;
        this.turnsPlayed = 0;
        this.board = board;
        this.scanner = scanner;
    }

    //Configura los jugadores de la partida, asignando sus nombres y posiciones iniciales.

    public void setPlayers(Scanner scanner) {
        for (int i = 0; i < numPlayers; i++) {
            String name;
            do {
                System.out.print("Ingresa el nombre del jugador " + (i + 1) + ": ");
                name = scanner.nextLine().trim(); // Elimina espacios innecesarios
                if (name.isEmpty()) {
                    System.out.println("❌ El nombre no puede estar vacío. Intenta nuevamente.");
                }
            } while (name.isEmpty());

            playerNames[i] = name;
            players[i] = 0; // Inicializar posición del jugador
        }
    }

    public void resetPositions(){
        Arrays.fill(players, 0);
    }

    public int rollDice() {
        return (int) (Math.random() * 6) + 1;
    }

    public String[] getPlayerNames() {
        return playerNames;
    }

    public int[] getPlayers() {
        return players;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public String getWinner() {  // Obtiene el nombre del jugador ganador. returna El nombre del ganador o null si aún no hay ganador.
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
        this.winners.add(winner);
    }

    public ArrayList<String> getWinners()
    {
        return this.winners;
    }

    public int contWinner(String nombre){
        int cont = 0;
        for (int i  = 0; i < this.winners.size() ; i++) {
            if(this.winners.get(i).equals(nombre)) {
                cont++;
            }
        }

        return cont;
    }

    private void play() {

        // Mostrar de quién es el turno y esperar interacción

        String input;
        do{//Bucle do-while para preguntar otra vez al usuario en caso de que no precione Enter.
            System.out.println("Presione Enter para lanzar el dado... turno del jugador : "  +  getPlayerNames()[currentPlayerIndex]);
            input = scanner.nextLine();
        } while (!input.isEmpty());

        // Se lanza el dado y se muestra el resultado
        int diceRoll = rollDice();
        System.out.println(" \uD83C\uDFB2 -->El dado mostró: " + diceRoll);

        // Obtener la posición actual del jugador y calcular la nueva posición
        int currentPos = getPlayers()[currentPlayerIndex];
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
        int[] playersPositions = getPlayers();
        playersPositions[currentPlayerIndex] = newPos;
        turnsPlayed++;

        // Verificar condición de victoria. Suponemos que la posición máxima
        // es board.getSize() * board.getSize() - 1.
        int boardMaxPos = board.getSize() * board.getSize() - 1;
        if (newPos >= boardMaxPos) {
            currentState = GameState.WIN;
            setWinner(getPlayerNames()[currentPlayerIndex]);
        } else {
            // Cambiar al siguiente jugador (turno cíclico)
            currentPlayerIndex = (currentPlayerIndex + 1) % getNumPlayers();
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
                System.out.println("\n \uD83C\uDFC6 -->¡El jugador " + getPlayerNames()[currentPlayerIndex] + " ha ganado el juego!");
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
        this.resetPositions();
    }


}
