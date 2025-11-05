import enums.AppState;
import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

import java.util.Random;

public class Main extends PApplet {
    AppState state = AppState.PAUSED;

    // CONFIG
    int cellSize = 8;
    int height = 800;
    int width = 800;
    int redrawEvery = 100;
    int currentTick = 0;

    boolean[][] grid;

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
        // Initialize the grid with false
        this.grid = initializeGrid(height / cellSize, width / cellSize);
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

    private void recalculateGrid() {}

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
