package com.grupo10.proyecto.model.entities;

/**
 * Representa una escalera en el tablero.
 * Si un jugador cae en la base de la escalera (bottom), debe moverse automáticamente a la parte superior (top).
 * -----------------------------------------------
 * Metodos para usar:
 * - getBottomPosition(),
 * . getTopPosition().
 * - isBottomPosition().
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

    De este modo sacamos: getTopPosition(); getBottomPosition().
    ]
     */
public record Ladder(int bottom, int top) {
    public Ladder {
        if (bottom >= top) {
            throw new IllegalArgumentException("La base de la escalera debe estar en una posicion menor que su parte superior.");
        }
    }
    public boolean isBottomPosition(int position){
        return position  == bottom;
    }
}