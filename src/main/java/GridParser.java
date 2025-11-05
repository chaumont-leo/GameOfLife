import java.util.function.BiConsumer;

public class GridParser {
    private final int cols;

    private final int rows;

    public GridParser(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
    }

    public void execute(BiConsumer<Integer, Integer> consumer) {
        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                consumer.accept(x, y);
            }
        }
    }
}
