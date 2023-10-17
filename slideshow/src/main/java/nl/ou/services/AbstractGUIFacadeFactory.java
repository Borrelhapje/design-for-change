package nl.ou.services;

import nl.ou.domain.SlideShow;
import nl.ou.services.impl.SimpleGUIFacadeFactory;

public abstract class AbstractGUIFacadeFactory {

    public abstract GUIFacade create(SlideShow show);
    
    public static AbstractGUIFacadeFactory getFactory() {
        return new SimpleGUIFacadeFactory();
    }

}
