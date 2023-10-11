package nl.ou.desktop;

import java.awt.GridLayout;
import java.net.MalformedURLException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import nl.ou.domain.CompositeContent;
import nl.ou.domain.ContentVisitor;
import nl.ou.domain.Figure;
import nl.ou.domain.ListContent;
import nl.ou.domain.TableContent;
import nl.ou.domain.Text;

public class ContentRenderer implements ContentVisitor, ComponentCreator {

    private final JPanel panel;
    private JComponent current;

    ContentRenderer() {
        panel = new JPanel();
        current = panel;
    }

    @Override
    public void doForFigure(Figure figure) {
        try {
            final var icon = new JLabel(new ImageIcon(figure.getSource().toURL()));
            current.add(icon);
        } catch (MalformedURLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    @Override
    public void startList(ListContent content) {
        final var layout = new GridLayout(0, 1);
        final var list = new JPanel(layout);
        current.add(list);     
        current = list;
    }

    @Override
    public void startTable(TableContent content) {
        final var layout = new GridLayout(0, 1);
        final var table = new JPanel(layout);
        current.add(table);
        current = table;
    }
    
    @Override
    public void startTableRow(TableContent content) {
        final var layout = new GridLayout(1, 0);
        final var row = new JPanel(layout);
        current.add(row);
        current = row;
    }

    @Override
    public void end(CompositeContent content) {
        current = (JComponent) current.getParent();
    }
    
    @Override
    public void doForText(Text text) {
        current.add(new JLabel(text.getText()));
    }
    
    @Override
    public JComponent getComponent() {
        return panel;
    }   
}