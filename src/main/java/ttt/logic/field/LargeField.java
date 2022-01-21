package ttt.logic.field;

import java.util.LinkedHashMap;

public class LargeField extends AbstractField{
    private LinkedHashMap<Tile, SmallField> subfields;

    public LargeField(Tile tile) {
        super(tile);
    }

    public void addSubfield(SmallField subfield) {
        this.subfields.put(subfield.tile, subfield);
    }

    public SmallField getSubfield(Tile inner) {
        return this.subfields.get(inner);
    }

    public void trySolve() {
        if (getSubfield(Tile.NW).sign == getSubfield(Tile.N).sign && getSubfield(Tile.N).sign == getSubfield(Tile.NE).sign) {
            this.setSign(getSubfield(Tile.NW).sign);
            return;
        }
        if (getSubfield(Tile.W).sign == getSubfield(Tile.C).sign && getSubfield(Tile.C).sign == getSubfield(Tile.E).sign) {
            this.setSign(getSubfield(Tile.W).sign);
            return;
        }
        if (getSubfield(Tile.SW).sign == getSubfield(Tile.S).sign && getSubfield(Tile.S).sign == getSubfield(Tile.SE).sign) {
            this.setSign(getSubfield(Tile.SW).sign);
            return;
        }
        if (getSubfield(Tile.NW).sign == getSubfield(Tile.W).sign && getSubfield(Tile.W).sign == getSubfield(Tile.SW).sign) {
            this.setSign(getSubfield(Tile.NW).sign);
            return;
        }
        if (getSubfield(Tile.N).sign == getSubfield(Tile.C).sign && getSubfield(Tile.C).sign == getSubfield(Tile.S).sign) {
            this.setSign(getSubfield(Tile.N).sign);
            return;
        }
        if (getSubfield(Tile.NE).sign == getSubfield(Tile.E).sign && getSubfield(Tile.E).sign == getSubfield(Tile.SE).sign) {
            this.setSign(getSubfield(Tile.NE).sign);
            return;
        }
        if (getSubfield(Tile.NW).sign == getSubfield(Tile.C).sign && getSubfield(Tile.C).sign == getSubfield(Tile.SE).sign) {
            this.setSign(getSubfield(Tile.NW).sign);
            return;
        }
        if (getSubfield(Tile.NE).sign == getSubfield(Tile.C).sign && getSubfield(Tile.C).sign == getSubfield(Tile.SW).sign) {
            this.setSign(getSubfield(Tile.NE).sign);
        }
    }
}
