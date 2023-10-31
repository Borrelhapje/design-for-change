package nl.ou.services;

import nl.ou.domain.StopStrategy;
import nl.ou.services.impl.SelectSequenceStopStrategyFactory;
import nl.ou.services.impl.SimpleStopStrategyFactory;

public abstract class AbstractStopStrategyFactory {
    private static final String STOP_STRATEGY_SELECT_SEQUENCE = "selectSequenceOnExitSlideShow";

    public abstract StopStrategy getStopStrategy();

    public static AbstractStopStrategyFactory getFactory() {
        String selectSequenceOnExitSlideShow = System.getenv().get(STOP_STRATEGY_SELECT_SEQUENCE);
        if (Boolean.TRUE.toString().equals(selectSequenceOnExitSlideShow)) {
            return new SelectSequenceStopStrategyFactory();
        } else {
            return new SimpleStopStrategyFactory();
        }
    }
    
}
