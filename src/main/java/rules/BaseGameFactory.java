package rules;

public class BaseGameFactory implements IGameRulesFactory {

    @Override
    public IGameRules initializeGameRules() {
        return new BaseGameRules();
    }
}
