import enums.AppState;
import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;
import rules.BaseGameFactory;
import rules.IGameRulesFactory;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import java.util.Random;

public class Main extends PApplet {
    AppState state = AppState.PAUSED;

    // CONFIG
    int cellSize = 1;
    int height = 800;
    int width = 800;
    int redrawEvery = 3;
    int currentTick = 0;

    boolean[][] grid;
    IGameRulesFactory  gameRulesFactory;

    public static void main(String[] args) {
        PApplet.main("Main");
    }

    public void settings() {
        size(width, height);
    }

    public void draw() {
        background(0,0,0);
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                if (grid[x][y]) {
                    fill(255);  // Set fill color to white for alive cells
                    rect(x * cellSize, y * cellSize, cellSize, cellSize);
                }
            }
        }

        if (state == AppState.RUNNING) {
            currentTick++;
            if (currentTick > redrawEvery) {
                currentTick = 0;
                recalculateGrid();
            }
        }
    }

    public void setup() {
        stroke(255);  // Set line drawing color to white
        background(0,0,0);
        this.grid = initializeGrid(height / cellSize, width / cellSize);
        this.gameRulesFactory = new BaseGameFactory();
    }

    private void recalculateGrid() {
        boolean[][] nextGrid =  new boolean[height / cellSize][width / cellSize];
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                nextGrid[x][y] = this.grid[x][y]
                        ? gameRulesFactory.initializeGameRules().neighboursToSurvive().contains(calculateNeighbours(x, y))
                        : gameRulesFactory.initializeGameRules().neighboursToSpawn().contains(calculateNeighbours(x, y));
            }
        }
        this.grid = nextGrid;
    }

    private int calculateNeighbours(int x, int y) {
        int neighbours = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if(x + i < 0 || y + j < 0 || x + i >= (width / cellSize) || y + j >= (height / cellSize)) continue;
                neighbours += grid[x + i][y + j]
                        ? (i == 0 && j == 0) ? 0 : 1
                        : 0;
            }
        }
        return neighbours;
    }
    @Override
    public void keyPressed(KeyEvent event) {

        switch (event.getKeyCode()) {
            case 10 -> { // ENTER
                if (state == AppState.RUNNING) {
                    state = AppState.PAUSED;
                }
                recalculateGrid();
            }

            case 32  -> { // SPACE
                if (state == AppState.PAUSED) {
                    state = AppState.RUNNING;
                } else {
                    state = AppState.PAUSED;
                    currentTick = 0;
                }
            }

            case 17 -> { // CTRL
                this.grid = randomizeGrid(height / cellSize, width / cellSize);
            }

            case 82 -> { // R
                this.grid = initializeGrid(height / cellSize, width / cellSize);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        if (state == AppState.PAUSED) {
            // Get the selected cell
            int cellCol = event.getY() / cellSize;
            int cellRow = event.getX() / cellSize;

            // Toggle the cell state
            grid[cellRow][cellCol] = !grid[cellRow][cellCol];
        }
    }


    private static boolean[][] randomizeGrid(int rows, int cols) {
        Random random = new Random();
        boolean[][] array = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                array[i][j] = random.nextBoolean();
            }
        }
        return array;
    }

    private static boolean[][] initializeGrid(int rows, int cols) {
        return new boolean[rows][cols];
    }
}
