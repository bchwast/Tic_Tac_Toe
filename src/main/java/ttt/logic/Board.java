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
    private boolean drawn = false;

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
        trySolve();
    }

    private void trySolve() {
        if (fields.get(Tile.NW).getSign() != null && fields.get(Tile.NW).getSign() == fields.get(Tile.N).getSign()
                && fields.get(Tile.N).getSign() == fields.get(Tile.NE).getSign()) {
            this.winner = fields.get(Tile.NW).getSign();
            this.solved = true;
            return;
        }
        if (fields.get(Tile.W).getSign() != null && fields.get(Tile.W).getSign() == fields.get(Tile.C).getSign()
                && fields.get(Tile.C).getSign() == fields.get(Tile.E).getSign()) {
            this.winner = fields.get(Tile.W).getSign();
            this.solved = true;
            return;
        }
        if (fields.get(Tile.SW).getSign() != null && fields.get(Tile.SW).getSign() == fields.get(Tile.S).getSign()
                && fields.get(Tile.S).getSign() == fields.get(Tile.SE).getSign()) {
            this.winner = fields.get(Tile.SW).getSign();
            this.solved = true;
            return;
        }
        if (fields.get(Tile.NW).getSign() != null && fields.get(Tile.NW).getSign() == fields.get(Tile.W).getSign()
                && fields.get(Tile.W).getSign() == fields.get(Tile.SW).getSign()) {
            this.winner = fields.get(Tile.NW).getSign();
            this.solved = true;
            return;
        }
        if (fields.get(Tile.N).getSign() != null && fields.get(Tile.N).getSign() == fields.get(Tile.C).getSign()
                && fields.get(Tile.C).getSign() == fields.get(Tile.S).getSign()) {
            this.winner = fields.get(Tile.NW).getSign();
            this.solved = true;
            return;
        }
        if (fields.get(Tile.NE).getSign() != null && fields.get(Tile.NE).getSign() == fields.get(Tile.E).getSign()
                && fields.get(Tile.E).getSign() == fields.get(Tile.SE).getSign()) {
            this.winner = fields.get(Tile.NE).getSign();
            this.solved = true;
            return;
        }
        if (fields.get(Tile.NW).getSign() != null && fields.get(Tile.NW).getSign() == fields.get(Tile.C).getSign()
                && fields.get(Tile.C).getSign() == fields.get(Tile.SE).getSign()) {
            this.winner = fields.get(Tile.NW).getSign();
            this.solved = true;
            return;
        }
        if (fields.get(Tile.SW).getSign() != null && fields.get(Tile.SW).getSign() == fields.get(Tile.C).getSign()
                && fields.get(Tile.C).getSign() == fields.get(Tile.NE).getSign()) {
            this.winner = fields.get(Tile.SW).getSign();
            this.solved = true;
            return;
        }
        tryDraw();
    }

    private void tryDraw() {
        for (LargeField field : this.fields.values()) {
            if (!field.checkDraw() && !field.checkSolved()) {
                return;
            }
        }
        this.drawn = true;
    }

    public boolean checkSolved() {
        return this.solved;
    }

    public boolean checkSolvedField(Tile tile) {
        return this.fields.get(tile).checkSolved();
    }

    public boolean checkSelected(Tile tile) {
        return this.fields.get(tile).checkSelect();
    }

    public void select(Tile tile) {
        this.fields.get(tile).select();
    }

    public void unSelect(Tile tile) {
        this.fields.get(tile).unSelect();
    }

    public boolean checkDraw() {
        return this.drawn;
    }

    public boolean checkDrawnField(Tile tile) {
        return this.fields.get(tile).checkDraw();
    }

    public boolean canPlace(Tile outerTile, Tile innerTile) {
        return this.fields.get(outerTile).canPlace(innerTile);
    }

}
