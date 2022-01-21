package ttt.logic;

import ttt.logic.field.LargeField;
import ttt.logic.field.Sign;
import ttt.logic.field.SmallField;
import ttt.logic.field.Tile;

import java.util.LinkedHashMap;

public class Board {
    private final LinkedHashMap<Tile, LargeField> fields = new LinkedHashMap<>();
    private boolean solved = false;
    private Sign winner = null;

    public Board() {
        for (Tile outer : Tile.values()) {
            LargeField largeField = new LargeField(outer);
            for (Tile inner: Tile.values()) {
                SmallField smallField = new SmallField(inner, largeField);
                largeField.addSubfield(smallField);
            }
            this.fields.put(largeField.getTile(), largeField);
        }
    }

    public void place(Tile outer, Tile inner, Sign sign) {
        this.fields.get(outer).getSubfield(inner).setSign(sign);
        this.fields.get(outer).trySolve();
    }

    private void trySolve() {
        if (fields.get(Tile.NW).getSign() == fields.get(Tile.N).getSign()
                && fields.get(Tile.N).getSign() == fields.get(Tile.NE).getSign()) {
            this.winner = fields.get(Tile.NW).getSign();
            this.solved = true;
            return;
        }
        if (fields.get(Tile.W).getSign() == fields.get(Tile.C).getSign()
                && fields.get(Tile.C).getSign() == fields.get(Tile.E).getSign()) {
            this.winner = fields.get(Tile.W).getSign();
            this.solved = true;
            return;
        }
        if (fields.get(Tile.SW).getSign() == fields.get(Tile.S).getSign()
                && fields.get(Tile.S).getSign() == fields.get(Tile.SE).getSign()) {
            this.winner = fields.get(Tile.SW).getSign();
            this.solved = true;
            return;
        }
        if (fields.get(Tile.NW).getSign() == fields.get(Tile.W).getSign()
                && fields.get(Tile.W).getSign() == fields.get(Tile.SW).getSign()) {
            this.winner = fields.get(Tile.NW).getSign();
            this.solved = true;
            return;
        }
        if (fields.get(Tile.N).getSign() == fields.get(Tile.C).getSign()
                && fields.get(Tile.C).getSign() == fields.get(Tile.S).getSign()) {
            this.winner = fields.get(Tile.NW).getSign();
            this.solved = true;
            return;
        }
        if (fields.get(Tile.NE).getSign() == fields.get(Tile.E).getSign()
                && fields.get(Tile.E).getSign() == fields.get(Tile.SE).getSign()) {
            this.winner = fields.get(Tile.NE).getSign();
            this.solved = true;
            return;
        }
        if (fields.get(Tile.NW).getSign() == fields.get(Tile.C).getSign()
                && fields.get(Tile.C).getSign() == fields.get(Tile.SE).getSign()) {
            this.winner = fields.get(Tile.NW).getSign();
            this.solved = true;
            return;
        }
        if (fields.get(Tile.SW).getSign() == fields.get(Tile.C).getSign()
                && fields.get(Tile.C).getSign() == fields.get(Tile.NE).getSign()) {
            this.winner = fields.get(Tile.SW).getSign();
            this.solved = true;
        }
    }

    public boolean checkSolved() {
        return this.solved;
    }
}
