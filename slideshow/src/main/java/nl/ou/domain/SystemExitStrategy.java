package nl.ou.domain;

import nl.ou.services.GUIFacade;

public class SystemExitStrategy implements StopStrategy {

    public void stop(GUIFacade guiFacade) {
        System.exit(0);
    }
    
}
