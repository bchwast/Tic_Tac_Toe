package ttt.gui;

import javafx.application.Application;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import ttt.logic.Board;
import ttt.logic.field.Tile;

import java.util.LinkedHashMap;
import java.util.Map;

public class App extends Application {
    private GridPane boardGrid = new GridPane();
    private LinkedHashMap<Tile, GridPane> fieldGrids = new LinkedHashMap<>();
    private Board board = new Board();
    private int tileSize = 50;

    @Override
    public void init(){
        for (Tile tile : Tile.values()) {
            GridPane grid = new GridPane();
            createGrid(grid, 1);
            this.fieldGrids.put(tile, new GridPane());
        }

    }

    private void createGrid(GridPane grid, int mult) {
        grid.setGridLinesVisible(true);

        for (int i = 0; i < 3; i++) {
            grid.getColumnConstraints().add(new ColumnConstraints(mult * this.tileSize));
            grid.getRowConstraints().add(new RowConstraints(mult * this.tileSize));
        }
    }

    @Override
    public void start(Stage primaryStage) {

    }
}
