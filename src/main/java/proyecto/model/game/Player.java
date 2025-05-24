package proyecto.model.game;

// Contiene el nombre del jugador, su posición en el tablero y su estado de victoria.
public class Player {
    private String name;  // Nombre del jugador
    private int position; // Posición en el tablero
    private boolean hasWon; // Estado de victoria

    //Constructor para inicializar un jugador con su nombre

    public Player(String name) {
        this.name = name;
        this.position = 0; // Inicializa en la posición de inicio
        this.hasWon = false;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    // Mueve al jugador una cantidad de pasos.
    //  * @param steps Cantidad de espacios a mover.
    public void moveSteps(int steps) {
        this.position += steps;
    }
    //Actualiza la posición del jugador a una nueva ubicación específica.
    // * @param newPosition Nueva posición a asignar.

    public void setPosition(int newPosition) {
        this.position = newPosition;
    }

    // Verifica si el jugador ha ganado la partida.

    public boolean hasWon() {
        return hasWon;
    }

    //Establece el estado de victoria del jugador.
    public void setWinner() {
        this.hasWon = true;
    }
}
