package com.grupo10.proyecto.ui;

import java.util.Scanner;

/**
 * Representa el menú principal del juego
 *
 * Esta clase gestiona la navegación del usuario a través de las opciones disponibles:
 * - **Iniciar Juego**: Comienza la partida.
 * - **Ver Tabla de Puntajes**: Muestra los puntajes (actualmente en construcción).
 * - **Salir**: Finaliza el programa.
 *
 * El menú está diseñado con un bucle `while` para permitir que el usuario interactúe hasta que seleccione salir.
 * Se utiliza un `Scanner` para capturar las entradas del usuario y un `switch-case` para gestionar las opciones.

 */


//NOTA: Se puede aplicar Handler
public class Menu {
    private boolean running;
    private final Scanner scanner;

    /**
     * Constructor para inicializar el menú con un objeto `Scanner`.
     *
     * @param scanner Objeto Scanner para leer la entrada del usuario.
     */
    public Menu(Scanner scanner) {
        this.running = true;
        this.scanner = scanner;
    }

    /**
     * Inicia el bucle del menú, permitiendo al usuario seleccionar opciones hasta que elija salir.
     */
    public void start() {
        while (running) {
            displayMenu();
            String input = scanner.nextLine();
            handleInput(input);
        }
    }

    /**
     * Muestra el menú en la consola con las opciones disponibles.
     */
    private void displayMenu() {
        System.out.println("\n--- Snakes \uD83D\uDC0D And Ladders \uD83E\uDE9C (But Cheap!) ---");
        System.out.println("\uD83C\uDFAE 1. Iniciar Juego");
        System.out.println("\uD83D\uDCCA 2. Ver Tabla de Puntajes");
        System.out.println("\uD83D\uDEAA 3. Salir");
        System.out.print("➡\uFE0F Selecciona una opción: ");
    }

    /**
     * Gestiona la entrada del usuario y ejecuta la acción correspondiente.
     *
     * @param input La opción ingresada por el usuario.
     */
    private void handleInput(String input) {
        switch (input) {
            case "1":
                startGame();
                break;
            case "2":
                showScoreboard();
                break;
            case "3":
                exit();
                break;
            default:
                System.out.println("( ⚠\uFE0F ) Opción no válida. Intenta de nuevo.");
        }
    }
     //Inicia el juego y finaliza el menú.

    private void startGame() {
        System.out.println("🎮 Iniciando el juego...");
        running = false; // Salimos del menú para continuar con el juego
    }

     // Muestra la tabla de puntajes (actualmente en construcción).

    private void showScoreboard() {
        System.out.println("\uD83D\uDEE0\uFE0F En construcción (Presiona Enter para regresar) \uD83D\uDEE0\uFE0F");
        scanner.nextLine(); // Espera a que el usuario presione Enter
    }

     //Finaliza el programa con un mensaje de despedida.

    private void exit() {
        System.out.println("Gracias por jugar. Saliendo... \uD83D\uDC4B\uD83D\uDC4B");
        running = false;
    }
}
