package com.grupo10.proyecto.model.entities;

/**
 * Representa una casilla del tablero con coordenadas X e Y.
 * Puede ser utilizada para definir posiciones en el juego.
 * -----------------------------------------------
 * Metodos para usar:
 * - getCoorX().
 * - getCoorY().
 * - verifyOperation().
 */


//NOTE: Se puede aplicar records:
    /*
    [Un record es un tipo especial de clase diseñado para
    simplificar la creacion de clases que tienen intencion
    de ser inmutables y que encapsulan principalmente fatos.
    Es ideal para:
        Datos inmutables.
        Estructura simple.
    En nuestro caso, solo queremos guardar cosas, no modificarlas.
    ]
     */
public /*class*/ record Square(int coorX, int coorY) {
    //private int coorX; // Coordenada X de la casilla
    //private int coorY; // Coordenada Y de la casilla

    /**
     * Constructor para inicializar una casilla con sus coordenadas en el tablero.
     *
     * @param coorX La posición horizontal de la casilla.
     * @param coorY La posición vertical de la casilla.
     */
    //public Square(int coorX, int coorY) {
    //  this.coorX = coorX;
    //  this.coorY = coorY;
    //}

    /**
     * Obtiene la coordenada X de la casilla.
     *
     * @return La coordenada X en el tablero.
     */
    // public int getCoorX() {
    //   return coorX;
    //}

    /**
     * Obtiene la coordenada Y de la casilla.
     *
     * @return La coordenada Y en el tablero.
     */
    // public int getCoorY() {
    //    return coorY;
    //}

    /**
     * Verifica la operación en la casilla.
     */
    public void verifyOperation(int playerPosition) {
        if (playerPosition == coorX) {
            System.out.println("✅ El jugador está en la casilla (" + coorX + ", " + coorY + "). Aplicar efecto.");
        } else {
            System.out.println("🔹 No hay efecto en esta casilla.");
        }
    }
}
