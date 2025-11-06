package grids;

import context.Context;

public class BaseGridType implements IGridType {
    public void countNeighbours(int x, int y, int[][] grid, Context ctx) {
        if(x-1 >= 0) {
            if(y - 1 >= 0) grid[x-1][y-1]++;
            grid[x-1][y]++;
            if(y + 1 < ctx.rows) grid[x-1][y+1]++;
        }
        if(y - 1 >= 0) grid[x][y-1]++;
        if(y + 1 < ctx.rows) grid[x][y+1]++;
        if(x+1 < ctx.cols) {
            if(y - 1 >= 0) grid[x+1][y-1]++;
            grid[x+1][y]++;
            if(y + 1 < ctx.rows) grid[x+1][y+1]++;
        }
    }
}
