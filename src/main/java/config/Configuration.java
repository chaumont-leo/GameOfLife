package config;

public record Configuration(
        int cellSize,
        int cols,
        int rows,
        GridType baseGridType,
        RuleSet baseRuleSet,
        int tickSpeed
) {}
