import enums.AppState;
import processing.core.PApplet;
import processing.event.KeyEvent;

import java.util.Random;

import java.util.Random;

public class Main extends PApplet {
    AppState state = AppState.PAUSED;

    // CONFIG
    int cellSize = 8;
    int height = 800;
    int width = 800;
    int redrawEvery = 100;
    int currentTick = 100;

    boolean[][] grid;

    public static void main(String[] args) {
        PApplet.main("Main");
    }

    public void settings() {
        size(width, height);
    }

    public void draw() {
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                if (grid[x][y]) {
                    rect(x * cellSize, y * cellSize, cellSize, cellSize);
                }
            }
        }

        if (state == AppState.RUNNING) {
            currentTick++;
            if (currentTick > redrawEvery) {
                currentTick = 0;
                println("DRAW");
            }
        }
    }

    public void setup() {
        stroke(255);  // Set line drawing color to white
        background(0,0,0);
        this.grid = randomize(height / cellSize, width / cellSize);
    }

    @Override
    public void keyPressed(KeyEvent event) {
        switch (event.getKey()) {
            case 10 -> { // ENTER
                if (state == AppState.RUNNING) {
                    state = AppState.PAUSED;
                }
                println("FORCE_DRAW");
                recalculateGrid();
            }

            case 32  -> { // SPACE
                if (state == AppState.PAUSED) {
                    state = AppState.RUNNING;
                    println("RESUME");
                } else {
                    state = AppState.PAUSED;
                    currentTick = 0;
                    println("STOP");
                }
            }
        }
    }

    private void recalculateGrid() {}

    private static boolean[][] randomize(int rows, int cols) {
        Random random = new Random();
        boolean[][] array = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                array[i][j] = random.nextBoolean();
            }
        }
        return array;
    }
}
