package nl.ou.desktop;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.net.MalformedURLException;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import nl.ou.domain.ContentVisitor;
import nl.ou.domain.Figure;
import nl.ou.domain.ListContent;
import nl.ou.domain.TableContent;
import nl.ou.domain.Text;

public class ContentRenderer implements ContentVisitor, ComponentCreator {

    private final JPanel panel;
    private JComponent current;
    private final Stack<Integer> listLevels;

    ContentRenderer() {
        panel = new JPanel();
        current = panel;
        listLevels = new Stack<>();
        listLevels.push(0);
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
    public void doForListStart(ListContent content) {
        int level = listLevels.peek();
        final var listPanelLayout = new FlowLayout();
        final var listPanel = new JPanel(listPanelLayout);
        for (int i = 0; i < level; i++) {
            listPanel.add(new JLabel(" "));
        }
        final var listItemsLayout = new GridLayout(0, 1);
        final var list = new JPanel(listItemsLayout);
        listPanel.add(list);
        current.add(listPanel);
        current = list;
        incListLevel();
    }

    @Override
    public void doForListEnd(ListContent content) {
        this.current = (JComponent) current.getParent().getParent();
        decListLevel();
    }

    @Override
    public void doForTableStart(TableContent content) {
        final var layout = new GridLayout(0, 1);
        final var table = new JPanel(layout);
        current.add(table);
        current = table;
        saveAndResetListLevel();
    }

    @Override
    public void doForTableRowStart(TableContent content) {
        final var layout = new GridLayout(1, 0);
        final var row = new JPanel(layout);
        current.add(row);
        current = row;
    }

    @Override
    public void doForTableEnd(TableContent content) {
        end();
        restoreListLevel();
    }

    @Override
    public void doForTableRowEnd(TableContent content) {
        end();
    }
    
    @Override
    public void doForText(Text text) {
        current.add(new JLabel(text.getText()));
    }
    
    @Override
    public JComponent getComponent() {
        return panel;
    }
    
    private void end() {
        current = (JComponent) current.getParent();
    }

    private void incListLevel() {
        listLevels.push(listLevels.pop() + 1);
    }

    private void decListLevel() {
        listLevels.push(listLevels.pop() - 1);
    }

    private void saveAndResetListLevel() {
        listLevels.push(0);
    }

    private void restoreListLevel() {
        listLevels.pop();
    }
}