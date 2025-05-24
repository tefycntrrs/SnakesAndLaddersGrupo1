package proyecto;


import proyecto.controller.Controller;
import proyecto.model.board.Board;
import proyecto.model.entities.Ladder;
import proyecto.model.entities.Snake;
import proyecto.model.entities.Square;
import proyecto.model.game.Game;
import proyecto.ui.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void simulation() {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();


        // Se añaden serpientes y escaleras
        Snake snake1 = new Snake(17, 5);
        Ladder ladder1 = new Ladder(3, 15);
        Square square1 = new Square(8, 8);

        Snake snake2 = new Snake(76, 63);

        board.placeElements(2, 3, snake1);
        board.placeElements(5, 5, ladder1);
        board.placeElements(8, 8, square1);

        board.placeElements(6, 3, snake2);

        // Simulación de interacciones individuales:
        System.out.println("\n🧩 Colocando elementos...");
         snake1 = new Snake(17, 5);
         snake2 = new Snake(76, 63);
         ladder1 = new Ladder(3, 15);
         square1 = new Square(8, 8);

        board.placeElements(2, 3, snake1);    // Posición ficticia (2,3) representa la serpiente 17 -> 5
        board.placeElements(5, 5, ladder1);   // Escalera 3 -> 15
        board.placeElements(6, 3, snake2);    // Segunda serpiente 76 -> 63
        board.placeElements(8, 8, square1);   // Casilla especial

        System.out.println("🧩 TABLERO CON ELEMENTOS:");
        board.printBoard();

        // Simulación simple de interacciones con elementos
        System.out.println("\n🧪 Simulación de interacciones individuales:");

        int posEscalera = 3;
        System.out.println("\nEl jugador está en la posición: " + posEscalera + " ✅");
        if (ladder1.isBottomPosition(posEscalera)) {
            posEscalera = ladder1.top();
            System.out.println("⬆ ¡Sube por la escalera hasta la posición " + posEscalera + "! ✅");
        }

        int posSerpiente = 17;
        System.out.println("\nEl jugador está en la posición: " + posSerpiente + " ✅");
        if (snake1.isHeadPosition(posSerpiente)) {
            posSerpiente = snake1.tail();
            System.out.println("⬇ ¡Cae por la serpiente hasta la posición " + posSerpiente + "! ✅");
        }

        System.out.println("\nVerificando casilla especial en (8,8): ✅");
        square1.verifyOperation(8);

        System.out.println("\n✅✅ Fin de simulación de interacciones individuales.\n");

    }


    public static void main(String[] args) {
        Main.simulation();

        Scanner scanner = new Scanner(System.in);
        Board board = new Board();
        Menu menu = new Menu(scanner);
        Game game = new Game(4, board, scanner);
        Controller controller = new Controller(game,board,menu);
        controller.runGame();
        scanner.close();
    }
}