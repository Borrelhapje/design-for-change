package nl.ou.services.impl;

import nl.ou.domain.StopStrategy;
import nl.ou.services.GUIFacade;

public class SelectSequenceStopStrategy implements StopStrategy {

    @Override
    public void stop(GUIFacade guiFacade) {
        guiFacade.showSequenceSelection();
    }
}
