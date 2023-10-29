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
import java.awt.KeyboardFocusManager;

public class SlideShowSequencePanel extends JPanel {

    private final Iterator<Slide> iterator;
    private final GUIFacade facade;
    private JButton next;
    private JButton previous;
    private JComponent slideShower;

    public SlideShowSequencePanel(GUIFacade facade) {
        this.facade = facade;
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
        registerStopKeyListener();
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

    private void registerStopKeyListener() {
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventPostProcessor(e -> {
            if (e.getKeyChar() == 'q' || e.getKeyChar() == 'Q') {
                facade.getStopStrategy().stop();
                return true;
            } else {
                return false;
            }
        });
    }
}
