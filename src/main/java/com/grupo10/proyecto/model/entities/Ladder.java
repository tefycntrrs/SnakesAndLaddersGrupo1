package com.grupo10.proyecto.model.entities;

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