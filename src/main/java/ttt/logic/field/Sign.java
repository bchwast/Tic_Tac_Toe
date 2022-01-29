package ttt.logic.field;

public enum Sign {
    X, O;

    public String getString() {
        return switch(this) {
            case X -> "X";
            case O -> "O";
        };
    }

    public Sign getNext() {
        return switch(this) {
            case X -> O;
            case O -> X;
        };
    }
}
