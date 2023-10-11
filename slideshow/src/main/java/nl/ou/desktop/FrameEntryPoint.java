package nl.ou.desktop;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import nl.ou.domain.Iterator;
import nl.ou.domain.Slide;
import nl.ou.services.GUIFacade;

public class FrameEntryPoint {

    private final GUIFacade facade;
    private final Iterator<Slide> iterator;
    private final JComponent frame;
    private JComponent slideShower;

    public FrameEntryPoint(GUIFacade facade) {
        this.facade = facade;
        this.iterator = facade.getSlideshow().getSlideIterator();
        this.frame = renderFrame();
        onSlideChange();
    }

    private JComponent renderFrame() {
        final var frame = new JFrame("JabberPoint " + facade.getSlideshow().getMeta().getTitle());
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                facade.getStopStrategy().stop();
            }
        });
        final var panel = new JPanel();
        frame.add(panel);
        final var nextButton = new JButton(new AbstractAction() {
            public boolean isEnabled() {
                return iterator.hasNext();
            }

            public void actionPerformed(java.awt.event.ActionEvent event) {
                iterator.next();
                onSlideChange();
            }
        });
        nextButton.setText("Next");
        panel.add(nextButton);
        final var prevButton = new JButton(new AbstractAction() {
            public boolean isEnabled() {
                return iterator.hasPrevious();
            }

            public void actionPerformed(java.awt.event.ActionEvent event) {
                iterator.previous();
                onSlideChange();
            }
        });
        prevButton.setText("Back");
        panel.add(prevButton);
        frame.setVisible(true);
        frame.setAutoRequestFocus(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 800);
        return panel;
    }
    
    private void onSlideChange() {
        if (slideShower != null) {
            frame.remove(slideShower);
        }
        slideShower = new SlideRenderer(iterator.current()).getComponent();
        frame.add(slideShower);
        frame.validate();
    }
    
}
