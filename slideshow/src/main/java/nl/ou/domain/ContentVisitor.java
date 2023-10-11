package nl.ou.domain;

public interface ContentVisitor {

    /**
     * action if a list is encountered
     * @param content
     */
    void startList(ListContent content);

    /**
     * action for when a table is encountered
     * @param content
     */
    void startTable(TableContent content);

    /**
     * called when a new table row is encountered
     */
    void startTableRow(TableContent content);

    /**
     * called at the end of the current composite
     * @param content
     */
    void end(CompositeContent content);

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
