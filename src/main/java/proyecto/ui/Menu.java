package proyecto.ui;

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
    public MenuStatus handleInput() {

        while(true) {
            try {
                String opcion = scanner.next().substring(0,1);
                if (Integer.parseInt(opcion) <= 0 || Integer.parseInt(opcion) > 3) {
                    throw new Exception("opcion invalida");
                }

                switch (opcion) {
                    case "1" -> {
                        return MenuStatus.STARTGAME;
                    }
                    case "2" -> {
                        return MenuStatus.STATISTICS;
                    }
                    case "3" -> {
                        return MenuStatus.ENDGAME;
                    }
                }

            } catch (NumberFormatException e) {
                System.out.print("Solo se permiten numeros \n Por favor ingrese una opcion : ");
            } catch (Exception e) {
                //mensaje de errores genericos
                System.out.println(e.toString());
            }
        }
    }
    //Inicia el juego y finaliza el menú.
    private void startGame() {
        System.out.println("🎮 Iniciando el juego...");
        running = false; // Salimos del menú para continuar con el juego
    }


    //Finaliza el programa con un mensaje de despedida.
    private void exit() {
        System.out.println("Gracias por jugar. Saliendo... \uD83D\uDC4B\uD83D\uDC4B");
        running = false;
    }

    public enum MenuStatus {
        STARTGAME,
        STATISTICS,
        ENDGAME;
    }
}
