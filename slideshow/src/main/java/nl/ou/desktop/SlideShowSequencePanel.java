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

class SlideShowSequencePanel extends JPanel {

    private final Iterator<Slide> iterator;
    private final GUIFacade guiFacade;
    private JButton next;
    private JButton previous;
    private JComponent slideShower;

    SlideShowSequencePanel(GUIFacade guiFacade) {
        this.guiFacade = guiFacade;
        this.iterator = guiFacade.getSlideIterator();
        render();
        if (iterator.hasNext()) {
            iterator.next();
            onSlideChange();
        }
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
        previous.setEnabled(false);
        next = new JButton(new AbstractAction() {

            public void actionPerformed(java.awt.event.ActionEvent event) {
                iterator.next();
                onSlideChange();
            }
        });
        next.setText("Next");
        next.setEnabled(false);
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
    }

    private void registerStopKeyListener() {
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventPostProcessor(e -> {
            if (e.getKeyChar() == 'q' || e.getKeyChar() == 'Q') {
                guiFacade.stop();
                return true;
            } else {
                return false;
            }
        });
    }
}
