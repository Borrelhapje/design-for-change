package nl.ou.services.impl;

import nl.ou.services.GUIFacade;
import nl.ou.services.StopStrategy;

public class SystemExitStrategy implements StopStrategy {

    public void stop(GUIFacade guiFacade) {
        System.exit(0);
    }
    
}
