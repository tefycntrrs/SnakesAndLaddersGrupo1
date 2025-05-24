package proyecto.model.board;


import proyecto.model.entities.Ladder;
import proyecto.model.entities.Snake;
import proyecto.model.entities.Square;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
 El tablero contiene una cuadrícula `grid` donde se ubican los jugadores y los elementos del juego:
 Serpientes (`Snake`)**: Cuando un jugador cae en la cabeza de una serpiente, desciende a la cola.
 Escaleras (`Ladder`)**: Si un jugador cae en la base de una escalera, asciende hasta la parte superior.
 Casillas (`Square`)**: Espacios genéricos del tablero que pueden tener efectos adicionales.
 La clase `Board` gestiona la inicialización del tablero, la actualización de posiciones y la impresión de la
 cuadrícula.
 */
//NOTA: Se puede aplica Handler
public class Board {
    private char[][] grid;              // La grilla vacia.
    private int size;                   // Se le va a apasr lo mismo para X e Y para que sea iguales.
    private List<Square> squares;        // Lista de casillas del tablero.
    private List<Snake> snakes;          //Lista de serpientes.
    private List<Ladder> ladders;        // Lista de escaleras.


    //Constructor
    public Board() {
        this.size = 10;
        this.squares = new ArrayList<>();
        this.snakes = new ArrayList<>();
        this.ladders = new ArrayList<>();
        this.grid = new char[size][size];

        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = '-';
            }
        }
    }

    //Obtiene el tamaño del tablero o area de juego.
    public int getSize(){
        return size;
    }

    public char[][] getGrid() {
        return grid;
    }

    public void printBoard(){
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                System.out.print(grid[i][j] + " ");
            }
            System.out.println(); // Una linea al terminar cada i para que salte la linea.
        }
    }

    public int getNewPosition(int currentPosition, int diceRoll){
        // Devuelve el calculo de la nueva posicion del jugador.
        // Calcula la nueva posicion sumando el resultado del dado a la posicion actual.
        int newPosition = currentPosition + diceRoll;
        if (newPosition >= size * size){
            newPosition = (size * size) - 1;
        }

        // Verifica si el jugador cae en una serpiente y ajusta su posicion
        for (Snake s : snakes){
            if (Objects.equals(/*s.getStartPosition()*/s.head(), newPosition)){
                newPosition = /*s.getEndPosition()*/ s.tail(); // Retrocede segun la serpiente.
            }
        }

        // Verifica si el jugador cae en una escalera y ajusta su posicion.
        for (Ladder l: ladders){
            if (Objects.equals(/*l.getBottomPosition()*/l.bottom(), newPosition)){
                newPosition = /*l.getTopPosition()*/l.top(); // Retrocede segun la serpiente.
            }
        }
        return newPosition; // Retorna la nueva posicion del jugador.
    }

    public String getPosition(int currentPosition){
        // Devuelve la posicion actual del jugador.
        // Calcula la celda del tablero en el que se encuentra el jugador.
        char content = grid[currentPosition / size][currentPosition % size];
        // Devuelve un mensaje con la posicion y el contenido de la casilla.
        return "Esta en la posicion " + currentPosition + " (Contenido: " + content + ")";
    }

    public void placeElements(int x, int y, Object element) {
        if (element instanceof Snake) {
            Snake snakeElement = (Snake) element;
            // Colocar la cabeza de la serpiente con 'S'
            grid[snakeElement.head() / size]
                    [snakeElement.head() % size] = 'S';
            // Colocar la cola de la serpiente con 's'
            grid[snakeElement.tail() / size]
                    [snakeElement.tail() % size] = 's';
            snakes.add(snakeElement);
        } else if (element instanceof Ladder) {
            Ladder ladderElement = (Ladder) element;
            // Colocar la parte inferior de la escalera con 'L'
            grid[ ladderElement.bottom() / size]
                    [ladderElement.bottom() % size] = 'L';
            // Colocar la parte superior de la escalera con 'l'
            grid[ladderElement.top() / size]
                    [ladderElement.bottom() % size] = 'l';
            ladders.add(ladderElement);
        } else if (element instanceof Square) {
            // Representar casillas genéricas con '*'
            grid[x][y] = '*';
            squares.add((Square) element);
        }
    }

    public List<Square> getSquares() {
        return squares;
    }

    public List<Snake> getSnakes() {
        return snakes;
    }

    public List<Ladder> getLadders() {
        return ladders;
    }
}
