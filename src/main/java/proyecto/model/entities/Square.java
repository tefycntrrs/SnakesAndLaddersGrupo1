package proyecto.model.entities;

public record Square(int coorX, int coorY) {

    public void verifyOperation(int playerPosition) {
        if (playerPosition == coorX) {
            System.out.println("✅ El jugador está en la casilla (" + coorX + ", " + coorY + "). Aplicar efecto.");
        } else {
            System.out.println("🔹 No hay efecto en esta casilla.");
        }
    }
}
