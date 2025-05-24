package proyecto.model.entities;

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
