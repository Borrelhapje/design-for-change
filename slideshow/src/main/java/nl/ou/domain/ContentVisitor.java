package nl.ou.domain;

public interface ContentVisitor {

    /**
     * action if a list is encountered
     * @param content
     */
    void doForListStart(ListContent content);

    void doForListEnd(ListContent content);

    /**
     * action for when a table is encountered
     * @param content
     */
    void doForTableStart(TableContent content);

    void doForTableEnd(TableContent content);

    /**
     * called when a new table row is encountered
     */
    void doForTableRowStart(TableContent content);

    void doForTableRowEnd(TableContent content);

    /**
     * action if a text is encountered
     * @param text
     */
    void doForText(Text text);

    /**
     * action if a figure is encountered
     * @param figure
     */
    void doForFigure(Figure figure);
    
}
