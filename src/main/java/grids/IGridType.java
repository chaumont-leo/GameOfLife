package grids;

import context.Context;

public interface IGridType {
    void countNeighbours(int x, int y, int[][] grid, Context ctx);
}
