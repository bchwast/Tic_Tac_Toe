package ttt.logic.field;

public enum Tile {
    NW, N, NE, E, SE, S, SW, W, C;

    public int getIndex() {
        return switch(this) {
            case NW -> 0;
            case N -> 1;
            case NE -> 2;
            case E -> 3;
            case SE -> 4;
            case S -> 5;
            case SW -> 6;
            case W -> 7;
            case C -> 8;
        };
    }
}
