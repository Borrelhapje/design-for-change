package nl.ou.services;

import nl.ou.domain.SlideShow;
import nl.ou.domain.StopStrategy;

public interface GUIFacade {

    SlideShow getSlideshow();

    StopStrategy getStopStrategy();    
}
