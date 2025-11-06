package grids;

import context.Context;

public class ToroidalGridType implements IGridType {
    public void countNeighbours(int x, int y, int[][] grid, Context ctx) {
        int xMinusOne = calcCoordinate(x, -1, ctx.cols);
        int xZero = calcCoordinate(x, 0, ctx.cols);
        int xPlusOne = calcCoordinate(x, 1, ctx.cols);
        int yMinusOne = calcCoordinate(y, -1, ctx.rows);
        int yZero = calcCoordinate(y, 0, ctx.rows);
        int yPlusOne = calcCoordinate(y, 1, ctx.rows);
        
        
        if(xMinusOne >= 0) {
            if(yMinusOne >= 0) grid[xMinusOne][yMinusOne]++;
            grid[xMinusOne][yZero]++;
            if(yPlusOne < ctx.rows) grid[xMinusOne][yPlusOne]++;
        }
        if(yMinusOne >= 0) grid[xZero][yMinusOne]++;
        if(yPlusOne < ctx.rows) grid[xZero][yPlusOne]++;
        if(xPlusOne < ctx.cols) {
            if(yMinusOne >= 0) grid[xPlusOne][yMinusOne]++;
            grid[xPlusOne][yZero]++;
            if(yPlusOne < ctx.rows) grid[xPlusOne][yPlusOne]++;
        }
    }
    
    private int calcCoordinate(int base, int variation, int max) {
        return (base + variation + max) % max;
    }
    
}
