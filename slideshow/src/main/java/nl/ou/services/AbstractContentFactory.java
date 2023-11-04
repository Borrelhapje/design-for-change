package nl.ou.services;

import nl.ou.domain.Content;
import nl.ou.services.impl.SimpleContentFactory;

import java.net.URI;
import java.util.List;

public abstract class AbstractContentFactory {
    private static AbstractContentFactory CONTENT_FACTORY;

    public abstract Content createText(String text);

    public abstract Content createList(List<Content> listItems, boolean bulleted);

    public abstract Content createFigure(URI source);

    public abstract Content createTable(List<List<Content>> tableItems);

    public static AbstractContentFactory getContentFactory() {
        if (CONTENT_FACTORY == null) {
            CONTENT_FACTORY = new SimpleContentFactory();
        }
        return CONTENT_FACTORY;
    }
}
