package com.grupo10.proyecto.model.bard;


import com.grupo10.proyecto.model.entities.Ladder;
import com.grupo10.proyecto.model.entities.Snake;
import com.grupo10.proyecto.model.entities.Square;

import java.util.List;
import java.util.Objects;

/**
 * Representa el tablero de juego de "Snakes and Ladders".
 *
 * El tablero contiene una cuadrícula `grid` donde se ubican los jugadores y los elementos del juego:
 * - **Serpientes (`Snake`)**: Cuando un jugador cae en la cabeza de una serpiente, desciende a la cola.
 * - **Escaleras (`Ladder`)**: Si un jugador cae en la base de una escalera, asciende hasta la parte superior.
 * - **Casillas (`Square`)**: Espacios genéricos del tablero que pueden tener efectos adicionales.
 *
 * La clase `Board` gestiona la inicialización del tablero, la actualización de posiciones y la impresión de la
 * cuadrícula.
 */
//NOTA: Se puede aplica Handler
public class Board {
    private char[][] grid;              // La grilla vacia.
    private int size;                   // Se le va a apasr lo mismo para X e Y para que sea iguales.
    private List<Square> square;        // Lista de casillas del tablero.
    private List<Snake> snake;          //Lista de serpientes.
    private List<Ladder> ladder;        // Lista de escaleras.

    //Constructor
    public Board(int size, List<Square> square, List<Snake> snake, List<Ladder> ladder) {
        this.size = size;
        this.grid = new char[size][size]; // La representacion de la tabla.
        initializeBoard();

        //lista de square, snake, ladder
        this.square = square;
        this.snake = snake;
        this.ladder = ladder;
    }

    private void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = '-';
            }
        }
    }

    /**
     * Obtiene el tamaño del tablero o area de juego.
     * @return El tamaño definido en la variable 'size'.
     */
    public int getSize(){
        return size;
    }

    public char[][] getGrid() {
        return grid;
    }

    /**
     * Imprime el tablero en la consola, mostrando su contenido en una estructura de cuadrilla.
     * Cada fila se imprime en una nueva linea para mantener el formato correcto.
     */
    public void printBoard(){
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                System.out.print(grid[i][j] + " ");
            }
            System.out.println(); // Una linea al terminar cada i para que salte la linea.
        }
    }

    /**
     * Calcula la nueva posición del jugador después de lanzar el dado y verifica si cae en una serpiente o escalera.
     *
     * @param currentPosition La posición actual del jugador en el tablero.
     * @param diceRoll El número obtenido en el lanzamiento del dado.
     * @return La nueva posición del jugador después de aplicar las reglas del juego.
     */
    // NOTA: [Ahora que se usa Recods, ya no se pueden usar los metodos eliminados.
    //        hay que remplazar las llamadas a los metodos que ya no existen por los
    //        accesos naturales de record.
    //        Que para Snake son:
    //          - head().
    //          - tail().
    //         Que para Ladder son:
    //          - bottom().
    //          - top().]
    public int getNewPosition(int currentPosition, int diceRoll){
        // Devuelve el calculo de la nueva posicion del jugador.
        // Calcula la nueva posicion sumando el resultado del dado a la posicion actual.
        int newPosition = currentPosition + diceRoll;
        if (newPosition >= size * size){
            newPosition = (size * size) - 1;
        }

        // Verifica si el jugador cae en una serpiente y ajusta su posicion
        for (Snake s : snake){
            if (Objects.equals(/*s.getStartPosition()*/s.head(), newPosition)){
                newPosition = /*s.getEndPosition()*/ s.tail(); // Retrocede segun la serpiente.
            }
        }

        // Verifica si el jugador cae en una escalera y ajusta su posicion.
        for (Ladder l: ladder){
            if (Objects.equals(/*l.getBottomPosition()*/l.bottom(), newPosition)){
                newPosition = /*l.getTopPosition()*/l.top(); // Retrocede segun la serpiente.
            }
        }
        return newPosition; // Retorna la nueva posicion del jugador.
    }


    /**
     * Obtiene la posición actual del jugador en el tablero y muestra su contenido.
     *
     * @param currentPosition La posición actual del jugador en el tablero.
     * @return Una cadena con la posición del jugador y el contenido de esa casilla.
     */
    public String getPosition(int currentPosition){
        // Devuelve la posicion actual del jugador.
        // Calcula la celda del tablero en el que se encuentra el jugador.
        char content = grid[currentPosition / size][currentPosition % size];

        // Devuelve un mensaje con la posicion y el contenido de la casilla.
        return "Esta en la posicion " + currentPosition + " (Contenido: " + content + ")";
    }

    /**
     * Coloca elementos en el tablero según su tipo (Serpiente, Escalera o Casilla genérica).
     *
     * @param x Coordenada X en la matriz del tablero.
     * @param y Coordenada Y en la matriz del tablero.
     * @param element Objeto a colocar en la celda (puede ser Serpiente, Escalera o Casilla).
     */
    // Funcionalidad en las celdas.

    //NOTA:[Misma problematica que antes.
    //      En esteo caso:
    //          - snakeElement.getStartPosition() => snakeElement.head().
    //          - snakeElement.getEndPosition() => snakeElement.tail().
    //
    //          - ladderElement.getBottomPosition() => ladderElement.bottom().
    //          - ladderElement.getTopPosition() => ladderElement.top().]
    public void placeElements(int x, int y, Object element) {
        if (element instanceof Snake) {
            Snake snakeElement = (Snake) element;
            // Colocar la cabeza de la serpiente con 'S'
            grid[/*snakeElement.getStartPosition()*/ snakeElement.head() / size]
                [/*snakeElement.getStartPosition()*/ snakeElement.head() % size] = 'S';
            // Colocar la cola de la serpiente con 's'
            grid[/*snakeElement.getEndPosition()*/ snakeElement.tail() / size]
                [/*snakeElement.getEndPosition()*/ snakeElement.tail() % size] = 's';
            snake.add(snakeElement);
        } else if (element instanceof Ladder) {
            Ladder ladderElement = (Ladder) element;
            // Colocar la parte inferior de la escalera con 'L'
            grid[/*ladderElement.getBottomPosition()*/ ladderElement.bottom() / size]
                [/*ladderElement.getBottomPosition()*/ ladderElement.bottom() % size] = 'L';
            // Colocar la parte superior de la escalera con 'l'
            grid[/*ladderElement.getTopPosition()*/ ladderElement.top() / size]
                [/*ladderElement.getTopPosition()*/ ladderElement.bottom() % size] = 'l';
            ladder.add(ladderElement);
        } else if (element instanceof Square) {
            // Representar casillas genéricas con '*'
            grid[x][y] = '*';
            square.add((Square) element);
        }
    }
}



