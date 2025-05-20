package com.grupo10.proyecto.ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.Scanner;

// Representa el menú principal del juego

//NOTA: Se puede aplicar Handler
public class Menu {
    private boolean running;
    private final Scanner scanner;

    public Menu(Scanner scanner) {
        this.running = true;
        this.scanner = scanner;
    }

    public void displayMenu() {
        System.out.println("\n--- Snakes \uD83D\uDC0D And Ladders \uD83E\uDE9C (But Cheap!) ---");
        System.out.println("\uD83C\uDFAE 1. Iniciar Juego");
        System.out.println("\uD83D\uDCCA 2. Ver Tabla de Puntajes");
        System.out.println("\uD83D\uDEAA 3. Salir");
        System.out.print("➡\uFE0F Selecciona una opción: ");
    }

    //Gestiona la entrada del usuario y ejecuta la acción correspondiente.
    public int handleInput() {
        int opcion = scanner.nextInt();
        return opcion;

    }
    //Inicia el juego y finaliza el menú.
    private void startGame() {
        System.out.println("🎮 Iniciando el juego...");
        running = false; // Salimos del menú para continuar con el juego
    }

    // Muestra la tabla de puntajes
    private void showScoreboard() {
        System.out.println("\n📊 Tabla de Puntajes:");
        System.out.println("--------------------------");

        File archivo = new File("ranking.txt");

        if (!archivo.exists()) {
            System.out.println("⚠️ Aún no hay partidas registradas.");
        } else {
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    System.out.println("🏅 " + linea);
                }
            } catch (IOException e) {
                System.out.println("Error al leer ranking.txt: " + e.getMessage());
            }
        }

        System.out.println("--------------------------");
        System.out.println("Presiona Enter para regresar al menú...");
        scanner.nextLine();

    }

    //Finaliza el programa con un mensaje de despedida.
    private void exit() {
        System.out.println("Gracias por jugar. Saliendo... \uD83D\uDC4B\uD83D\uDC4B");
        running = false;
    }
}
