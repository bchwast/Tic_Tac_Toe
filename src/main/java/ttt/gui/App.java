package ttt.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ttt.Move;
import ttt.logic.Board;
import ttt.logic.field.Sign;
import ttt.logic.field.Tile;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class App extends Application {
    private final HBox main = new HBox();
    private GridPane boardGrid;
    private LinkedHashMap<Tile, GridPane> fieldGrids;
    private LinkedHashMap<Tile, Boolean> locked;
    private VBox listMoves;
    private Board board;
    private int tileSize = 50;
    private final int[][] indexes = {{0, 0}, {0, 1}, {0, 2}, {1, 2}, {2, 2}, {2, 1}, {2, 0}, {1, 0}, {1, 1}};
    private Sign currentPlayer;
    private boolean freeMove;

    @Override
    public void init(){
        this.boardGrid = new GridPane();
        this.fieldGrids = new LinkedHashMap<>();
        this.locked = new LinkedHashMap<>();
        this.board = new Board();
        this.listMoves = new VBox();
        for (Tile tile : Tile.values()) {
            GridPane grid = new GridPane();
            createGrid(grid, 1);
            this.fieldGrids.put(tile, grid);
            this.locked.put(tile, Boolean.FALSE);
        }
        createGrid(this.boardGrid, 3);
        fillBoardGrid();
        addFieldLogic();
        this.freeMove = true;
        this.currentPlayer = Sign.O;
        this.main.getChildren().add(this.boardGrid);
        ScrollPane scrollPane = new ScrollPane(this.listMoves);
        scrollPane.setMinWidth(100);
        this.main.getChildren().add(scrollPane);
    }

    private void createGrid(GridPane grid, int mult) {
        grid.setGridLinesVisible(true);
        for (int i = 0; i < 3; i++) {
            grid.getColumnConstraints().add(new ColumnConstraints(mult * this.tileSize));
            grid.getRowConstraints().add(new RowConstraints(mult * this.tileSize));
        }
    }

    private void fillBoardGrid() {
        int i = 0;
        for (GridPane field: this.fieldGrids.values()) {
            this.boardGrid.add(field, this.indexes[i][1], this.indexes[i][0], 1, 1);
            i++;
        }
    }

    private void addFieldLogic() {
        for (Tile outerTile : Tile.values()) {
            int i = 0;
            for (Tile innerTile : Tile.values()) {
                Label element = new Label();
                element.setFont(new Font(30));
                element.setAlignment(Pos.CENTER);
                element.setMinSize(this.tileSize, this.tileSize);
                element.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
                element.setOnMouseClicked((mouseEvent) -> {
                    if (this.board.canPlace(outerTile, innerTile) && (this.board.checkSelected(outerTile) || this.freeMove) &&
                            !this.board.checkSolvedField(outerTile)) {
                        this.board.place(outerTile, innerTile, this.currentPlayer);
                        element.setText(this.currentPlayer.getString());
                        if (this.currentPlayer == Sign.O) {
                            element.setTextFill(Color.RED);
                        }
                        else {
                            element.setTextFill(Color.BLUE);
                        }
                        this.board.unSelect(outerTile);
                        unHighlight(outerTile);
                        this.listMoves.getChildren().add(new Label(new Move(outerTile, innerTile, currentPlayer).toString()));
                        if (this.board.checkSolved()) {
                            endGame();
                        }
                        if (this.board.checkDraw()) {
                            drawGame();
                        }
                        if (checkSolvedTile(outerTile) || checkSolvedTile(innerTile)) {
                            this.currentPlayer = this.currentPlayer.getNext();
                            this.freeMove = true;
                        }
                        else if (checkDrawnTile(outerTile) || checkDrawnTile(innerTile)) {
                            this.currentPlayer = this.currentPlayer.getNext();
                            this.freeMove = true;
                        }
                        else {
                            nextMove(innerTile);
                        }
                    }
                });
                this.fieldGrids.get(outerTile).add(element, this.indexes[i][1], this.indexes[i][0], 1, 1);
                i++;
            }
        }
    }

    private void highlight(Tile tile) {
        GridPane grid = this.fieldGrids.get(tile);
        grid.setBackground(new Background(new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private void unHighlight(Tile tile) {
        GridPane grid = this.fieldGrids.get(tile);
        grid.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private void nextMove(Tile tile) {
        if (!this.board.checkSolved()) {
            this.currentPlayer = this.currentPlayer.getNext();
            this.board.select(tile);
            highlight(tile);
            this.freeMove = false;
        }
        else {
            endGame();
        }
    }
    
    private boolean checkSolvedTile(Tile tile) {
        if (this.board.checkSolvedField(tile)) {
            if (!this.locked.get(tile)) {
                this.locked.put(tile, Boolean.TRUE);
                Label label = new Label(this.currentPlayer.getString());
                if (this.currentPlayer == Sign.O) {
                    label.setTextFill(Color.RED);
                }
                else {
                    label.setTextFill(Color.BLUE);
                }
                label.setFont(new Font(100));
                label.setAlignment(Pos.CENTER);
                label.setMinSize(3 * this.tileSize, 3 * this.tileSize);
                label.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
                this.boardGrid.add(label, this.indexes[tile.getIndex()][1], this.indexes[tile.getIndex()][0], 1, 1);
            }
            return true;
        }
        return false;
    }

    private boolean checkDrawnTile(Tile tile) {
        if (this.board.checkDrawnField(tile)) {
            if (!this.locked.get(tile)) {
                this.locked.put(tile, Boolean.TRUE);
                Label label = new Label("Drawn");
                label.setFont(new Font(40));
                label.setAlignment(Pos.CENTER);
                label.setMinSize(3 * this.tileSize, 3 * this.tileSize);
                label.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
                this.boardGrid.add(label, this.indexes[tile.getIndex()][1], this.indexes[tile.getIndex()][0], 1, 1);
            }
            return true;
        }
        return false;
    }

    private void endGame() {
        Button start = new Button("Play again");
        Label label = new Label();
        if (this.currentPlayer == Sign.O) {
            label.setText("Player O has won");
        }
        else {
            label.setText("Player X has won");
        }
        label.setFont(new Font(50));
        label.setMinSize(9 * this.tileSize, 6 * tileSize);
        label.setAlignment(Pos.CENTER);
        start.setOnAction((click) -> {
            this.main.getChildren().clear();
            this.init();
        });
        VBox vbox = new VBox(label, start);
        vbox.setMinSize(9 * tileSize, 9 * tileSize);
        this.main.getChildren().add(vbox);
    }

    private void drawGame() {
        Button start = new Button("Play again");
        Label label = new Label("The game has been drawn");
        label.setFont(new Font(50));
        label.setMinSize(9 * this.tileSize, 6 * tileSize);
        label.setAlignment(Pos.CENTER);
        start.setOnAction((click) -> {
            this.main.getChildren().clear();
            this.init();
        });
        VBox vbox = new VBox(label, start);
        vbox.setMinSize(9 * tileSize, 9 * tileSize);
        this.main.getChildren().add(vbox);
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(this.main, 1000, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
