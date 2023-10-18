package nl.ou.services.impl;

import nl.ou.domain.*;
import nl.ou.services.AbstractContentFactory;

import java.net.URI;
import java.util.Collection;
import java.util.List;

public class SimpleContentFactory extends AbstractContentFactory {
    @Override
    public Text createText(String text) {
        return new Text(text);
    }

    @Override
    public ListContent createList(List<Content> listItems, boolean bulleted) {
        ListContent listContent = new ListContent(listItems, bulleted);
        listItems.forEach(li -> li.setParent(listContent));
        return listContent;
    }

    @Override
    public Figure createFigure(URI source) {
        return new Figure(source);
    }

    @Override
    public TableContent createTable(List<List<Content>> tableItems) {
        TableContent tableContent = new TableContent(tableItems);
        tableItems.stream()
                .flatMap(Collection::stream)
                .forEach(ti -> ti.setParent(tableContent));
        return tableContent;
    }
}
