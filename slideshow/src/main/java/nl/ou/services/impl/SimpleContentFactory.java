package nl.ou.services.impl;

import nl.ou.domain.*;
import nl.ou.services.AbstractContentFactory;

import java.net.URI;
import java.util.List;

public class SimpleContentFactory extends AbstractContentFactory {
    @Override
    public Text createText(String text) {
        return new Text(text);
    }

    @Override
    public ListContent createList(List<Content> listItems, boolean bulleted) {
        return new ListContent(listItems, bulleted);
    }

    @Override
    public Figure createFigure(URI source) {
        return new Figure(source);
    }

    @Override
    public TableContent createTable(List<List<Content>> tableItems) {
        return new TableContent(tableItems);
    }
}
