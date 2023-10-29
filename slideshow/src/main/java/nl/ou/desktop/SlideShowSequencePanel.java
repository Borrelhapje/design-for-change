package nl.ou.desktop;

import nl.ou.domain.Iterator;
import nl.ou.domain.Slide;
import nl.ou.services.GUIFacade;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.FlowLayout;

public class SlideShowSequencePanel extends JPanel {

    private final Iterator<Slide> iterator;
    private JButton next;
    private JButton previous;
    private JComponent slideShower;

    public SlideShowSequencePanel(GUIFacade facade) {
        this.iterator = facade.getSlideshow().getSlideIterator();
        render();
        onSlideChange();
    }

    private void render() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        previous = new JButton(new AbstractAction() {

            public void actionPerformed(java.awt.event.ActionEvent event) {
                iterator.previous();
                onSlideChange();
            }
        });
        previous.setText("Back");
        next = new JButton(new AbstractAction() {

            public void actionPerformed(java.awt.event.ActionEvent event) {
                iterator.next();
                onSlideChange();
            }
        });
        next.setText("Next");
        final var buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(previous);
        buttonPanel.add(next);
        add(buttonPanel);
    }

    private void onSlideChange() {
        if (slideShower != null) {
            remove(slideShower);
            invalidate();
        }
        previous.setEnabled(iterator.hasPrevious());
        next.setEnabled(iterator.hasNext());
        slideShower = new SlideRenderer(iterator.current()).getComponent();
        add(slideShower);
        revalidate();
        repaint();
        setVisible(true);
    }
}
