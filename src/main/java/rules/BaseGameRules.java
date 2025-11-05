package rules;

import java.util.ArrayList;
import java.util.Arrays;

public class BaseGameRules implements IGameRules {
    @Override
    public ArrayList<Integer> neighboursToSurvive() {
        return new ArrayList<>(Arrays.asList(2, 3));
    }
}
