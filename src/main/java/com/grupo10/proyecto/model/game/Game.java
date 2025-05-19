package com.grupo10.proyecto.model.game;

import java.util.Scanner;
/**
 * Representa el juego de "Snakes and Ladders".
 *
 * Gestiona el número de jugadores, sus posiciones en el tablero y el ganador de la partida.
 * Los jugadores pueden lanzar un dado para avanzar en el tablero y sus nombres deben ingresarse correctamente
 */
public class Game {
    private int[] players;          // Posición de cada jugador en el tablero
    private int numPlayers;         // Número total de jugadores en la partida
    private String[] playerNames;   // Nombres de los jugadores
    private String winner = null;   // Almacena el nombre del jugador ganador (null si aún no hay ganador)

    /**
     * Constructor para inicializar el juego con un número específico de jugadores.
     *
     * @param numPlayers Número de jugadores en la partida.
     */
    public Game(int numPlayers) {
        this.numPlayers = numPlayers; // Inicializa el número de jugadores
        this.players = new int[numPlayers]; // Crea un array para almacenar la posición de cada jugador
        this.playerNames = new String[numPlayers]; // Crea un array para los nombres de los jugadores
    }


    /**
     * Configura los jugadores de la partida, asignando sus nombres y posiciones iniciales.
     * @param scanner Objeto Scanner para leer la entrada del usuario.
     */
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

    /**
     * Simula el lanzamiento de un dado de seis caras.
     * @return Un número aleatorio entre 1 y 6.
     */
    public int rollDice() {
        return (int) (Math.random() * 6) + 1;
    }

    /**
     * Obtiene los nombres de los jugadores.
     * @return Un array con los nombres de los jugadores.
     */
    public String[] getPlayerNames() {
        return playerNames;
    }

    /**
     * Obtiene las posiciones actuales de los jugadores en el tablero.
     * @return Un array con las posiciones de cada jugador.
     */
    public int[] getPlayers() {
        return players;
    }

    /**
     * Obtiene el número total de jugadores en la partida.
     * @return El número de jugadores.
     */
    public int getNumPlayers() {
        return numPlayers;
    }

    /**
     * Obtiene el nombre del jugador ganador.
     * @return El nombre del ganador o null si aún no hay ganador.
     */
    public String getWinner() {
        return winner;
    }

    /**
     * Establece el ganador de la partida.
     * @param winner Nombre del jugador que ha ganado.
     */
    public void setWinner(String winner) {
        this.winner = winner;
    }
}
