package nl.ou.services.impl;

import nl.ou.domain.SlideShow;
import nl.ou.domain.StopStrategy;
import nl.ou.services.AbstractGUIFacadeFactory;
import nl.ou.services.AbstractStopStrategyFactory;
import nl.ou.services.GUIFacade;

public class SimpleGUIFacadeFactory extends AbstractGUIFacadeFactory {
    
    @Override
    public GUIFacade create(SlideShow show) {
        final var stopStrategy = AbstractStopStrategyFactory.getFactory().getStopStrategy();
        return new GUIFacade() {
            @Override
            public SlideShow getSlideshow() {
                return show;
            }

            @Override
            public StopStrategy getStopStrategy() {
                return stopStrategy;
            }
        };
    }
    
}
