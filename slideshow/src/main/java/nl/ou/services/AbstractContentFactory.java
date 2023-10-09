package nl.ou.services;

import nl.ou.domain.Content;
import nl.ou.domain.Figure;
import nl.ou.domain.ListContent;
import nl.ou.domain.Text;
import nl.ou.services.impl.SimpleContentFactory;

import java.net.URI;
import java.util.List;

public abstract class AbstractContentFactory {
    private static AbstractContentFactory CONTENT_FACTORY;

    public abstract Text createText(String text);

    public abstract ListContent createList(List<Content> listItems, boolean bulleted);

    public abstract Figure createFigure(URI source);

    public abstract Content createTable(List<List<Content>> tableItems);

    public static AbstractContentFactory getContentFactory() {
        if (CONTENT_FACTORY == null) {
            CONTENT_FACTORY = new SimpleContentFactory();
        }
        return CONTENT_FACTORY;
    }
}
