package context;

import config.Configuration;
import grids.BaseGridType;
import grids.IGridType;
import grids.ToroidalGridType;
import rules.IGameRules;

import java.util.ArrayList;

public class Context {
    public int rows;
    public int cols;
    public int cellSize;
    public int currentTick = 0;
    public int tickSpeed;

    public AppState state;

    private IGameRules gameRules;
    private IGridType gridType;

    public static Context fromConfig(Configuration config) {
        Context ctx = new Context();
        ctx.cellSize = config.cellSize();
        ctx.rows = config.rows();
        ctx.cols = config.cols();
        ctx.tickSpeed = config.tickSpeed();
        ctx.state = AppState.PAUSED;
        ctx.setGridType(
                switch (config.baseGridType()) {
                    case BASE -> new BaseGridType();
                    case TOROIDAL -> new ToroidalGridType();
                }
        );
        ctx.setGameRules(
                switch (config.baseRuleSet()) {
                    case BASE -> new rules.BaseGameRules();
                }
        );
        return ctx;
    }

    public void setGameRules(IGameRules gameRules) {
        this.gameRules = gameRules;
    }

    public void setGridType(IGridType gridType) {
        this.gridType = gridType;
    }

    public void getNeighbourCount(int x, int y, int[][] grid) {
        gridType.countNeighbours(x, y, grid, this);
    }

    public ArrayList<Integer> getNeighboursToSurvive() {
        return gameRules.neighboursToSurvive();
    }

    public ArrayList<Integer> getNeighboursToSpawn() {
        return gameRules.neighboursToSpawn();
    }
}
