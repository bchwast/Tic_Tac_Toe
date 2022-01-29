package ttt;

import ttt.logic.field.Sign;
import ttt.logic.field.Tile;

public record Move(Tile outerTile, Tile innerTile, Sign player) {
    @Override
    public String toString() {
        return "[" + outerTile + ", " + innerTile +
                ", " + player + ']';
    }
}
