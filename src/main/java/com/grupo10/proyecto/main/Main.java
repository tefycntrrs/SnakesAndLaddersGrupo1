package com.grupo10.proyecto.main;


import com.grupo10.proyecto.controller.Controller;
import com.grupo10.proyecto.model.bard.Board;
import com.grupo10.proyecto.model.entities.Ladder;
import com.grupo10.proyecto.model.entities.Snake;
import com.grupo10.proyecto.model.entities.Square;
import com.grupo10.proyecto.model.game.Game;
import com.grupo10.proyecto.ui.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Código para probar la interacción de la matriz y los datos.
        List<Square> squares = new ArrayList<>();
        List<Snake> snakes = new ArrayList<>();
        List<Ladder> ladders = new ArrayList<>();

        Board board = new Board(10, squares, snakes, ladders);

        System.out.println("TABLEROS");
        System.out.println("Empty board:");
        board.printBoard();

        // Se añaden serpientes y escaleras
        Snake snake1 = new Snake(17, 5);
        Ladder ladder1 = new Ladder(3, 15);
        Square square1 = new Square(8, 8);

        Snake snake2 = new Snake(76, 63);

        board.placeElements(2, 3, snake1);
        board.placeElements(5, 5, ladder1);
        board.placeElements(8, 8, square1);

        board.placeElements(6, 3, snake2);

        System.out.println("Full board:");
        board.printBoard();

        // Simulación de interacciones individuales:
        System.out.println("\n Simulacion de interacciones individuales: ");
        int playerPosition = 3;
        System.out.println("\nEl jugador está en la posición: " + playerPosition + "(✅)");
        if (ladder1.isBottomPosition(playerPosition)) {
            playerPosition = ladder1.top();
            System.out.println("¡El jugador subió por la escalera a la posición " + playerPosition + "!" + "(✅)");
        }

        int playerPosition1 = 17;
        System.out.println("\nEl jugador está en la posición: " + playerPosition1 + "(✅)");
        if (snake1.isHeadPosition(playerPosition1)) {
            playerPosition1 = snake1.tail();
            System.out.println("¡El jugador cayó en la serpiente y bajó a la posición " + playerPosition1 + "!" + "(✅)");
        }

        System.out.println("\nVerificando casilla en (8,8):" + "(✅)");
        square1.verifyOperation(8);
        System.out.println("-- Termmina simulacion de interacciones individuales: --" + "✅  ✅)");
        // ------ Simulación de interacciones individuales:
        System.out.println(" ");
        System.out.println(" ");
        // SIMULACIÓN COMPLETA DEL JUEGO CON EL GAMECONTROLLER
        System.out.println("-- Comienza la partida real \n Nombres de los jugadores: --");
        Game game = new Game(4);
        game.setPlayers(scanner);

        //aca comienza la partida real con los usuarios
        Menu menu = new Menu(scanner);
        Controller controller = new Controller(game,board,menu);
        System.out.println("\n-- \uD83D\uDCBB Iniciando simulación completa de la partida \uD83D\uDCBB --");

        controller.runGame();

        scanner.close();
    }
}