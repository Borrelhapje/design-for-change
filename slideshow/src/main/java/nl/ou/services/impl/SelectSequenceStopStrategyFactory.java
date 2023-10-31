package nl.ou.services.impl;

import nl.ou.domain.StopStrategy;
import nl.ou.services.AbstractStopStrategyFactory;

public class SelectSequenceStopStrategyFactory extends AbstractStopStrategyFactory {

    @Override
    public StopStrategy getStopStrategy() {
        return new SelectSequenceStopStrategy();
    }
    
}
