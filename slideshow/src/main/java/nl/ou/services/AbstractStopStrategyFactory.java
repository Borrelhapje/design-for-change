package nl.ou.services;

import nl.ou.domain.StopStrategy;
import nl.ou.services.impl.SimpleStopStrategyFactory;

public abstract class AbstractStopStrategyFactory {

    public abstract StopStrategy getStopStrategy();

    public static AbstractStopStrategyFactory getFactory() {
        return new SimpleStopStrategyFactory();
    }
    
}
