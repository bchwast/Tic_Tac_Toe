package ttt.logic.field;

public class SmallField extends AbstractField{
    private final LargeField domain;

    public SmallField(Tile tile, LargeField domain) {
        super(tile);
        this.domain = domain;
    }
}
