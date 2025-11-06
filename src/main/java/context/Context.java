package context;

import grids.IGridType;
import rules.IGameRules;

import java.util.ArrayList;

public class Context {
    public int height;
    public int width;
    public int cellSize;

    private IGameRules gameRules;
    private IGridType gridType;

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
