package rules;

import java.util.ArrayList;

public interface IGameRules {
    public ArrayList<Integer> neighboursToSurvive();
    public ArrayList<Integer> neighboursToSpawn();
}
