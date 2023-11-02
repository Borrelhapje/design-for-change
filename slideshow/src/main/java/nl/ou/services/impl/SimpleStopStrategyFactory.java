package nl.ou.services.impl;

import nl.ou.services.StopStrategy;
import nl.ou.services.AbstractStopStrategyFactory;

public class SimpleStopStrategyFactory extends AbstractStopStrategyFactory {

    @Override
    public StopStrategy getStopStrategy() {
        return new SystemExitStrategy();
    }
    
}
