package ttt.logic.field;

public abstract class AbstractField {
    protected Tile tile;
    protected Sign sign = null;
    protected boolean selected = false;
    protected boolean solved = false;
    protected boolean drawn = false;

    public AbstractField(Tile tile) {
        this.tile = tile;
    }

    public void setSign(Sign sign) {
        this.sign = sign;
        this.solved = true;
    }

    public boolean checkSelect() {
        return this.selected;
    }

    public void select(){
        this.selected = true;
    }

    public void unSelect() {
        this.selected = false;
    }

    public Tile getTile() {
        return this.tile;
    }

    public Sign getSign() {
        return this.sign;
    }

    public boolean checkSolved() {
        return this.solved;
    }
}
