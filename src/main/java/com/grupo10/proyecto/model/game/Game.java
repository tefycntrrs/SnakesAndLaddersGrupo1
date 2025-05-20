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


    public Game(int numPlayers) {
        this.numPlayers = numPlayers; // Inicializa el número de jugadores
        this.players = new int[numPlayers]; // Crea un array para almacenar la posición de cada jugador
        this.playerNames = new String[numPlayers]; // Crea un array para los nombres de los jugadores
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
    }

    public void guardarGanadorEnRanking(String nombreGanador, int turnosJugados) {
        Map<String, Integer> ranking = new HashMap<>();

        File archivo = new File("ranking.txt");
        if (archivo.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] partes = linea.split(" - ");
                    if (partes.length == 2) {
                        String nombre = partes[0].trim();
                        int victorias = Integer.parseInt(partes[1].trim());
                        ranking.put(nombre, victorias);
                    }
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Error al leer ranking.txt: " + e.getMessage());
            }
        }

        // Sumamos la victoria
        ranking.put(nombreGanador, ranking.getOrDefault(nombreGanador, 0) + 1);

        // Ordenamos de mayor a menor por cantidad de victorias
        List<Map.Entry<String, Integer>> listaOrdenada = new ArrayList<>(ranking.entrySet());
        listaOrdenada.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        // Escribimos el archivo actualizado
        try (PrintWriter writer = new PrintWriter(new FileWriter("ranking.txt"))) {
            for (Map.Entry<String, Integer> entry : listaOrdenada) {
                writer.println(entry.getKey() + " - " + entry.getValue());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar ranking.txt: " + e.getMessage());
        }
    }
}
