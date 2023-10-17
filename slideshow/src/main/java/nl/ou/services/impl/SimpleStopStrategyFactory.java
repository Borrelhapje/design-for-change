package nl.ou.services.impl;

import nl.ou.domain.StopStrategy;
import nl.ou.domain.SystemExitStrategy;
import nl.ou.services.AbstractStopStrategyFactory;

public class SimpleStopStrategyFactory extends AbstractStopStrategyFactory {

    @Override
    public StopStrategy getStopStrategy() {
        return new SystemExitStrategy();
    }
    
}
