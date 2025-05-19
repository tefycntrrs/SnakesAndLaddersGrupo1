package com.grupo10.proyecto.model.entities;

/**
 * Representa una serpiente en el tablero del juego "Snakes and Ladders".
 *
 * Cuando un jugador cae en la cabeza de la serpiente, será movido automáticamente a la cola.
 * Las serpientes siempre deben tener una posición inicial mayor que su posición final.
 * -----------------------------------------------
 * Metodos para usar:
 * - getStartPosition().
 * - getEndPosition().
 * - isHeadPosition().
 * - getTailPosition().
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

    De este modo sacamos getEndPosition(); getTailPosition(); getStartPosition().
    ]

     */


public record Snake(int head, int tail) {
    public Snake{
        if (head <= tail){
            throw new IllegalArgumentException("La posicion de inicio debe ser mayor que la de fin para una serpiente.");
        }
    }

    public boolean isHeadPosition(int position){
        return position == head;
    }
}


