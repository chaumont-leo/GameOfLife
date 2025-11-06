package grids;

import context.Context;

public class ToroidalGridType implements IGridType {
    public void countNeighbours(int x, int y, int[][] grid, Context ctx) {
        int cols = ctx.width / ctx.cellSize;
        int rows = ctx.height / ctx.cellSize;
        
        int xMinusOne = calcCoordinate(x, -1, cols);
        int xZero = calcCoordinate(x, 0, cols);
        int xPlusOne = calcCoordinate(x, 1, cols);
        int yMinusOne = calcCoordinate(y, -1, rows);
        int yZero = calcCoordinate(y, 0, rows);
        int yPlusOne = calcCoordinate(y, 1, rows);
        
        
        if(xMinusOne >= 0) {
            if(yMinusOne >= 0) grid[xMinusOne][yMinusOne]++;
            grid[xMinusOne][yZero]++;
            if(yPlusOne < rows) grid[xMinusOne][yPlusOne]++;
        }
        if(yMinusOne >= 0) grid[xZero][yMinusOne]++;
        if(yPlusOne < rows) grid[xZero][yPlusOne]++;
        if(xPlusOne < cols) {
            if(yMinusOne >= 0) grid[xPlusOne][yMinusOne]++;
            grid[xPlusOne][yZero]++;
            if(yPlusOne < rows) grid[xPlusOne][yPlusOne]++;
        }
    }
    
    private int calcCoordinate(int base, int variation, int max) {
        return (base + variation + max) % max;
    }
    
}
