import config.ConfigManager;
import config.Configuration;
import context.Context;
import context.AppState;
import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

import java.util.Random;


public class Main extends PApplet {
    static GridParser gridParser;
    static Configuration config;

    boolean[][] grid;
    Context context;

    public static void main(String[] args) {
        config = ConfigManager.getInstance().getConfiguration();
        PApplet.main("Main");
    }

    public void settings() {
        size(
                config.cols() * config.cellSize(),
                config.rows() * config.cellSize()
        );
    }

    public void draw() {
        background(0,0,0);
        gridParser.execute(this::firstGridInit);

        if (context.state == AppState.RUNNING) {
            context.currentTick++;
            if (context.currentTick > context.tickSpeed) {
                context.currentTick = 0;
                recalculateGrid();
            }
        }
    }

    private void firstGridInit(int x, int y) {
        if (grid[x][y]) {
            fill(255);  // Set fill color to white for alive cells
            rect(x * context.cellSize, y * context.cellSize, context.cellSize, context.cellSize);
        }
    }

    public void setup() {
        stroke(255);  // Set line drawing color to white
        background(0,0,0);
        gridParser = new GridParser(config.cols(), config.rows());
        context = Context.fromConfig(config);
        grid = initializeGrid(config.rows(), config.cols());
    }

    private void recalculateGrid() {
        int[][] countGrid =  new int[context.rows][context.cols];
        boolean[][] nextGrid =  new boolean[context.rows][context.cols];

        gridParser.execute((x, y) -> {
            if(grid[x][y]) context.getNeighbourCount(x, y, countGrid);
        });

        gridParser.execute((x, y) -> {
            nextGrid[x][y] = this.grid[x][y]
                    ? context.getNeighboursToSurvive().contains(countGrid[x][y])
                    : context.getNeighboursToSpawn().contains(countGrid[x][y]);
        });
        this.grid = nextGrid;
    }

    @Override
    public void keyPressed(KeyEvent event) {

        switch (event.getKeyCode()) {
            case 10 -> { // ENTER
                if (context.state == AppState.RUNNING) {
                    context.state = AppState.PAUSED;
                }
                recalculateGrid();
            }

            case 32  -> { // SPACE
                if (context.state == AppState.PAUSED) {
                    context.state = AppState.RUNNING;
                } else {
                    context.state = AppState.PAUSED;
                    context.currentTick = 0;
                }
            }

            case 17 -> { // CTRL
                this.grid = randomizeGrid(context.rows, context.cols);
            }

            case 82 -> { // R
                this.grid = initializeGrid(context.rows, context.cols);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        if (context.state == AppState.PAUSED) {
            // Get the selected cell
            int cellCol = event.getY() / context.cellSize;
            int cellRow = event.getX() / context.cellSize;

            // Toggle the cell state
            grid[cellRow][cellCol] = !grid[cellRow][cellCol];
        } else {
            context.state = AppState.PAUSED;
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
