package com.grupo10.proyecto.model.game;

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

    public Game(int numPlayers) {
        this.numPlayers = numPlayers; // Inicializa el número de jugadores
        this.players = new int[numPlayers]; // Crea un array para almacenar la posición de cada jugador
        this.playerNames = new String[numPlayers]; // Crea un array para los nombres de los jugadores
        this.winners = new ArrayList<>();
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
}
